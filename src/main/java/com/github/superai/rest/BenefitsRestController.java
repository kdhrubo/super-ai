package com.github.superai.rest;

import com.github.superai.dto.BenefitFaq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@Slf4j
@RequiredArgsConstructor
public class BenefitsRestController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/jpmc-2023-benefits.st")
    private Resource jmpcPromptResource;


    @GetMapping("/benefits")
    public BenefitFaq askBenefits(@RequestParam(value = "message") String message) {
        log.info("Meesage = {}", message);

        if(StringUtils.isBlank(message)) {
            return new BenefitFaq(UUID.randomUUID().toString(), "I don't know what to do with this");
        }

        PromptTemplate promptTemplate = new PromptTemplate(jmpcPromptResource);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));

        String value =  chatClient.call(promptTemplate.create(promptParameters))
                .getResult()
                .getOutput()
                .getContent();

        return new BenefitFaq(UUID.randomUUID().toString(), value);
    }

    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        return similarDocuments.stream().map(Document::getContent).toList();
    }
}