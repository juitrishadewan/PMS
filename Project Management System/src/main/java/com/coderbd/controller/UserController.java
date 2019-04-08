package com.coderbd.controller;


import com.coderbd.entity.User;
import com.coderbd.repo.RoleRepo;
import com.coderbd.repo.UserRepo;
import com.coderbd.util.ImageOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageOptimizer imageOptimizer;

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @GetMapping(value = "add")
    public String viewAdd(Model model){
        model.addAttribute("user",new User());
        return "users/add";
    }
    @PostMapping(value = "add")
    public String add(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "users/add";
        }
        if(repo.existsByEmail(user.getEmail())){
            model.addAttribute("rejectMsg","Already Have This Entry");
        }else{
            String username = user.getEmail().split("\\@")[0];
            user.setUserName(username);
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setConfirmationToken(UUID.randomUUID().toString());
            this.repo.save(user);
            model.addAttribute("successMsg","Successfully Saved!");
        }

        return "users/add";
    }


    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user",repo.getOne(id));
        return "users/edit";
    }
    @PostMapping(value = "edit/{id}")
    public String edit(@Valid User user, BindingResult result, Model model,@PathVariable("id") Long id,@RequestParam("file") MultipartFile file){
        if(result.hasErrors()){
            return "users/edit";
        }
        User u = this.repo.getOne(id);
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setMobile(user.getMobile());


        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);
            u.setFileName("new-" + file.getOriginalFilename());
            u.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            u.setFilePath("/images/" + "new-" + file.getOriginalFilename());
            u.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////

            this.repo.save(u);

           imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/user/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id){
        if(id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/user/list";
    }

    @GetMapping(value = "list")
    public String list(Model model){
          model.addAttribute("list",this.repo.findAll());
        return "users/list";
    }

}
