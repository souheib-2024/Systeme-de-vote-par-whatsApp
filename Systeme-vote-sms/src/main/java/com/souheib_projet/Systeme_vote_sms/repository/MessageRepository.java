
package com.souheib_projet.Systeme_vote_sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.souheib_projet.Systeme_vote_sms.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}