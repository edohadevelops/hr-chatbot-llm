package edu.missouristate.csc615.chatbot.llm;
import edu.missouristate.csc615.chatbot.llm.service.ChatService; import
        edu.missouristate.csc615.chatbot.llm.service.mock.MockChatService;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== HR Chatbot LLM Service - Week 5 Demo ===\n");

        // Create mock chat service
        ChatService chatService = new MockChatService();

        // Test 1: Benefits question
        System.out.println("User: What benefits does the company offer?");
        String response1 = chatService.chat("What benefits does the company offer?");
        System.out.println("Assistant: " + response1);
        System.out.println();

        // Test 2: PTO question
        System.out.println("User: How do I request time off?");
        String response2 = chatService.chat("How do I request time off?");
        System.out.println("Assistant: " + response2);
        System.out.println();

        // Test 3: Remote work question
        System.out.println("User: Can I work from home?");
        String response3 = chatService.chat("Can I work from home?");
        System.out.println("Assistant: " + response3);
        System.out.println();

        // Test 4: Generic question
        System.out.println("User: Tell me about company policies");
        String response4 = chatService.chat("Tell me about company policies");
        System.out.println("Assistant: " + response4);
        System.out.println();

        System.out.println("=== Demo Complete ===");
    }
}