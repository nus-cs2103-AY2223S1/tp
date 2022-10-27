package seedu.workbook.model.internship.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.workbook.model.internship.Stage;

/**
 * Contains utility methods used in implementing methods regarding {@code Stage} for GUI.
 */
public class StageUtil {

    private static final List<Stage> stagesWithTips = Arrays.asList(
            new Stage("Application Sent"),
            new Stage("Online Assessment"),
            new Stage("Technical Interview"),
            new Stage("Behavioral Interview"),
            new Stage("Phone Interview"),
            new Stage("Rejected")
    );

    // CHECKSTYLE.OFF: LineLength
    private static final List<String> applicationSentTips = Arrays.asList(
            "Contact the hiring manager/recruitment by submitting a short follow-up email to show interest in the company and stand out!",
            "Keep a copy of your resume by the phone so that you can refer to them and answer questions if the company were to call you for a short interview!",
            "Conduct a detailed research on the organisation you applied for, especially their mission statement, policies, initiatives and programmes to prepare for a potential interview!",
            "Prepare for interviews and assessment by brushing up on your interview and coding skills! Refer to our tips included in “Online Assessment”, “Technical Interview” and “Behavioural Interview” for more advice!"
    );

    // 'Algorithm cheat sheets': https://www.techinterviewhandbook.org/algorithms/study-cheatsheet/
    // 'LeetCode': https://leetcode.com/
    private static final List<String> onlineAssessmentTips = Arrays.asList(
            "Decide on a programming language! We highly recommend Python as it has a huge library of useful functions and data structures!",
            "Remember to make a note of all the important keywords in the question!",
            "Algorithms cheat sheets - This includes the must-remembers that you should internalize for every data structure.",
            "Keep Practicing. Not sure which questions to try? Have a look at LeetCode!"
    );

    // 'STAR': https://www.themuse.com/advice/star-interview-method
    // 'online mock interviews': https://interviewing.io/?urc=DMCa
    private static final List<String> technicalInterviewTips = Arrays.asList(
            "Do not jump into coding right away. Coding questions tend to be vague and underspecified on purpose to gauge your attention to detail and carefulness. Ask at least 2-3 clarifying questions!",
            "Before starting, go through the different ways you could solve this question, discussing the time and space complexity tradeoffs!",
            "Explain what you are trying to achieve as you are coding to your interviewer, so he knows exactly what you are doing at each step!",
            "Once finished, remember to ALWAYS check that your code compiles and works for edge cases as well!"
    );

    private static final List<String> behavioralInterviewTips = Arrays.asList(
            "Mainly looking at your soft-skills and your ability to adapt and navigate about scenarios presented in the workplace.",
            "Prepare relevant-to-the-role experiences beforehand, especially if it’s from school projects because you’re still a student! ;)",
            "Try out the STAR framework to keep your answers concise and relevant to the question.",
            "Be yourself! Be natural and confident. If it helps, practice with a friend and let them evaluate you. You can also try scheduling online mock interviews to put your skills to the test."
    );

    private static final List<String> phoneInterviewTips = Arrays.asList(
            "A recruiter is looking for the following indicators during the Phone Interview: Passion for tech/coding, Enthusiasm, Communication skills, Culture fit, Alignment with company mission/values",
            "As such, focus on displaying your soft skills (Show not tell!)",
            "Radiate excitement about the company or project, and that positivity will make a good first impression on your interviewer.",
            "Be honest: What interests you the most about this job? What kinds of projects were you hoping to work on?",
            "Do your research and tailor your answers to the exact position and company you’re interviewing for.",
            "Look for opportunities to show your value alignment. Show that you support the mission of the organization."
    );

    private static final List<String> rejectedTips = Arrays.asList(
            "Acknowledge your emotions and feelings about the rejection. Don’t worry, it is perfectly normal to feel this way but don’t give up!",
            "Remind yourself that you are competent. Getting rejected does not mean you are not qualified, it could just mean that there was an applicant more suitable for the particular job! Capitalise on the skills and expertise that are unique to yourself!",
            "Express gratitude by thanking the company for the opportunity and taking the time to know you better! If you are interested in applying to the company in the future, do also maintain a good impression and let them know about it! :)",
            "Ask for feedback from the company so that you can do better the next time! It’ll be good to find out what went well, what could be improved on and how to go about improving them!",
            "Most importantly, keep trying and move on to the next opportunity, they are plenty awaiting you! Sharpen, refine your skills before applying for internships again, you’ll be one step closer to landing the internship of your dream!"
    );
    // CHECKSTYLE.ON: LineLength

    private static final List<List<String>> listOfTips = Arrays.asList(
            applicationSentTips,
            onlineAssessmentTips,
            technicalInterviewTips,
            behavioralInterviewTips,
            phoneInterviewTips,
            rejectedTips
    );


    private static final Map<Stage, List<String>> tipsForStage = new HashMap<>();

    /*
     * Static initialization block to populate the map.
     * Each stage is mapped to a list of tips.
     */
    static {
        for (int i = 0; i < stagesWithTips.size(); i++) {
            tipsForStage.put(stagesWithTips.get(i), listOfTips.get(i));
        }
    }

    /**
     * Returns the list of stages with curated tips.
     */
    public static List<Stage> getStagesWithTips() {
        return stagesWithTips;
    }

    /**
     * Returns a list of tips if stage is present in {@code stagesWithTips},
     * otherwise return an empty list.
     * @param stage the given stage
     * @return a list of stage-specific tips
     */
    public static List<String> getStageSpecificTips(Stage stage) {
        return (!stagesWithTips.contains(stage))
                ? Collections.emptyList()
                : tipsForStage.get(stage);
    }


    /**
     * Checks if a stage is in the pre-defined list of stages which has tips.
     * @param stage the given stage
     * @return true if the stage has tips
     */
    public static boolean stageHasTips(Stage stage) {
        return stagesWithTips.contains(stage);
    }

    /**
     * Returns the list of stages with curated tips as a string
     * for {@code CommandResult} message to hint users on
     * the stages we have tips for.
     */
    public static String stagesWithTipsToString() {
        return stagesWithTips.toString();
    }

}
