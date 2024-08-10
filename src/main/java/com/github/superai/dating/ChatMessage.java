package com.github.superai.dating;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String id;
    private MessageType type;
    private String content;
    private String sender;
    private String sentiment;

}