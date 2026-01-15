package com.springboot.restservice.messagerieapp.service;

import com.springboot.restservice.messagerieapp.model.MailMessage;
import com.springboot.restservice.messagerieapp.repository.MailMessageRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MailMessageService {
    private MailMessageRepository mailMessageRepository;
    public MailMessageService(MailMessageRepository mailMessageRepository) {
        this.mailMessageRepository = mailMessageRepository;
    }

    private void envoyerMessage(String expediteur, String destinataire, String objet, String contenu){
        MailMessage mailMessage = new MailMessage();
        mailMessage.setExpediteur(expediteur);
        mailMessage.setDestinataire(destinataire);
        mailMessage.setObjet(objet);
        mailMessage.setContenu(contenu);
        mailMessage.setDateEnvoi(Date.valueOf(LocalDate.now()));
        mailMessage.setLu(false);
        mailMessageRepository.save(mailMessage);
    }

    private List<MailMessage> getMailMessages(){
        return mailMessageRepository.findAll();
    }

    private MailMessage getMailMessage(Long id){
        return mailMessageRepository.findById(id).orElse(null);
    }

    private void marquerCommeLu(Long id){
        Optional<MailMessage> mailMessage = mailMessageRepository.findById(id);
        if(mailMessage.isPresent()){
            mailMessage.get().setLu(true);
        }
    }

    private void deleteMailMessage(Long id){
        mailMessageRepository.deleteById(id);
    }
}


