

package com.github.superai.dating;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;



@Service
public class ChatReplyAssistant {

	private final ChatClient chatClient;

	public ChatReplyAssistant(ChatClient.Builder modelBuilder) {

		// @formatter:off
		this.chatClient = modelBuilder
				.defaultSystem("""
						You are a customer support representative at Simple Date. Help draft responses in a friendly and respectful manner.
					""")
				//.defaultFunctions("getBookingDetails", "changeBooking", "cancelBooking") // FUNCTION CALLING
				.build();
		// @formatter:on
	}

	public Flux<String> chat(String userMessageContent) {

		return this.chatClient.prompt()
				.user(userMessageContent)
				.stream().content();
	}
}