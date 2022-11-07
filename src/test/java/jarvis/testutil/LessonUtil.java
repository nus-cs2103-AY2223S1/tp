package jarvis.testutil;

import static jarvis.logic.parser.CliSyntax.PREFIX_END_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_END_TIME;
import static jarvis.logic.parser.CliSyntax.PREFIX_LESSON;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_DATE;
import static jarvis.logic.parser.CliSyntax.PREFIX_START_TIME;
import static jarvis.logic.parser.ParserUtil.DATE_FORMATTER;
import static jarvis.logic.parser.ParserUtil.TIME_FORMATTER;

import java.time.LocalDateTime;

import jarvis.logic.commands.AddConsultCommand;
import jarvis.logic.commands.AddMasteryCheckCommand;
import jarvis.logic.commands.AddStudioCommand;
import jarvis.model.Consult;
import jarvis.model.Lesson;
import jarvis.model.MasteryCheck;
import jarvis.model.Studio;

/**
 * A utility class for Lesson.
 */
public class LessonUtil {

    /**
     * Returns an add command string for adding the {@code Studio}.
     */
    public static String getAddStudioCommand(Studio studio) {
        return AddStudioCommand.COMMAND_WORD + " " + getLessonDetails(studio);
    }

    /**
     * Returns an incomplete add command string for adding the {@code MasteryCheck}.
     * The String only contains the COMMAND_WORD, lessonDesc, startDate, startTime, endDate and endTime.
     */
    public static String getPartialAddMasteryCheckCommand(MasteryCheck mc) {
        return AddMasteryCheckCommand.COMMAND_WORD + " " + getLessonDetails(mc);
    }

    /**
     * Returns an incomplete add command string for adding the {@code Consult}.
     * The String only contains the COMMAND_WORD, lessonDesc, startDate, startTime, endDate and endTime.
     */
    public static String getPartialAddConsultCommand(Consult consult) {
        return AddConsultCommand.COMMAND_WORD + " " + getLessonDetails(consult);
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

        return sb.toString();
    }
}
