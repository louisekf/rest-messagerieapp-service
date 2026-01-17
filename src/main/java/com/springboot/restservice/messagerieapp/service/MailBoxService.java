package com.springboot.restservice.messagerieapp.service;

import com.springboot.restservice.messagerieapp.dto.MailBoxDTO;
import com.springboot.restservice.messagerieapp.dto.QuotaDTO;
import com.springboot.restservice.messagerieapp.exception.MailBoxNotFoundException;
import com.springboot.restservice.messagerieapp.mapper.MailBoxMapper;
import com.springboot.restservice.messagerieapp.model.MailBox;
import com.springboot.restservice.messagerieapp.repository.MailBoxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MailBoxService {
    private final MailBoxRepository mailBoxRepository;
    private final MailBoxMapper mailBoxMapper;


    public MailBoxService(MailBoxRepository mailBoxRepository, MailBoxMapper mailBoxMapper) {
        this.mailBoxRepository = mailBoxRepository;
        this.mailBoxMapper = mailBoxMapper;
    }

    public MailBoxDTO createMailbox(MailBoxDTO mailboxDTO) {
        // Vérifier si l'adresse email existe déjà
        if (mailBoxRepository.existsByAdresseEmail(mailboxDTO.getAdresseEmail())) {
            throw new IllegalArgumentException(
                    "Une boîte mail avec l'adresse " + mailboxDTO.getAdresseEmail() + " existe déjà"
            );
        }

        MailBox mailbox = mailBoxMapper.toEntity(mailboxDTO);
        mailbox.setEspaceUtilise(0.0);

        MailBox savedMailbox = mailBoxRepository.save(mailbox);
        return mailBoxMapper.toDTO(savedMailbox);
    }

    public List<MailBoxDTO> getAllMailBox() {
        return mailBoxRepository.findAll()
                .stream()
                .map(mailBoxMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MailBoxDTO getMailBox(Long id) {
        MailBox mailbox = mailBoxRepository.findById(id)
                .orElseThrow(() -> new MailBoxNotFoundException(id));
        return mailBoxMapper.toDTO(mailbox);

    }

    public MailBox getMailboxEntityById(Long id) {
        return mailBoxRepository.findById(id)
                .orElseThrow(() -> new MailBoxNotFoundException(id));
    }

    public MailBox getMailboxEntityByEmail(String email) {
        return mailBoxRepository.findByAdresseEmail(email)
                .orElseThrow(() -> new MailBoxNotFoundException(
                        "Boîte mail avec l'adresse " + email + " introuvable"
                ));
    }


    public void deleteMailBox(Long id) {
        if (!mailBoxRepository.existsById(id)) {
            throw new MailBoxNotFoundException(id);
        }
        mailBoxRepository.deleteById(id);
    }
/*
    public MailBoxDTO getQuota(Long id) {
        // Retourne simplement le MailboxDTO qui contient déjà toutes les infos de quota
        return getMailBox(id);
    }*/
    public QuotaDTO getQuota(Long id) {
        MailBox mailbox = getMailboxEntityById(id);
        double pourcentage = (mailbox.getEspaceUtilise() / mailbox.getCapaciteMax()) * 100;

        return new QuotaDTO(
                mailbox.getCapaciteMax(),
                mailbox.getEspaceUtilise(),
                pourcentage
        );
    }
    public boolean canAddMessage(Long mailboxId, double messageSizeInMo) {
        MailBox mailbox = getMailboxEntityById(mailboxId);
        return (mailbox.getEspaceUtilise() + messageSizeInMo) <= mailbox.getCapaciteMax();
    }

    // Méthode pour mettre à jour l'espace utilisé
    public void updateEspaceUtilise(Long mailboxId, double additionalSpace) {
        MailBox mailbox = getMailboxEntityById(mailboxId);
        mailbox.setEspaceUtilise(mailbox.getEspaceUtilise() + additionalSpace);
        mailBoxRepository.save(mailbox);
    }


}
