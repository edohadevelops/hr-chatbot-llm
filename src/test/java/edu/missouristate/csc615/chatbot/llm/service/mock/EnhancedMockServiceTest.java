package edu.missouristate.csc615.chatbot.llm.service.mock;

import edu.missouristate.csc615.chatbot.llm.service.ChatService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EnhancedMockServiceTest {

    private ChatService chatService;

    @BeforeEach
    void setUp() {
        chatService = new MockChatService();
    }

    @Test
    @Order(1)
    @DisplayName("Test health insurance question returns appropriate response")
    void testHealthInsuranceQuestion() {
        String response = chatService.chat("What health insurance options does the university offer?");
        assertNotNull(response, "Response should not be null");
        assertFalse(response.isEmpty(), "Response should not be empty");
        assertTrue(response.toLowerCase().contains("insurance") ||
                        response.toLowerCase().contains("health") ||
                        response.toLowerCase().contains("coverage"),
                "Response should mention insurance/health/coverage");
        assertTrue(response.length() > 100, "Response should be detailed (>100 chars)");
    }

    @Test
    @Order(2)
    @DisplayName("Test 401k question returns retirement information")
    void test401kQuestion() {
        String response = chatService.chat("Tell me about the 401k plan");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("retirement") ||
                        response.toLowerCase().contains("403") ||
                        response.toLowerCase().contains("match"),
                "Response should mention retirement/403b/match");
    }

    @Test
    @Order(3)
    @DisplayName("Test PTO question returns time off information")
    void testPtoQuestion() {
        String response = chatService.chat("How do I request time off?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("pto") ||
                        response.toLowerCase().contains("time off") ||
                        response.toLowerCase().contains("vacation"),
                "Response should mention PTO/time off/vacation");
    }

    @Test
    @Order(4)
    @DisplayName("Test remote work question returns policy information")
    void testRemoteWorkQuestion() {
        String response = chatService.chat("Can I work from home?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("remote") ||
                        response.toLowerCase().contains("work from home") ||
                        response.toLowerCase().contains("manager"),
                "Response should mention remote work policy");
    }

    @Test
    @Order(5)
    @DisplayName("Test dress code question returns attire guidelines")
    void testDressCodeQuestion() {
        String response = chatService.chat("What should I wear to work?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("dress") ||
                        response.toLowerCase().contains("casual") ||
                        response.toLowerCase().contains("attire"),
                "Response should mention dress code/casual/attire");
    }

    @Test
    @Order(6)
    @DisplayName("Test holiday question returns schedule information")
    void testHolidayQuestion() {
        String response = chatService.chat("When is the office closed for holidays?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("holiday") ||
                        response.toLowerCase().contains("closed") ||
                        response.toLowerCase().contains("schedule"),
                "Response should mention holidays/closed/schedule");
    }

    @Test
    @Order(7)
    @DisplayName("Test onboarding question returns first day information")
    void testOnboardingQuestion() {
        String response = chatService.chat("What happens on my first day?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("first day") ||
                        response.toLowerCase().contains("onboarding") ||
                        response.toLowerCase().contains("orientation") ||
                        response.toLowerCase().contains("hr"),
                "Response should mention first day/onboarding/orientation");
    }

    @Test
    @Order(8)
    @DisplayName("Test training question returns professional development information")
    void testTrainingQuestion() {
        String response = chatService.chat("Are there any training opportunities?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("training") ||
                        response.toLowerCase().contains("development") ||
                        response.toLowerCase().contains("course") ||
                        response.toLowerCase().contains("learning"),
                "Response should mention training/development/courses");
    }

    @Test
    @Order(9)
    @DisplayName("Test performance review question returns evaluation information")
    void testPerformanceReviewQuestion() {
        String response = chatService.chat("How do performance reviews work?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("performance") ||
                        response.toLowerCase().contains("review") ||
                        response.toLowerCase().contains("evaluation"),
                "Response should mention performance/review/evaluation");
    }

    @Test
    @Order(10)
    @DisplayName("Test personal data request redirects appropriately")
    void testPersonalDataRequest() {
        String response = chatService.chat("Can you update my personal information?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("hr") ||
                        response.toLowerCase().contains("contact") ||
                        response.toLowerCase().contains("portal"),
                "Response should redirect to HR/portal");
        assertTrue(response.toLowerCase().contains("don't have access") ||
                        response.toLowerCase().contains("cannot") ||
                        response.toLowerCase().contains("privacy"),
                "Response should explain limitation");
    }

    @Test
    @Order(11)
    @DisplayName("Test policy exception request redirects to HR")
    void testPolicyExceptionRequest() {
        String response = chatService.chat("Can you make an exception to the policy for me?");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("hr") ||
                        response.toLowerCase().contains("contact") ||
                        response.toLowerCase().contains("directly"),
                "Response should redirect to HR");
        assertTrue(response.toLowerCase().contains("cannot") ||
                        response.toLowerCase().contains("exception"),
                "Response should explain limitation");
    }

    @Test
    @Order(12)
    @DisplayName("Test vague question requests clarification")
    void testVagueQuestion() {
        String response = chatService.chat("Hi");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("help") ||
                        response.toLowerCase().contains("assist") ||
                        response.toLowerCase().contains("information"),
                "Response should offer help or ask for clarification");
    }

    @Test
    @Order(13)
    @DisplayName("Test response time is realistic")
    void testResponseTime() {
        long startTime = System.currentTimeMillis();
        chatService.chat("What benefits do we have?");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        assertTrue(duration >= 100, "Response should take at least 100ms");
        assertTrue(duration <= 600, "Response should take no more than 600ms");
    }

    @Test
    @Order(14)
    @DisplayName("Test response variability")
    void testResponseVariability() {
        // Ask same question multiple times
        String response1 = chatService.chat("What health insurance do we have?");
        String response2 = chatService.chat("What health insurance do we have?");
        String response3 = chatService.chat("What health insurance do we have?");
        // At least one should be different (due to randomization)
        // Note: Small chance all 3 are same if only 2 responses in bank
        boolean hasVariation = !response1.equals(response2) ||
                !response2.equals(response3) ||
                !response1.equals(response3);
        assertTrue(hasVariation, "Multiple responses should show some variability");
    }

    @Test
    @Order(15)
    @DisplayName("Test chat with history calls underlying chat method")
    void testChatWithHistory() {
        String response = chatService.chatWithHistory("What is PTO?", "conv-123");
        assertNotNull(response);
        assertTrue(response.toLowerCase().contains("pto") ||
                        response.toLowerCase().contains("time off"),
                "Response should handle question even with conversation ID");
    }
}