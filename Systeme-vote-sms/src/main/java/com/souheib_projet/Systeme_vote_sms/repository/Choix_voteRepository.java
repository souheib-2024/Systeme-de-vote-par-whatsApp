package com.souheib_projet.Systeme_vote_sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souheib_projet.Systeme_vote_sms.model.Choix_vote;

public interface Choix_voteRepository extends JpaRepository<Choix_vote, Long>{

	Optional<Choix_vote> findByImageName(String imageName);

}
