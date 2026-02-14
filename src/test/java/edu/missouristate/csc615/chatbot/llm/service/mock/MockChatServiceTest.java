package edu.missouristate.csc615.chatbot.llm.service.mock;
import edu.missouristate.csc615.chatbot.llm.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MockChatServiceTest {
    private ChatService chatService;
    @BeforeEach
    void setUp() {
        chatService = new MockChatService();
    }
    @Test
    void testBenefitsQuestion() {
        String response = chatService.chat("What benefits does the company offer?");
        assertNotNull(response);
        assertTrue(response.contains("health insurance"));
        assertTrue(response.contains("401(k)"));
    }
    @Test
    void testPtoQuestion() {
        String response = chatService.chat("How do I request time off?");
        assertNotNull(response);
        assertTrue(response.contains("PTO"));
        assertTrue(response.contains("HR portal"));
    }

    @Test
    void testRemoteWorkQuestion() {
        String response = chatService.chat("Can I work from home?");
        assertNotNull(response);
        assertTrue(response.contains("remote work policy"));
        assertTrue(response.contains("2 days per week"));
    }

    @Test
    void testGenericQuestion() {
        String response = chatService.chat("Hello");
        assertNotNull(response);
        assertTrue(response.contains("help with HR-related questions"));
    }

    @Test
    void testChatWithHistory() {
        String response = chatService.chatWithHistory("What about PTO?", "conv-123");
        assertNotNull(response);
        // Currently same as chat() - will be enhanced by Duy
    }
}