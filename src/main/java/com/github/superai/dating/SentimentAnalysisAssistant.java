

package com.github.superai.dating;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class SentimentAnalysisAssistant {

	private final ChatClient chatClient;

	@Value("classpath:/prompts/sentiment.st")
	private Resource sentimentPrompt;

	public SentimentAnalysisAssistant(ChatClient.Builder modelBuilder) {

		this.chatClient = modelBuilder.build();
	}

	public String chat(String userMessageContent) {

		PromptTemplate promptTemplate = new PromptTemplate(sentimentPrompt);
		Map<String, Object> promptParameters = new HashMap<>();
		promptParameters.put("chatMessage", userMessageContent);

		String value =  chatClient.call(promptTemplate.create(promptParameters))
				.getResult()
				.getOutput()
				.getContent();

		log.info("SentimentAnalysisAssistant chat result: {}", value);

		return value;
	}
}