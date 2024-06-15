package com.github.superai.rest;

import com.github.superai.dto.WorldCupFaq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class WorldCupFaqRestController {

    private final ChatClient chatClient;

    public WorldCupFaqRestController(ChatClient.Builder builder,
                                     @Qualifier("simpleVectorStore")
                                     VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore,SearchRequest.defaults()))
                .build();
    }

    @GetMapping("/faq")
    public WorldCupFaq faq(@RequestParam(value = "message", defaultValue = "How many teams compete in the World Cup T20 2024?") String message) {
        log.info("Meesage = {}", message);


        String answer =  chatClient.prompt()
                .user(message)
                .call()
                .content();


        return new WorldCupFaq(UUID.randomUUID().toString(), answer);
    }

}