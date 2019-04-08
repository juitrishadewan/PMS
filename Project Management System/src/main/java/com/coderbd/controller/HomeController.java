package com.coderbd.controller;



import com.coderbd.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private RoleRepo repo;
    @GetMapping(value = "/")
    public String displayDashboard(){
        return "index";
    }
    @GetMapping(value = "/about")
    public String displayAbout(){
        return "about";
    }

    @GetMapping(value = "/test")
    public String displayTest(Model model)
    {
        model.addAttribute("list",this.repo.findAll());
        return "test";
    }
    // Total control - setup a model and return the view name yourself. Or
    // consider subclassing ExceptionHandlerExceptionResolver (see below).
//    @ExceptionHandler(Exception.class)
//    @GetMapping(value = "/error")
//    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", ex);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }
}
