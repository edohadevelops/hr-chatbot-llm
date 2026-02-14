LangChain4j Overview

LangChain4j is a Java framework for building applications powered by Large Language Models (LLMs).

It provides:

A unified abstraction over different LLM providers

Chat model interfaces

Prompt templating

Memory management

Integration with providers like Ollama, OpenAI, etc.

1. How to Add LangChain4j Dependency to pom.xml

Add the following to your pom.xml:

<properties>
    <langchain4j.version>0.35.0</langchain4j.version>
</properties>

<dependencies>

    <!-- Core -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>

    <!-- Ollama Integration -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-ollama</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>

</dependencies>

After adding dependencies:

Reload Maven

Or run: mvn clean install

2. How to Create a ChatLanguageModel Instance

LangChain4j uses an abstraction called:

ChatLanguageModel

This is an interface implemented by different providers:

OllamaChatModel

OpenAiChatModel

etc.

Example: Create Ollama Chat Model
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;

ChatLanguageModel model = OllamaChatModel.builder()
.baseUrl("http://localhost:11434")
.modelName("mistral:13b")
.temperature(0.7)
.build();

Explanation:

baseUrl → Ollama server

modelName → model installed in Ollama

temperature → creativity level (0.0–1.0)

3. How to Send a Message and Get a Response

The simplest way:

String response = model.generate("What benefits does the company offer?");
System.out.println(response);

This sends a single message without structured conversation history.

4. Using SystemMessage vs UserMessage

LangChain4j supports structured chat messages.

SystemMessage

Used to define behavior/instructions for the model.

Example:

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;

SystemMessage systemMessage =
SystemMessage.from("You are an HR assistant. Only answer HR-related questions.");

UserMessage userMessage =
UserMessage.from("What is the PTO policy?");
Sending Structured Messages
import dev.langchain4j.data.message.ChatMessage;
import java.util.List;

List<ChatMessage> messages = List.of(systemMessage, userMessage);

String response = model.generate(messages);
System.out.println(response);

Difference:

SystemMessage → sets rules and context

UserMessage → represents user input

This structure is preferred in production systems.

5. How Conversation History Works

Conversation history is maintained by sending previous messages again with each request.

LLMs are stateless — they do not remember past conversations unless you provide them again.

Example:

List<ChatMessage> conversation = new ArrayList<>();

conversation.add(SystemMessage.from("You are an HR assistant."));
conversation.add(UserMessage.from("What is PTO?"));

String response1 = model.generate(conversation);
conversation.add(AiMessage.from(response1));

conversation.add(UserMessage.from("How many days do I get?"));

String response2 = model.generate(conversation);

How it works:

You maintain a list of messages

After each AI response, add it to history

Send entire message list on next request

LangChain4j also provides memory abstractions for managing this automatically (e.g., ChatMemory).

6. Chat Model Abstraction

LangChain4j provides a provider-agnostic interface:

ChatLanguageModel

Implementations include:

OllamaChatModel

OpenAiChatModel

MockChatModel (custom)

This allows:

Switching providers easily

Testing with mock models

Clean architecture separation

7. Key Concepts Summary
Concept	Purpose
ChatLanguageModel	Main abstraction for chat models
generate()	Sends prompt and receives response
SystemMessage	Sets model behavior
UserMessage	Represents user input
ChatMessage List	Maintains conversation
Temperature	Controls creativity
OllamaChatModel	Connects to Ollama server
8. Why We Use MockChatService (Week 5)

Since Ollama is not deployed yet, we use a mock implementation to:

Validate architecture

Test service integration

Avoid external dependency

Prepare for Week 6

In Week 6:

MockChatService → OllamaChatService

Without changing the rest of the codebase.