package jarvis.testutil;

import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.ParserUtil.DATE_FORMATTER;
import static jarvis.logic.parser.ParserUtil.TIME_FORMATTER;

import java.time.LocalDateTime;

import jarvis.logic.commands.AddConsultCommand;
import jarvis.logic.commands.AddMasteryCheckCommand;
import jarvis.logic.commands.AddStudioCommand;
import jarvis.model.Lesson;
import jarvis.model.LessonType;

/**
 * A utility class for Lesson.
 */
public class LessonUtil {

    /**
     * Returns an add command string for adding the {@code lesson}.
     */
    public static String getAddCommand(Lesson lesson) {
        switch (lesson.getLessonType()) {
        case STUDIO:
            return AddStudioCommand.COMMAND_WORD + " " + getLessonDetails(lesson);
//        case CONSULT:
//            return AddConsultCommand.COMMAND_WORD + " " + getLessonDetails(lesson);
//        case MASTERY_CHECK:
//            return AddMasteryCheckCommand.COMMAND_WORD + " " + getLessonDetails(lesson);
        default:
            return "Unknown Lesson type";
        }
    }

    /**
     * Returns the part of command string for the given {@code lesson}'s details.
     */
    public static String getLessonDetails(Lesson lesson) {
        StringBuilder sb = new StringBuilder();

        if (lesson.hasDesc()) {
            sb.append(PREFIX_LESSON).append(lesson.getDesc().lessonDesc).append(" ");
        }

        LocalDateTime start = lesson.startDateTime();
        LocalDateTime end = lesson.endDateTime();
        sb.append(PREFIX_START_DATE).append(start.format(DATE_FORMATTER)).append(" ");
        sb.append(PREFIX_START_TIME).append(start.format(TIME_FORMATTER)).append(" ");
        sb.append(PREFIX_END_DATE).append(end.format(DATE_FORMATTER)).append(" ");
        sb.append(PREFIX_END_TIME).append(end.format(TIME_FORMATTER)).append(" ");

        if (lesson.getLessonType().equals(LessonType.CONSULT)
                || lesson.getLessonType().equals(LessonType.MASTERY_CHECK)) {
            // how to get student index from student
        }

        return sb.toString();
    }
}
