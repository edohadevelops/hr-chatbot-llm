package edu.missouristate.csc615.chatbot.llm.service.mock;

import edu.missouristate.csc615.chatbot.llm.service.ChatService;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MockChatService implements ChatService {
    private static final Map<String, List<String>> RESPONSE_BANK = new HashMap<>();

    static {
        // Health Insurance responses
        RESPONSE_BANK.put("health_insurance", Arrays.asList(
                "Missouri State University offers comprehensive health insurance through Blue Cross Blue Shield. " +
                        "Coverage includes medical, dental, and vision benefits. Full-time employees are eligible, " +
                        "and coverage begins on the first day of the month following your hire date. " +
                        "For detailed plan options and costs, please visit the HR portal at hr.missouristate.edu/benefits " +
                        "or contact HR at (417) 836-5151.",
                "Our health insurance coverage includes several plan options with varying deductibles and " +
                        "co-pays. All plans cover preventive care at 100%. You can add dependents to your coverage. " +
                        "During open enrollment (typically in November), you can change your plan. " +
                        "For specific questions about coverage or claims, contact Blue Cross Blue Shield directly " +
                        "at the number on your insurance card, or reach out to HR for enrollment assistance."
        ));

        // 401k responses
        RESPONSE_BANK.put("401k", Arrays.asList(
                "Missouri State University offers a 403(b) retirement savings plan with employer matching. " +
                        "The university matches 100% of your contributions up to 6% of your salary. " +
                        "You can contribute up to the IRS annual limit. Vesting is immediate for your contributions " +
                        "and employer match. To enroll or change your contribution percentage, log into the " +
                        "retirement portal at retirement.missouristate.edu or contact HR.",
                "Our retirement plan matches your contributions dollar-for-dollar up to 6% of your salary. " +
                        "For example, if you contribute 6%, the university contributes an additional 6%, giving you " +
                        "a total of 12% going into your retirement account. You can adjust your contribution " +
                        "percentage at any time. I recommend contributing at least 6% to maximize the employer match."
        ));

        // PTO responses
        RESPONSE_BANK.put("pto", Arrays.asList(
                "Full-time staff employees accrue 15 days of paid time off (PTO) per year, which increases " +
                        "with years of service. PTO accrues each pay period. To request time off, submit a request " +
                        "through the HR portal at least 2 weeks in advance for your manager's approval. " +
                        "For urgent situations, contact your manager directly. Unused PTO up to 20 days rolls over " +
                        "to the next year.",
                "You earn PTO based on your employment status and length of service. Full-time employees " +
                        "start at 15 days per year. To check your current PTO balance, log into the HR portal " +
                        "under 'My Time Off'. When requesting time off, provide as much advance notice as possible " +
                        "to help your team plan. Emergency time off requests should be communicated to your manager immediately."
        ));

        // Remote work policy responses
        RESPONSE_BANK.put("remote_work", Arrays.asList(
                "Missouri State University's remote work policy allows eligible employees to work remotely " +
                        "up to 2 days per week, subject to manager approval and department needs. You must be " +
                        "available during core business hours (9 AM - 3 PM) and maintain productivity standards. " +
                        "To request a remote work arrangement, discuss with your manager and submit a remote work " +
                        "agreement form through the HR portal. Not all positions are eligible for remote work.",
                "Remote work eligibility depends on your role and department needs. If your position is " +
                        "eligible, you'll need manager approval and a signed remote work agreement. The typical " +
                        "arrangement is up to 2 days per week remote. You're expected to maintain the same " +
                        "productivity and be responsive during work hours. Equipment needs should be discussed " +
                        "with your manager. Contact your supervisor to explore if remote work is an option for you."
        ));

        // Dress code responses
        RESPONSE_BANK.put("dress_code", Arrays.asList(
                "Missouri State University maintains a business casual dress code for most staff positions. " +
                        "This means clean, professional attire appropriate for an educational environment. " +
                        "Jeans are acceptable if neat and without holes. Avoid clothing with offensive graphics. " +
                        "Some departments may have specific requirements - check with your supervisor. " +
                        "On Fridays, casual attire is generally acceptable. When meeting with external partners, " +
                        "business professional attire is recommended.",
                "Our dress code is business casual. Acceptable attire includes slacks, khakis, dress shirts, " +
                        "blouses, and neat jeans. Avoid shorts, tank tops, flip-flops, or overly casual clothing. " +
                        "If you interact regularly with students or external partners, lean toward more professional " +
                        "attire. Some offices observe 'casual Fridays' - check your department's practice. " +
                        "When in doubt, it's better to be slightly overdressed than underdressed."
        ));

        // Holiday schedule responses
        RESPONSE_BANK.put("holidays", Arrays.asList(
                "The university observes the following paid holidays: New Year's Day, Martin Luther King Jr. Day, " +
                        "Memorial Day, Independence Day, Labor Day, Thanksgiving (Thursday and Friday), and " +
                        "Christmas Eve through New Year's Day (winter break). The exact dates are published each year " +
                        "in the academic calendar. The university is closed on these days. For the current year's " +
                        "specific dates, check the HR portal or the academic calendar at calendar.missouristate.edu.",
                "We have 11-12 paid holidays per year, including major federal holidays and a winter break " +
                        "from approximately December 24 through January 1. The complete holiday schedule is published " +
                        "at the start of each academic year. If a holiday falls on a weekend, the university typically " +
                        "observes it on the nearest weekday. Department-specific coverage during holidays should be " +
                        "coordinated with your supervisor."
        ));

        // Onboarding responses
        RESPONSE_BANK.put("onboarding", Arrays.asList(
                "Welcome to Missouri State University! On your first day, report to the HR office in Carrington " +
                        "Hall at 8:30 AM to complete paperwork (I-9, W-4, benefits enrollment). Bring your driver's license " +
                        "and Social Security card or passport. After HR, you'll meet with your department for orientation, " +
                        "receive your ID badge, and get system access set up. The full onboarding process takes 1-2 days. " +
                        "Check your email for a welcome message with additional details.",
                "First day agenda: Start at HR (Carrington Hall, 8:30 AM) for paperwork and benefits enrollment. " +
                        "This takes about 2 hours. Required documents: government ID and proof of work eligibility. " +
                        "Then you'll meet your department team, get a tour, and set up your workspace. IT will help with " +
                        "computer setup and system access. You'll receive your employee ID, parking pass information, " +
                        "and a new employee handbook. Your manager will review department-specific procedures."
        ));

        // Training responses
        RESPONSE_BANK.put("training", Arrays.asList(
                "Missouri State offers numerous professional development opportunities. LinkedIn Learning is " +
                        "free for all employees with hundreds of courses. The Office of Professional Development offers " +
                        "workshops on leadership, technology, and soft skills. Many departments have training budgets for " +
                        "conferences or certifications - discuss with your manager. Check the professional development " +
                        "portal at profdev.missouristate.edu for upcoming offerings and to register for courses.",
                "Professional development options include: LinkedIn Learning (free access with unlimited courses), " +
                        "on-campus workshops through the Office of Professional Development, department-specific training, " +
                        "and conference attendance (budget permitting). Annual performance reviews are a good time to " +
                        "discuss training goals with your manager. Some positions may have required compliance training - " +
                        "you'll be notified via email if any is due."
        ));

        // Performance review responses
        RESPONSE_BANK.put("performance_review", Arrays.asList(
                "Annual performance reviews typically occur in May/June. Your manager will schedule a meeting " +
                        "to discuss your accomplishments, goals, and development areas. Before the review, you'll complete " +
                        "a self-assessment form documenting your achievements and goals. The review includes a formal " +
                        "written evaluation and rating. Merit increases, if budgeted, are typically effective July 1. " +
                        "Prepare by documenting your accomplishments throughout the year.",
                "Performance reviews happen annually, usually in late spring. The process includes a self-evaluation, " +
                        "manager evaluation, and review meeting. This is your opportunity to discuss accomplishments, " +
                        "challenges, career goals, and professional development interests. Come prepared with specific " +
                        "examples of your contributions. The review results may impact merit increases (if funded) and " +
                        "inform training and development plans. Your manager will provide the timeline and evaluation forms."
        ));

        // Out of scope - personal data request
        RESPONSE_BANK.put("personal_data", Arrays.asList(
                "I don't have access to personal employee records or data for privacy and security reasons. " +
                        "To request your personal information, update your records, or access your employee file, " +
                        "please log into the HR portal at hr.missouristate.edu or contact HR directly at " +
                        "hr@missouristate.edu or (417) 836-5151. They can securely assist with personal data requests."
        ));

        // Out of scope - policy exceptions
        RESPONSE_BANK.put("policy_exception", Arrays.asList(
                "I can provide information about standard policies, but I cannot make exceptions or special " +
                        "accommodations to policies. For requests that fall outside standard policy, please contact " +
                        "HR directly at hr@missouristate.edu or (417) 836-5151. They can review your specific situation " +
                        "and determine if accommodations are possible."
        ));

        // Clarification request
        RESPONSE_BANK.put("clarification", Arrays.asList(
                "I want to make sure I give you the most helpful information. Could you provide a bit more detail " +
                        "about what you're looking for? For example, are you asking about benefits, policies, procedures, " +
                        "or something else? The more specific you can be, the better I can assist you.",
                "I'm not quite sure I understand your question. Could you rephrase it or provide more context? " +
                        "For example, if you're asking about time off, are you interested in PTO policies, how to request " +
                        "time off, or checking your balance? Let me know and I'll do my best to help!"
        ));

        // Default/general response
        RESPONSE_BANK.put("default", Arrays.asList(
                "I'm here to help with HR-related questions about benefits, policies, and procedures. " +
                        "I can assist with topics like health insurance, retirement plans, paid time off, remote work, " +
                        "onboarding, training opportunities, and more. What would you like to know?",
                "I can help you with information about Missouri State University's HR policies and benefits. " +
                        "Common topics include: insurance and benefits, retirement plans, time off policies, remote work, " +
                        "onboarding procedures, professional development, and performance reviews. What specific " +
                        "information are you looking for?"
        ));
    }

    // Keyword to category mapping
    private static final Map<String, String> KEYWORD_MAP = new HashMap<>();

    static {
        // Health insurance keywords
        KEYWORD_MAP.put("insurance", "health_insurance");
        KEYWORD_MAP.put("health", "health_insurance");
        KEYWORD_MAP.put("medical", "health_insurance");
        KEYWORD_MAP.put("coverage", "health_insurance");
        KEYWORD_MAP.put("blue cross", "health_insurance");
        KEYWORD_MAP.put("dental", "health_insurance");
        KEYWORD_MAP.put("vision", "health_insurance");

        // 401k keywords
        KEYWORD_MAP.put("401k", "401k");
        KEYWORD_MAP.put("403b", "401k");
        KEYWORD_MAP.put("retirement", "401k");
        KEYWORD_MAP.put("pension", "401k");
        KEYWORD_MAP.put("savings", "401k");
        KEYWORD_MAP.put("match", "401k");

        // PTO keywords
        KEYWORD_MAP.put("pto", "pto");
        KEYWORD_MAP.put("vacation", "pto");
        KEYWORD_MAP.put("time off", "pto");
        KEYWORD_MAP.put("leave", "pto");
        KEYWORD_MAP.put("paid time", "pto");

        // Remote work keywords
        KEYWORD_MAP.put("remote", "remote_work");
        KEYWORD_MAP.put("work from home", "remote_work");
        KEYWORD_MAP.put("wfh", "remote_work");
        KEYWORD_MAP.put("telecommute", "remote_work");

        // Dress code keywords
        KEYWORD_MAP.put("dress code", "dress_code");
        KEYWORD_MAP.put("attire", "dress_code");
        KEYWORD_MAP.put("clothing", "dress_code");
        KEYWORD_MAP.put("casual", "dress_code");
        KEYWORD_MAP.put("wear", "dress_code");

        // Holiday keywords
        KEYWORD_MAP.put("holiday", "holidays");
        KEYWORD_MAP.put("schedule", "holidays");
        KEYWORD_MAP.put("closed", "holidays");
        KEYWORD_MAP.put("office hours", "holidays");

        // Onboarding keywords
        KEYWORD_MAP.put("first day", "onboarding");
        KEYWORD_MAP.put("onboarding", "onboarding");
        KEYWORD_MAP.put("new employee", "onboarding");
        KEYWORD_MAP.put("start date", "onboarding");
        KEYWORD_MAP.put("orientation", "onboarding");

        // Training keywords
        KEYWORD_MAP.put("training", "training");
        KEYWORD_MAP.put("course", "training");
        KEYWORD_MAP.put("learning", "training");
        KEYWORD_MAP.put("development", "training");
        KEYWORD_MAP.put("workshop", "training");

        // Performance review keywords
        KEYWORD_MAP.put("performance", "performance_review");
        KEYWORD_MAP.put("review", "performance_review");
        KEYWORD_MAP.put("evaluation", "performance_review");
        KEYWORD_MAP.put("feedback", "performance_review");

        // Personal data keywords (redirect)
        KEYWORD_MAP.put("my data", "personal_data");
        KEYWORD_MAP.put("my records", "personal_data");
        KEYWORD_MAP.put("my file", "personal_data");
        KEYWORD_MAP.put("personal information", "personal_data");
        KEYWORD_MAP.put("update my", "personal_data");

        // Policy exception keywords (redirect)
        KEYWORD_MAP.put("exception", "policy_exception");
        KEYWORD_MAP.put("special request", "policy_exception");
        KEYWORD_MAP.put("can you approve", "policy_exception");
        KEYWORD_MAP.put("make an exception", "policy_exception");
    }

    @Override
    public String chat(String message) {
        String lowerMessage = message.toLowerCase();

        // Find matching category
        String category = findCategory(lowerMessage);

        // Get responses for category
        List<String> responses = RESPONSE_BANK.getOrDefault(category, RESPONSE_BANK.get("default"));

        // Return random response from category (adds variability)
        int randomIndex = ThreadLocalRandom.current().nextInt(responses.size());

        // Simulate realistic delay (100-500ms)
        simulateDelay();

        return responses.get(randomIndex);
    }

    @Override
    public String chatWithHistory(String message, String conversationId) {
        // For Week 5, just call chat() - Duy will enhance with real history
        return chat(message);
    }

    private String findCategory(String message) {
        // Check each keyword to find a match
        for (Map.Entry<String, String> entry : KEYWORD_MAP.entrySet()) {
            if (message.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // If message is very short, ask for clarification
        if (message.trim().split("\\s+").length < 3) {
            return "clarification";
        }

        // Default category
        return "default";
    }

    private void simulateDelay() {
        try {
            // Random delay between 100-500ms to simulate LLM processing
            int delay = ThreadLocalRandom.current().nextInt(100, 500);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}