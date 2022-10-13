package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEPROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.Homework;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_EDIT_COMMAND_FORMAT = "The correct format for an edit is:\n"
            + "edit [" + PREFIX_NAME + PREFIX_PHONE + PREFIX_LESSON_PLAN + "]NEW_FIELD, or\n"
            + "edit [" + PREFIX_HOMEWORK + PREFIX_GRADEPROGRESS + PREFIX_ATTENDANCE + "]INDEX NEW_FIELD\n"
            + "Example: edit " + PREFIX_HOMEWORK + "2 Maths worksheet";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String lessonPlan} into an {@code LessonPlan}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static LessonPlan parseLessonPlan(String lessonPlan) {
        requireNonNull(lessonPlan);
        String trimmedLessonPlan = lessonPlan.trim();
        return new LessonPlan(trimmedLessonPlan);
    }

    /**
     * Parses a {@code String homework} into an {@code Homework}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Homework parseHomework(String homework) {
        requireNonNull(homework);
        String trimmedHomework = homework.trim();
        return new Homework(trimmedHomework);
    }

    /**
     * Parses a {@code String homeworkInfo} and disassembles it into index and homework.
     *
     * @param homeworkInfo index and description of homework.
     * @return array containing index in position 0 and homework description in position 1.
     */
    public static String[] parseHomeworkInfo(String homeworkInfo) throws ParseException {
        String[] args = homeworkInfo.split(" ", 2);
        if (args.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EDIT_COMMAND_FORMAT));
        }
        return args;
    }

    /**
     * Parses a {@code String gradeProgress} into an {@code GradeProgress}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static GradeProgress parseGradeProgress(String gradeProgress) {
        requireNonNull(gradeProgress);
        String trimmedGradeProgress = gradeProgress.trim();
        return new GradeProgress(trimmedGradeProgress);
    }

    /**
     * Parses a {@code String gradeProgressInfo} and disassembles it into index and grade progress.
     *
     * @param gradeProgressInfo index and description of grade progress.
     * @return array containing index in position 0 and grade in position 1.
     */
    public static String[] parseGradeProgressInfo(String gradeProgressInfo) throws ParseException {
        String[] args = gradeProgressInfo.split(" ", 2);
        if (args.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EDIT_COMMAND_FORMAT));
        }
        return args;
    }

    /**
     * Parses a {@code String attendance} into an {@code Attendance}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Attendance parseAttendance(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        if (!Attendance.isValidAttendance(attendance)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(trimmedAttendance);
    }

    /**
     * Parses an {@code String attendanceInfo} and disassembles it into index and attendance.
     *
     * @param attendanceInfo index and description of attendance.
     * @return array containing index in position 0 and attendance in position 1.
     */
    public static String[] parseAttendanceInfo(String attendanceInfo) throws ParseException {
        String[] args = attendanceInfo.split(" ", 2);
        if (args.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EDIT_COMMAND_FORMAT));
        }
        return args;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
