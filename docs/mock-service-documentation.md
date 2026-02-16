# Mock LLM Service Documentation

## Overview
The Enhanced Mock Service simulates a realistic HR chatbot for Missouri State University. It mimics an LLM by providing variable, context-aware responses without requiring a GPU or external API connection. This allows the development team to test the chat interface while the real backend infrastructure is being built.

## Architecture
The service implements the `ChatService` interface. It uses a `HashMap` to store lists of canned responses mapped to specific keywords.
* **Class:** `MockChatService`
* **Package:** `edu.missouristate.csc615.chatbot.llm.service.mock`

## Response Categories
The service supports 13 distinct categories to cover required HR scenarios:

| Category | Keywords | Description |
| :--- | :--- | :--- |
| **Health Insurance** | `insurance`, `health`, `medical`, `dental` | Details on Blue Cross Blue Shield, coverage, and enrollment. |
| **401k/Retirement** | `401k`, `403b`, `retirement`, `match` | Info on 403(b) plan, 6% matching, and vesting. |
| **PTO** | `pto`, `vacation`, `time off` | Accrual rates (15 days/yr) and request procedures. |
| **Remote Work** | `remote`, `wfh` | Policy allowing up to 2 days/week with manager approval. |
| **Dress Code** | `dress code`, `attire` | Business casual guidelines. |
| **Holidays** | `holiday`, `schedule`, `closed` | List of observed holidays (Winter Break, etc.). |
| **Onboarding** | `onboarding`, `first day` | Instructions for Carrington Hall, 8:30 AM. |
| **Training** | `training`, `learning` | LinkedIn Learning and professional development info. |
| **Performance** | `performance`, `review` | Annual review cycle (May/June). |
| **Personal Data** | `my data`, `my record` | **(Out of Scope)** Polite redirect to HR portal. |
| **Policy Exceptions** | `exception` | **(Out of Scope)** Statement that bot cannot approve exceptions. |
| **Clarification** | *(Input < 3 words)* | Asks user to provide more detail. |
| **Default** | *(No match)* | General greeting and menu of capabilities. |

## Simulation Features
To ensure the mock service feels like a real AI:
1.  **Variability:** Each category has 2+ distinct response variations. The service randomly selects one using `ThreadLocalRandom`.
2.  **Latency:** A random delay of 100ms–500ms is injected before returning a response to mimic network/LLM processing time.

## Future Integration
This mock service is a placeholder. In Week 6, it will be swapped for `OllamaChatService`, which will connect to the real Mistral 13B model. 
The interface-based design ensures no changes will be needed in the Controller or UI layers.