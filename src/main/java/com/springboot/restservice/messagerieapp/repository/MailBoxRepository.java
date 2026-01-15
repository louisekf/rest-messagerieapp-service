package com.springboot.restservice.messagerieapp.repository;

import com.springboot.restservice.messagerieapp.model.MailBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailBoxRepository extends JpaRepository<MailBox, Long> {
    Optional<MailBox> findById(Long id);
}
