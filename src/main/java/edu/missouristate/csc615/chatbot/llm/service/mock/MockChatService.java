package edu.missouristate.csc615.chatbot.llm.service.mock;
import edu.missouristate.csc615.chatbot.llm.service.ChatService; import
        java.util.HashMap; import java.util.Map;
public class MockChatService implements ChatService {
    private static final Map<String, String> CANNED_RESPONSES = new HashMap<>();

    static {
        CANNED_RESPONSES.put("benefits",
                "Our company offers comprehensive health insurance, 401(k) matching up to 6%, " +
                        "and 15 days of PTO per year. Would you like details on any specific benefit?");

        CANNED_RESPONSES.put("pto",
                "You accrue 15 days of PTO per year. To request time off, please submit a request " +
                        "through the HR portal at least 2 weeks in advance.");

        CANNED_RESPONSES.put("remote", "Our remote work policy allows up to 2 days per week of remote work. Please coordinate " +
                "with your manager and ensure you're available during core hours (9 AM - 3 PM).");

        CANNED_RESPONSES.put("default",
                "I'm here to help with HR-related questions about benefits, policies, and procedures. "
                        +
                        "Could you please provide more details about what you need?");
    }

    @Override
    public String chat(String message) {
        // Simple keyword matching for mock responses
        String lowerMessage = message.toLowerCase();

        if (lowerMessage.contains("benefit") || lowerMessage.contains("insurance")) {
            return CANNED_RESPONSES.get("benefits");
        } else if (lowerMessage.contains("pto") || lowerMessage.contains("vacation") ||
                lowerMessage.contains("time off")) {
            return CANNED_RESPONSES.get("pto");
        } else if (lowerMessage.contains("remote") || lowerMessage.contains("work from home"))
        {
            return CANNED_RESPONSES.get("remote");
        } else {
            return CANNED_RESPONSES.get("default");
        }
    }

    @Override
    public String chatWithHistory(String message, String conversationId) {
        // For now, just call chat() - Duy will enhance with real history
        return chat(message);
    }
}