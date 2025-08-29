package com.springreply.ai.system;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
@AllArgsConstructor
public class EmailCreatorController {

        private final EmailCreatorService emailCreatorService;

        @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest){
            String response = emailCreatorService.emailReply(emailRequest);
        return ResponseEntity.ok(response);
    }

}
