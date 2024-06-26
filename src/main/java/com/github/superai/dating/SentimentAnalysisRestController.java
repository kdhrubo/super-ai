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
public class SentimentAnalysisRestController {

    private final SentimentAnalysisAssistant sentimentAnalysisAssistant;

    @GetMapping("/sentiments")
    public Sentiment faq(@RequestParam(value = "message") String message) {
        log.info("Meesage = {}", message);
        String senti =
        sentimentAnalysisAssistant.chat(message);

        return new Sentiment(UUID.randomUUID().toString(), senti);
    }
}
