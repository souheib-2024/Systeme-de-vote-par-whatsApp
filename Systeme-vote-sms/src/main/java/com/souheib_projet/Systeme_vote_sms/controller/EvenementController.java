package com.souheib_projet.Systeme_vote_sms.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.souheib_projet.Systeme_vote_sms.model.Admin;
import com.souheib_projet.Systeme_vote_sms.model.Evenement;
import com.souheib_projet.Systeme_vote_sms.service.EvenementService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@Validated
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @PostMapping("/evenements")
    public String ajouterEvenement(@RequestParam("event") String eventName,  @RequestParam("description") String description, @RequestParam("start_date") LocalDate startDate, @RequestParam("end_date") LocalDate endDate) {
    	
     // Créer un objet Evenement
        Evenement evenement = new Evenement();
        evenement.setNom(eventName);  // Affectation de l'événement
        evenement.setDescription(description);  // Affectation de la description
        evenement.setDateDebut(startDate);  // Affectation de la date de début
        evenement.setDateFin(endDate); 
     	
    // Enregistrer l'événement
       evenementService.ajouterEvenement(evenement);
 
    // Redirigez vers le formulaire vide
       return "choixVote";

    }
    
   

}