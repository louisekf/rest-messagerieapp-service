package com.springboot.restservice.messagerieapp.repository;

import com.springboot.restservice.messagerieapp.model.MailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MailMessageRepository extends JpaRepository<MailMessage, Long> {
    Optional<MailMessage> findById(Long id);
    List<MailMessage> findByMailboxIdOrderByDateEnvoiDesc(Long mailboxId);
    List<MailMessage> findByDestinataireOrderByDateEnvoiDesc(String destinataire);
    long countByMailboxIdAndLuFalse(Long mailboxId);
}
