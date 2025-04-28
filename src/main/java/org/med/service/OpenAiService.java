package org.med.service;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OpenAiService {
    private final String aiRoleMessage = "You are an expert in medicine.";
    private final Double predictableTemperature = 0.0;
    private final SimpleOpenAI openAI = SimpleOpenAI.builder()
            .apiKey(System.getenv("OPENAI_API_KEY"))
            .build();

    public String generatePromptResponse(String prompt){
        ChatRequest chatRequest = ChatRequest.builder()
                .clearModalities()
                .model("gpt-4o-mini")
                .message(ChatMessage.SystemMessage.of(aiRoleMessage))
                .message(ChatMessage.UserMessage.of(prompt))
                .temperature(predictableTemperature)
                .maxCompletionTokens(1000)
                .build();

        return openAI.chatCompletions().create(chatRequest).join().firstContent();
    }
}
