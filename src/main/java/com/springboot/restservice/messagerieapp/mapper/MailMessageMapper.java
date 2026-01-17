package com.springboot.restservice.messagerieapp.mapper;

import com.springboot.restservice.messagerieapp.dto.MailMessageDTO;
import com.springboot.restservice.messagerieapp.model.MailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailMessageMapper {
    public MailMessageDTO toDTO(MailMessage mailMessage) {
        if (mailMessage == null) {
            return null;
        }
        return new MailMessageDTO(
                mailMessage.getId(),
                mailMessage.getExpediteur(),
                mailMessage.getDestinataire(),
                mailMessage.getObjet(),
                mailMessage.getContenu(),
                mailMessage.getDateEnvoi(),
                mailMessage.getLu()
                );
    }
    public MailMessage toEntity(MailMessageDTO dto) {
        if (dto == null) {
            return null;
        }
        MailMessage mailMessage = new MailMessage();
        mailMessage.setId(dto.getId());
        mailMessage.setExpediteur(dto.getExpediteur());
        mailMessage.setDestinataire(dto.getDestinataire());
        mailMessage.setObjet(dto.getObjet());
        mailMessage.setContenu(dto.getContenu());
        if (dto.getDateEnvoi() != null) {
            mailMessage.setDateEnvoi(dto.getDateEnvoi());
        }
        if (dto.getLu() == null) {
            mailMessage.setLu(false);
        } else {
            mailMessage.setLu(dto.getLu());
        }

        return mailMessage;
    }

}
