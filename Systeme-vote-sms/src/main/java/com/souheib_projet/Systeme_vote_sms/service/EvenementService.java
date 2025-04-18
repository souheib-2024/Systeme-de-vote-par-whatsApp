package com.souheib_projet.Systeme_vote_sms.service;

import org.springframework.stereotype.Service;
import com.souheib_projet.Systeme_vote_sms.model.Evenement;
import com.souheib_projet.Systeme_vote_sms.repository.EvenementRepository;

@Service
public class EvenementService {
    private final EvenementRepository evenementRepository;

    public EvenementService(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    public Evenement ajouterEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }
}
