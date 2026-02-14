# HR Chatbot LLM Service - Usage Guide

## Project Setup

1. Clone or access the project:
   ```
   hr-chatbot-llm
   ```

2. Ensure the following are installed:
    - Java 17 or higher
    - Maven 3.8 or higher

3. Build the project:
   ```bash
   mvn clean install
   ```

---

## Project Structure

```
src/main/java/edu/missouristate/csc615/chatbot/llm/
```

- **config** – Configuration constants
- **service** – Chat service interfaces
- **service.mock** – Mock implementations for testing
- **prompt** – Prompt templates (Samiha's work)
- **conversation** – Conversation management (Duy's work)
- **model** – Data models

---

## How to Use Chat Service

### Import Required Classes

```java
import edu.missouristate.csc615.chatbot.llm.service.ChatService;
import edu.missouristate.csc615.chatbot.llm.service.mock.MockChatService;
```

### Create Chat Service

```java
ChatService chatService = new MockChatService();
```

### Send Message

```java
String response = chatService.chat("What benefits do we have?");
System.out.println(response);
```

---

## Running the Demo

```bash
mvn exec:java -Dexec.mainClass="edu.missouristate.csc615.chatbot.llm.Main"
```

Expected Output:

```
=== HR Chatbot LLM Service - Week 5 Demo ===
...
=== Demo Complete ===
```

---

## Running Tests

```bash
mvn test
```

Expected Output:

```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Next Steps (Week 6)

- Replace `MockChatService` with `OllamaChatService`
- Integrate Samiha's prompt templates
- Add Duy's conversation history management
- Connect to real Ollama API