package com.souheib_projet.Systeme_vote_sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souheib_projet.Systeme_vote_sms.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	 Admin findByNomAndMp(String nom, String mp);
}
