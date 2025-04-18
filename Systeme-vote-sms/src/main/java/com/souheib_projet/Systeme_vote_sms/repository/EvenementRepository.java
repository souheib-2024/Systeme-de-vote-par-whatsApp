package com.souheib_projet.Systeme_vote_sms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.souheib_projet.Systeme_vote_sms.model.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    
}