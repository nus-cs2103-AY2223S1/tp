package nus.climods.ui.module.components;

import java.util.List;

import org.openapitools.client.model.Lesson;

import nus.climods.ui.common.Pill;

/**
 * A pill component for displaying a {@link nus.climods.model.module.Module} modular credits
 */
public class LessonPill extends Pill {
    // TODO: Explore giving different BG_COLOR for different lesson enums
    private static final String DEFAULT_BG_COLOR = "#61AFEF";
    private static final String DEFAULT_TEXT_COLOR = "#FFFFFF";
    private static final int DEFAULT_FONT_SIZE = 13;
    // 1: Friday (1400 - 1500), COM3
    private static final String LESSON_FORMAT = "%s: %s (%s - %s), %s";
    /**
     * Creates an LessonPill.
     *
     * @param id id of the lesson
     */
    public LessonPill(String id, List<Lesson> lessons) {
        super(formatLesson(id, lessons), DEFAULT_BG_COLOR,
                DEFAULT_TEXT_COLOR,
                DEFAULT_FONT_SIZE);
    }

    private static String formatLesson(String id, List<Lesson> lessons) {
        String output = String.format("%s: \n", id);

        for (int i = 1; i <= lessons.size(); i++) {
            Lesson lesson = lessons.get(i - 1);
            output += String.format(LESSON_FORMAT, i, lesson.getDay(), lesson.getStartTime(),
                    lesson.getEndTime(), lesson.getVenue());
        }
        return output;
    }
}
