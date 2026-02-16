package edu.missouristate.csc615.chatbot.llm;

import edu.missouristate.csc615.chatbot.llm.service.ChatService;
import edu.missouristate.csc615.chatbot.llm.service.mock.MockChatService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Enhanced Mock Service Test ===\n");
        ChatService chatService = new MockChatService();

        // Test various categories
        testQuestion(chatService, "What health insurance options do we have?");
        testQuestion(chatService, "Tell me about the 401k plan");
        testQuestion(chatService, "How do I request PTO?");
        testQuestion(chatService, "Can I work remotely?");
        testQuestion(chatService, "What's the dress code?");
        testQuestion(chatService, "When are university holidays?");
        testQuestion(chatService, "What happens on my first day?");
        testQuestion(chatService, "Are there training opportunities?");
        testQuestion(chatService, "How do performance reviews work?");
        testQuestion(chatService, "Can you update my personal information?");
        testQuestion(chatService, "Can you make an exception to the policy?");
        testQuestion(chatService, "Hi there");

        System.out.println("=== Test Complete ===");
    }

    private static void testQuestion(ChatService service, String question) {
        System.out.println("User: " + question);
        long startTime = System.currentTimeMillis();
        String response = service.chat(question);
        long endTime = System.currentTimeMillis();
        System.out.println("Assistant: " + response);
        System.out.println("Response time: " + (endTime - startTime) + "ms\n");
    }
}