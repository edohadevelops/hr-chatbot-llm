package edu.missouristate.csc615.chatbot.llm.service;
public interface ChatService {
    /**
     * Send a message and get a response
     * @param message User message
     * @return AI response
     */
    String chat(String message);

    /**
     * Send a message with conversation history
     * @param message User message
     * @param conversationId Conversation identifier
     * @return AI response
     */
    String chatWithHistory(String message, String conversationId);
}