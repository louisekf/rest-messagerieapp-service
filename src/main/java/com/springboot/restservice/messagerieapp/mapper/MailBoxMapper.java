package com.springboot.restservice.messagerieapp.mapper;

import com.springboot.restservice.messagerieapp.dto.MailBoxDTO;
import com.springboot.restservice.messagerieapp.model.MailBox;
import org.springframework.stereotype.Component;

@Component
public class MailBoxMapper {
    public MailBoxDTO toDTO(MailBox mailBox) {
        if (mailBox == null) {
            return null;
        }
        return new MailBoxDTO(
                mailBox.getId(),
                mailBox.getAdresseEmail(),
                mailBox.getProprietaire(),
                mailBox.getCapaciteMax(),
                mailBox.getEspaceUtilise()
        );
    }
    public MailBox toEntity(MailBoxDTO dto) {
        if (dto == null) return null;

        MailBox mailbox = new MailBox();
        mailbox.setId(dto.getId());
        mailbox.setAdresseEmail(dto.getAdresseEmail());
        mailbox.setProprietaire(dto.getProprietaire());
        mailbox.setCapaciteMax(dto.getCapaciteMax());
        if (dto.getEspaceUtilise() == null) {
            mailbox.setEspaceUtilise(0.0);
        } else {
            mailbox.setEspaceUtilise(dto.getEspaceUtilise());
        }

        return mailbox;
    }

    public void updateEntityFromDTO(MailBoxDTO dto, MailBox mailbox) {
        if (dto.getAdresseEmail() != null) {
            mailbox.setAdresseEmail(dto.getAdresseEmail());
        }
        if (dto.getProprietaire() != null) {
            mailbox.setProprietaire(dto.getProprietaire());
        }
        if (dto.getCapaciteMax() != null && dto.getCapaciteMax() > 0) {
            mailbox.setCapaciteMax(dto.getCapaciteMax());
        }
    }
}
