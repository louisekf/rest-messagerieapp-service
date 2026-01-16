package com.springboot.restservice.messagerieapp.controller;

import com.springboot.restservice.messagerieapp.dto.MailBoxDTO;
import com.springboot.restservice.messagerieapp.dto.RestApiResponse;
import com.springboot.restservice.messagerieapp.service.MailBoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mailboxes")
public class MailBoxController {
    private final MailBoxService mailBoxService;
    public MailBoxController(MailBoxService mailBoxService) {
        this.mailBoxService = mailBoxService;
    }

    @PostMapping
    public ResponseEntity<RestApiResponse<Void>> createMailBox(@Validated @RequestBody MailBoxDTO mailBoxDTO) {
        mailBoxService.createMailbox(mailBoxDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestApiResponse.ok("MailBox created successfully", null));
    }

    @GetMapping
    public ResponseEntity<List<MailBoxDTO>> getAllMailboxes() {
        List<MailBoxDTO> mailboxes = mailBoxService.getAllMailBox();

        return ResponseEntity.ok(mailboxes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<MailBoxDTO>> getMailboxById(@PathVariable Long id) {
        MailBoxDTO mailbox = mailBoxService.getMailBox(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok(mailbox));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> deleteMailBox(@PathVariable Long id) {
        mailBoxService.deleteMailBox(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok("Mailbox deleted successfully", null));
    }

    @GetMapping("/{id}/quota")
    public ResponseEntity<RestApiResponse<MailBoxDTO>> getQuota(@PathVariable Long id) {
        MailBoxDTO quota = mailBoxService.getQuota(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RestApiResponse.ok(quota));
    }


}
