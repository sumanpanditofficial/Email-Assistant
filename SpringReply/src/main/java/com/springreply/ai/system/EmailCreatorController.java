package com.springreply.ai.system;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/email")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmailCreatorController {

    private final EmailCreatorService emailCreatorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest) {
        String response = emailCreatorService.emailReply(emailRequest);
        return ResponseEntity.ok(response);
    }

}
