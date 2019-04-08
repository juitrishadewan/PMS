package com.coderbd.controller;

import com.coderbd.entity.Company;
import com.coderbd.entity.Role;
import com.coderbd.repo.CompanyRepo;
import com.coderbd.repo.RoleRepo;
import com.coderbd.util.ImageOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping(value = "/company/")
public class CompanyController {

    @Autowired
    private CompanyRepo repo;

    @Autowired
    private ImageOptimizer imageOptimizer;

    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";

    @GetMapping(value = "add")
    public String viewAdd(Model model) {
        model.addAttribute("role", new Role());
        return "companies/add";
    }

    @PostMapping(value = "add")
    public String add(@Valid Company company, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "companies/add";
        }
        {
            if (repo.existsCompanyByEmail(company.getEmail())) {
                model.addAttribute("rejectMsg", "Already Have This Entry");
            } else {
                 this.repo.save(company);
                model.addAttribute("successMsg", "Successfully Saved!");
            }
        }
        return "companies/add";
    }

    @GetMapping(value = "edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("company", repo.getOne(id));
        return "companies/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@Valid Company company, BindingResult result, Model model, @PathVariable("id") Long id,@RequestParam("file") MultipartFile file) {
        if (result.hasErrors()) {
            return "companies/edit";
        }
        Company company1 = this.repo.getOne(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setMobile(company.getMobile());
             try {
            //////////////////////For Image Upload start /////////////////////
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

            Files.write(path, bytes);
            company1.setFileName("new-" + file.getOriginalFilename());
            company1.setFileSize(file.getSize());
            // user.setFile(file.getBytes());
            company1.setFilePath("/images/" + "new-"+ file.getOriginalFilename());
            company1.setFileExtension(file.getContentType());
            //////////////////////For Image Upload end/////////////////////

            this.repo.save(company1);

            imageOptimizer.optimizeImage(UPLOADED_FOLDER, file, 0.3f, 100, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/company/list";
    }

    @GetMapping(value = "del/{id}")
    public String del(@PathVariable("id") Long id) {
        if (id != null) {
            this.repo.deleteById(id);
        }
        return "redirect:/company/list";
    }

    @GetMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("list", this.repo.findAll());
        return "companies/list";
    }

}
