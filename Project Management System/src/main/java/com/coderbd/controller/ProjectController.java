package com.coderbd.controller;

import com.coderbd.entity.Company;
import com.coderbd.entity.Project;
import com.coderbd.entity.Role;
import com.coderbd.entity.User;
import com.coderbd.repo.CompanyRepo;
import com.coderbd.repo.ProjectRepo;
import com.coderbd.repo.UserRepo;
import com.coderbd.util.ImageOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/project/")
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private CompanyRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImageOptimizer imageOptimizer;

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("project", new Project());
        return "projects/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "projects/add";
        }
        {
            if (projectRepo.existsProjectByCompanyAndTitle(project.getCompany(), project.getTitle())) {
                model.addAttribute("rejectMsg", "Already Have This Entry");
            } else {
                this.projectRepo.save(project);
                model.addAttribute("successMsg", "Successfully Saved!");
            }
        }
        return "projects/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("project", projectRepo.getOne(id));
        return "projects/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Project project, BindingResult result, Model model, @PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "projects/edit";
        }
        Project project1 = this.projectRepo.getOne(id);
        project1.setTitle(project.getTitle());
        project1.setProjectStatus(project.getProjectStatus());
        try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);
            project1.setFileName("new-" + file.getOriginalFilename());
            project1.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            project1.setFilePath("/images/" +projectRepo.findByCompanyAndTitle(project.getCompany(),project.getTitle()).getId() +"-new-" + file.getOriginalFilename());
            project1.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////

            this.projectRepo.save(project1);

            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.9f, 100, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/project/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/project/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User user=userRepo.findByUserName(auth.getName());
        model.addAttribute("list", this.projectRepo.findAllByCompany(user.getCompany()));
        return "projects/list";
    }

}
