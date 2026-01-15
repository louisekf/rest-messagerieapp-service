package com.springboot.restservice.messagerieapp.service;

import com.springboot.restservice.messagerieapp.model.MailBox;
import com.springboot.restservice.messagerieapp.repository.MailBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MailBoxService {
    private MailBoxRepository mailBoxRepository;
    public MailBoxService(MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
    }

    public void createMailBox(String adresseEmail, String proprietaire, double capaciteMax) {
        MailBox mailBox = new MailBox();
        mailBox.setAdresseEmail(adresseEmail);
        mailBox.setProprietaire(proprietaire);
        mailBox.setCapaciteMax(capaciteMax);
        mailBox.setEspaceUtilise(0.0);
        mailBoxRepository.save(mailBox);
    }

    public List<MailBox> getAllMailBox() {
        return mailBoxRepository.findAll();
    }

    public Optional<MailBox> getMailBox(Long id) {
        return mailBoxRepository.findById(id);
    }

    public void deleteMailBox(Long id) {
        mailBoxRepository.deleteById(id);
    }

    public double consulterEspaceUtilise(Long id) {
        Optional<MailBox> mailBox = mailBoxRepository.findById(id);
        if (mailBox.isPresent()) {
            return mailBox.get().getEspaceUtilise();
        }
        return 0;
    }
}
