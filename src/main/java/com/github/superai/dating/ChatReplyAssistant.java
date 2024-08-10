

package com.github.superai.dating;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ChatReplyAssistant {

	private final ChatClient chatClient;

	@Value("classpath:/prompts/response.st")
	private Resource responsePrompt;

	public ChatReplyAssistant(ChatClient.Builder modelBuilder) {


		this.chatClient = modelBuilder.build();

	}

	public String chat(String userMessageContent) {

		PromptTemplate promptTemplate = new PromptTemplate(responsePrompt);
		Map<String, Object> promptParameters = new HashMap<>();
		promptParameters.put("chatMessage", userMessageContent);

		String value =  chatClient.call(promptTemplate.create(promptParameters))
				.getResult()
				.getOutput()
				.getContent();

		log.info("ChatReplyAssistant chat result: {}", value);

		return value;
	}
}