# Mock Service Design Document

## 1. Overview
This document outlines the design for the Enhanced Mock LLM Service. The service simulates a realistic HR chatbot for Missouri State University's Computer Science Department. 
It mimics the behavior of a real LLM by providing realistic delays, response variability, and context-aware answers without requiring GPU resources.

## 2. Design Principles (Based on System Prompts)
Based on `system-prompt-v1.txt` and `few-shot-examples.txt`, the mock service adheres to these principles:
* **Role:** HR Assistant (Informational only).
* **Tone:** Professional, respectful, and supportive.
* **Limitations:** clearly state inability to access personal records (PTO balances, salaries).
* **Escalation:** Direct complex or out-of-scope queries to `hr@missouristate.edu` or `(417) 836-5151`.

## 3. Response Categories
The service will support 13 distinct categories to cover the required HR scenarios:

### Benefits
1.  **Health Insurance:** Coverage details (Blue Cross Blue Shield), dental/vision, and enrollment info.
2.  **401k/Retirement:** 403(b) plan details, employer matching (6%), and vesting.
3.  **PTO:** Accrual rates (15 days/year), rollover policies, and request procedures.

### Policies
4.  **Remote Work:** Eligibility (up to 2 days/week), approval process, and core hours.
5.  **Dress Code:** Business casual guidelines and "casual Friday" notes.
6.  **Holiday Schedule:** List of observed holidays (Winter Break, Thanksgiving, etc.).

### Onboarding & Career
7.  **Onboarding:** First-day instructions (Carrington Hall, 8:30 AM), I-9 requirements.
8.  **Training:** LinkedIn Learning access and professional development funding.
9.  **Performance Reviews:** Annual timeline (May/June) and self-assessment process.

### Edge Cases & Handling
10. **Personal Data (Out of Scope):** Polite refusal to check records; redirect to HR portal.
11. **Policy Exceptions (Out of Scope):** Statement that the bot cannot approve exceptions; redirect to HR.
12. **Clarification:** Prompts used when user input is too short or vague.
13. **Default:** General greeting and menu of capabilities.

## 4. Keyword Mapping Strategy
We will use case-insensitive substring matching to map user inputs to categories.

| User Keyword | Mapped Category |
| :--- | :--- |
| `insurance`, `health`, `medical`, `dental`, `vision` | **health_insurance** |
| `401k`, `403b`, `retirement`, `pension`, `match` | **401k** |
| `pto`, `vacation`, `time off`, `leave` | **pto** |
| `remote`, `wfh`, `work from home` | **remote_work** |
| `dress code`, `attire`, `casual`, `clothing` | **dress_code** |
| `holiday`, `schedule`, `closed`, `office hours` | **holidays** |
| `onboarding`, `first day`, `orientation` | **onboarding** |
| `training`, `learning`, `course`, `development` | **training** |
| `performance`, `review`, `evaluation` | **performance_review** |
| `my data`, `my record`, `personal info` | **personal_data** (Redirect) |
| `exception`, `special request` | **policy_exception** (Redirect) |

## 5. Variability & Realism
To prevent the bot from feeling "robotic," we will implement:
1.  **Randomization:** Each category will have a `List<String>` of 2+ distinct responses. The service will randomly select one index (`ThreadLocalRandom`).
2.  **Simulated Latency:** A random delay between 100ms and 500ms will be injected before returning the response to mimic network/LLM processing time.

## 6. Integration Plan
The `MockChatService` class will implement the `ChatService` interface. It will be a drop-in replacement for the current placeholder, ensuring that when the real `OllamaChatService` is ready in Week 6, the rest of the application (UI/Controller) will not need to change.