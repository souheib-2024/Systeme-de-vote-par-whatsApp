package com.souheib_projet.Systeme_vote_sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class RoutageController {
		
	 @GetMapping("/")
	    public String showForm() {
	        return "admin_form";
	    }
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate(); // Supprimer la session
	        return "admin_form";
	    }
	 @GetMapping("/accueil")
	    public String accueil() {
	        return "accueil"; 
	    }
	 @GetMapping("/liste_evenement")
	    public String listeEvenement() {
	        return "liste_evenement"; // This corresponds to a Thymeleaf template in `src/main/resources/templates/
	    }
	@GetMapping("/bd")
		public String getBoiteDialog() {
		    return "bd"; // Correspond au fichier bd.html dans src/main/resources/templates (Thymeleaf)
		}
	 @GetMapping("/Resultat")
	    public String res() {
	        return "Resultat"; // This corresponds to a Thymeleaf template in `src/main/resources/templates/
	    }
	@GetMapping("/parametre")
		public String param() {
		    return "parametre"; // Correspond au fichier bd.html dans src/main/resources/templates (Thymeleaf)
		}


	 
	
}
