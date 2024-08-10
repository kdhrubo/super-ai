package com.github.superai.dating;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@Slf4j
@RequiredArgsConstructor
public class ChatReplyRestController {

    private final ChatReplyAssistant chatReplyAssistant;

    @GetMapping("/chatReply")
    public ChatReply faq(@RequestParam(value = "message") String message) {
        log.info("message = {}", message);
        String sentiment =
                chatReplyAssistant.chat(message);
        log.info("sentiment = {}", sentiment);
        return new ChatReply(UUID.randomUUID().toString(), sentiment);
    }
}
