package com.springboot.restservice.messagerieapp.controller;

import com.springboot.restservice.messagerieapp.dto.MailBoxDTO;
import com.springboot.restservice.messagerieapp.dto.MailMessageDTO;
import com.springboot.restservice.messagerieapp.dto.RestApiResponse;
import com.springboot.restservice.messagerieapp.service.MailMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MailMessageController {
    private final MailMessageService mailMessageService;
    public MailMessageController(MailMessageService mailMessageService) {
        this.mailMessageService = mailMessageService;
    }

    @PostMapping
    public ResponseEntity<RestApiResponse<MailMessageDTO>> sendMessage(
            @Validated @RequestBody MailMessageDTO messageDTO) {

        MailMessageDTO sent = mailMessageService.sendMessage(messageDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestApiResponse.ok("Mail message sent successfully", sent));
    }

    @GetMapping("/mailboxes/{id}")
    public ResponseEntity<RestApiResponse<List<MailMessageDTO>>> getMessagesByMailbox(@PathVariable Long id) {
        List<MailMessageDTO> messages = mailMessageService.getMessagesByMailboxId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok(messages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<MailMessageDTO>> readMailMessage(@PathVariable Long id) {
        MailMessageDTO mailMessage = mailMessageService.getMessageById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok(mailMessage));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<RestApiResponse<MailMessageDTO>> markMessageAsRead(@PathVariable Long id
                                                                             , @Validated @RequestBody MailMessageDTO messageDTO) {
        MailMessageDTO mailMessage = mailMessageService.markAsRead(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok(mailMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<MailMessageDTO>> deleteMailMessage(@PathVariable Long id) {
        mailMessageService.deleteMessage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok("Mail message deleted successfully", null));
    }



}
