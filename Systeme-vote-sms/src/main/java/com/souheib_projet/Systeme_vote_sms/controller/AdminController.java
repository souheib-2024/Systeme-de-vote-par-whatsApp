package com.souheib_projet.Systeme_vote_sms.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.souheib_projet.Systeme_vote_sms.model.Admin;
import com.souheib_projet.Systeme_vote_sms.repository.AdminRepository;
import org.springframework.ui.Model;



@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public String login(@RequestParam String nom, @RequestParam String mp, Model model,HttpSession session) {
        Admin admin = adminRepository.findByNomAndMp(nom, mp);
        if (admin != null) {
            model.addAttribute("admin", admin);
            session.setAttribute("adminNom", admin.getNom());
            return "accueil";  
        } else {
            model.addAttribute("error", "Nom ou mot de passe incorrect.");
            return "admin_form";
        }
    }



}
