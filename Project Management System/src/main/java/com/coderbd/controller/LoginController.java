package com.coderbd.controller;


import com.coderbd.entity.Company;
import com.coderbd.entity.Role;
import com.coderbd.entity.User;
import com.coderbd.repo.CompanyRepo;
import com.coderbd.repo.RoleRepo;
import com.coderbd.repo.UserRepo;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@Scope(scopeName="prototype")
public class LoginController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String USER_NAME = "rajaul.cse";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = ""; // GMail password

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String loginError() {
        return "redirect:/";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }


    @PostMapping(value = "/sign-up")
    public String add(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        String username = user.getEmail().split("\\@")[0];
        user.setUserName(username);
        user.setEnabled(false);
        user.setConfirmationToken(UUID.randomUUID().toString());
        user.setRegiDate(new Date());

        if (user.getCompnayName() == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(roleRepo.findByRoleName("USER").get().getId()));
            user.setRoles(roles);
        } else {
            if (companyRepo.existsCompanyByEmail(user.getEmail())) {
                model.addAttribute("rejectMsg", "opps, Company Already Exist!");
                return "signup";
            } else {

                Company company = new Company();
                company.setCompanyName(user.getCompnayName().trim());
                company.setEmail(user.getEmail());
                this.companyRepo.save(company);
                Company companyExist = this.companyRepo.findByEmail(user.getEmail());
                user.setCompany(companyExist);
                Set<Role> roles = new HashSet<>();
                roles.add(new Role(roleRepo.findByRoleName("CADMIN".trim()).get().getId()));
                roles.add(new Role(roleRepo.findByRoleName("USER").get().getId()));
                user.setRoles(roles);
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("rejectMsg", "opps, Something Wrong");
            return "signup";
        }
        if (repo.existsByEmail(user.getEmail())) {
            model.addAttribute("rejectMsg", "Already Have This Entry");
            return "signup";
        } else {

            this.repo.save(user);
            //email sending
            // 172.16.185.51
            //String appUrl = request.getScheme() + "://" + request.getServerName()+":"+"8008";
            String appUrl = request.getScheme() + "://" + request.getRemoteAddr() + ":" + "8008";
            System.out.println("appUrl: " + appUrl);
            String from = USER_NAME;
            String pass = PASSWORD;
            String[] to = {user.getEmail()}; // list of recipient email addresses
            String subject = "Registration Confirmation";
            String body = "To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirm?token=" + user.getConfirmationToken();

            sendFromGMail(from, pass, to, subject, body);

            model.addAttribute("successMsg", "A confirmation e-mail has been sent to " + user.getEmail());
        }


        return "signup";
    }

    @GetMapping(value = "/sign-up")
    public String viewSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping(value = "/forget")
    public String forget(@RequestParam(value = "email") String email, Model model, HttpServletRequest request) {



        if (!repo.existsByEmail(email)) {
            model.addAttribute("rejectMsg", "This email addresss is not associated with US");
            return "forget";
        } else {
            Optional<User> user1=repo.findByEmail(email);
            //email sending
            // 172.16.185.51
            //String appUrl = request.getScheme() + "://" + request.getServerName()+":"+"8008";
            String appUrl = request.getScheme() + "://" + request.getRemoteAddr() + ":" + "8008";
            System.out.println("appUrl: " + appUrl);
            String from = USER_NAME;
            String pass = PASSWORD;
            String[] to = {email}; // list of recipient email addresses
            String subject = "Got Your Password";
            String body = "To confirm your e-mail address, please click the link below:\n"
                    + appUrl + "/confirm?token=" + user1.get().getConfirmationToken();

            sendFromGMail(from, pass, to, subject, body);
            repo.setTokenExpiredByEmail(false,email);
            model.addAttribute("successMsg", "An e-mail has been sent to " + email);
        }


        return "forget";
    }

    @GetMapping(value = "/forget")
    public String viewForgetPassword(Model model) {
        model.addAttribute("user", new User());
        return "forget";
    }


    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam(value = "token", required = false) String token) {
        try {
            if (token.isEmpty()) {
                modelAndView.addObject("invalidToken", "Oops!  No Token Exist.");
                modelAndView.setViewName("confirm");
            } else {
                User user = repo.findByConfirmationTokenAndTokenExpired(token, false);

                if (user == null) { // No token found in DB
                    modelAndView.addObject("invalidToken", "Oops!  invalid confirmation link. Or Expired");
                } else { // Token found
                    modelAndView.addObject("confirmationToken", user.getConfirmationToken());
                }
            }
        } catch (NullPointerException ne) {
            modelAndView.addObject("invalidToken", "Oops!  No Token Exist.");
            modelAndView.setViewName("confirm");
        }
        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        modelAndView.setViewName("confirm");

        Zxcvbn passwordCheck = new Zxcvbn();

        Strength strength = passwordCheck.measure(requestParams.get("password"));

        if (strength.getScore() < 3) {
            //modelAndView.addObject("errorMessage", "Your password is too weak.  Choose a stronger one.");
            bindingResult.reject("password");

            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            //	System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        // Find the user associated with the reset token
        User user = repo.findByConfirmationTokenAndTokenExpired(requestParams.get("token"), false);

        // Set new password
        user.setPassword(passwordEncoder.encode(requestParams.get("password")));
        user.setTokenExpired(true);
        // Set user to enabled
        user.setEnabled(true);

        // Save user
        repo.save(user);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("rajaul.cse@gmail.com", "PMS Limited, Bangladesh"));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Email sending Success!!!");
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
