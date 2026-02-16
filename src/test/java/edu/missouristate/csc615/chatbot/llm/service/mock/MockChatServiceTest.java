package edu.missouristate.csc615.chatbot.llm.service.mock;

import edu.missouristate.csc615.chatbot.llm.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MockChatServiceTest {

    private ChatService chatService;

    @BeforeEach
    void setUp() {
        chatService = new MockChatService();
    }

    @Test
    @DisplayName("Test benefits question (Legacy Test)")
    void testBenefitsQuestion() {
        String response = chatService.chat("What benefits do we have?");
        assertNotNull(response);
        // Updated to expect a longer, more detailed response
        assertTrue(response.length() > 20, "Response should be detailed");
        // Check for key terms from your new Response Bank
        assertTrue(response.toLowerCase().contains("health") ||
                response.toLowerCase().contains("insurance") ||
                response.toLowerCase().contains("benefit"));
    }

    @Test
    @DisplayName("Test remote work question (Legacy Test)")
    void testRemoteWorkQuestion() {
        String response = chatService.chat("Can I work remote?");
        assertNotNull(response);
        assertTrue(response.length() > 20);
        assertTrue(response.toLowerCase().contains("remote") ||
                response.toLowerCase().contains("work"));
    }

    @Test
    @DisplayName("Test generic question (Legacy Test)")
    void testGenericQuestion() {
        String response = chatService.chat("Hello there");
        assertNotNull(response);
        // Expect either a clarification or a default response
        assertTrue(response.toLowerCase().contains("help") ||
                response.toLowerCase().contains("assist") ||
                response.toLowerCase().contains("information"));
    }
}