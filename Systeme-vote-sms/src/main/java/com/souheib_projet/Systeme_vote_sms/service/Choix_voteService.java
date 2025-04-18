package com.souheib_projet.Systeme_vote_sms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souheib_projet.Systeme_vote_sms.model.Choix_vote;
import com.souheib_projet.Systeme_vote_sms.repository.Choix_voteRepository;

@Service
public class Choix_voteService {
	 @Autowired
	    private Choix_voteRepository voteRepository;

	    public void saveVotes(List<Choix_vote> votes) {
	        voteRepository.saveAll(votes);
	    }
	 // Recherche un vote par le nom de l'image
	    public Optional<Choix_vote> findByImageName(String imageName) {
	        return voteRepository.findByImageName(imageName);
	    }
	 // Ajout de la méthode pour récupérer tous les votes
	    public List<Choix_vote> findAllVotes() {
	        return voteRepository.findAll();
	    }	    
	    // Méthode pour récupérer tous les numéros de vote valides
	    public List<String> findAllVoteNumbers() {
	        List<Choix_vote> votes = voteRepository.findAll();
	        return votes.stream()
	                    .map(vote -> String.valueOf(vote.getNumeroVote())) // Convertit les numéros en chaîne de caractères
	                    .toList(); // Retourne une liste de chaînes
	    }
	    public Optional<Choix_vote> findById(Long id) {
	        return voteRepository.findById(id);
	    }

	 

}
