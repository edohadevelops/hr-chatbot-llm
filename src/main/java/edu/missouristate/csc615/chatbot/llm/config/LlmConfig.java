package edu.missouristate.csc615.chatbot.llm.config;
public class LlmConfig {
    // Ollama configuration (for Week 6)
    public static final String OLLAMA_BASE_URL = "http://localhost:11434";
    public static final String MODEL_NAME = "mistral:13b";
    // Model parameters
    public static final Double TEMPERATURE = 0.7;
    public static final Integer MAX_TOKENS = 500;
    // Mock service configuration (for Week 5)
    public static final boolean USE_MOCK_SERVICE = true;
    private LlmConfig() {
// Utility class, prevent instantiation
    }
}