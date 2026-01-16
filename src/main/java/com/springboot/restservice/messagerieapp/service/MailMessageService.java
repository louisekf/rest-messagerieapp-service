package com.springboot.restservice.messagerieapp.service;

import com.springboot.restservice.messagerieapp.dto.MailMessageDTO;
import com.springboot.restservice.messagerieapp.exception.MailMessageNotFoundException;
import com.springboot.restservice.messagerieapp.exception.QuotaExceededException;
import com.springboot.restservice.messagerieapp.mapper.MailMessageMapper;
import com.springboot.restservice.messagerieapp.model.MailBox;
import com.springboot.restservice.messagerieapp.model.MailMessage;
import com.springboot.restservice.messagerieapp.repository.MailMessageRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MailMessageService {
    private final MailMessageRepository mailMessageRepository;
    private final MailMessageMapper messageMapper;
    private final MailBoxService mailboxService;

    public MailMessageService(MailMessageRepository mailMessageRepository, MailMessageMapper messageMapper, MailBoxService mailboxService) {
        this.mailMessageRepository = mailMessageRepository;
        this.messageMapper = messageMapper;
        this.mailboxService = mailboxService;
    }


    public MailMessageDTO sendMessage(MailMessageDTO messageDTO) {
        // Récupérer la boîte mail du destinataire
        MailBox destinataireMailbox = mailboxService.getMailboxEntityByEmail(
                messageDTO.getDestinataire()
        );

        // Créer le message
        MailMessage message = messageMapper.toEntity(messageDTO);

        // Vérifier le quota
        double messageTaille = message.getTailleEnMo();
        if (!mailboxService.canAddMessage(destinataireMailbox.getId(), messageTaille)) {
            throw new QuotaExceededException(
                    messageDTO.getDestinataire(),
                    messageTaille,
                    destinataireMailbox.getCapaciteMax() - destinataireMailbox.getEspaceUtilise()
            );
        }

        // Associer le message à la boîte mail
        message.setMailBox(destinataireMailbox);

        // Sauvegarder le message
        MailMessage savedMessage = mailMessageRepository.save(message);

        // Mettre à jour l'espace utilisé
        mailboxService.updateEspaceUtilise(destinataireMailbox.getId(), messageTaille);

        return messageMapper.toDTO(savedMessage);
    }

    public List<MailMessageDTO> getMessagesByMailboxId(Long mailboxId) {
        // Vérifier que la boîte mail existe
        mailboxService.getMailBox(mailboxId);

        return mailMessageRepository.findByMailboxIdOrderByDateEnvoiDesc(mailboxId)
                .stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MailMessageDTO getMessageById(Long id) {
        MailMessage message = mailMessageRepository.findById(id)
                .orElseThrow(() -> new MailMessageNotFoundException(id));
        return messageMapper.toDTO(message);
    }

    public MailMessageDTO markAsRead(Long id) {
        MailMessage message = mailMessageRepository.findById(id)
                .orElseThrow(() -> new MailMessageNotFoundException(id));

        message.setLu(true);
        MailMessage updatedMessage = mailMessageRepository.save(message);

        return messageMapper.toDTO(updatedMessage);
    }

    public void deleteMessage(Long id) {
        MailMessage message = mailMessageRepository.findById(id)
                .orElseThrow(() -> new MailMessageNotFoundException(id));

        double messageTaille = message.getTailleEnMo();
        Long mailboxId = message.getMailbox().getId();

        mailMessageRepository.deleteById(id);

        // Mettre à jour l'espace utilisé (en enlevant la taille du message)
        mailboxService.updateEspaceUtilise(mailboxId, -messageTaille);
    }

    private void marquerCommeLu(Long id){
        Optional<MailMessage> mailMessage = mailMessageRepository.findById(id);
        if(mailMessage.isPresent()){
            mailMessage.get().setLu(true);
        }
    }
    public long countUnreadMessages(Long mailboxId) {
        return mailMessageRepository.countByMailboxIdAndLuFalse(mailboxId);
    }
}


