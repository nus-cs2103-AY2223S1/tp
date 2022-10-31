package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

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
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_EDIT_COMMAND_FORMAT = "The correct format for an edit is:\n"
            + "edit [" + PREFIX_NAME + PREFIX_PHONE + PREFIX_LESSON_PLAN + "]NEW_FIELD, or\n"
            + "edit [" + PREFIX_HOMEWORK + PREFIX_GRADE_PROGRESS + PREFIX_ATTENDANCE + "]INDEX NEW_FIELD\n"
            + "Example: edit " + PREFIX_HOMEWORK + "2 Maths worksheet";
    public static final String MESSAGE_REMOVE_COMMAND_FORMAT = "The correct format for a remove is:\n"
            + "remove PERSON_INDEX [" + PREFIX_HOMEWORK
            + PREFIX_GRADE_PROGRESS + PREFIX_ATTENDANCE + "] INDEX_TO_REMOVE\n";

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
     * @throws ParseException if the given {@code lessonPlan} is invalid.
     */
    public static LessonPlan parseLessonPlan(String lessonPlan) throws ParseException {
        requireNonNull(lessonPlan);
        String trimmedLessonPlan = lessonPlan.trim();
        if (!LessonPlan.isValidLessonPlan(trimmedLessonPlan)) {
            throw new ParseException(LessonPlan.MESSAGE_CONSTRAINTS);
        }
        return new LessonPlan(trimmedLessonPlan);
    }

    /**
     * Parses a {@code String homework} into an {@code Homework}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code homework} is invalid.
     */
    public static Homework parseHomework(String homework) throws ParseException {
        requireNonNull(homework);
        String trimmedHomework = homework.trim();
        if (!Homework.isValidHomework(trimmedHomework)) {
            throw new ParseException(Homework.MESSAGE_CONSTRAINTS);
        }
        return new Homework(trimmedHomework);
    }

    /**
     * Parses a {@code String gradeProgress} into an {@code GradeProgress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gradeProgress} is invalid.
     */
    public static GradeProgress parseGradeProgress(String gradeProgress) throws ParseException {
        requireNonNull(gradeProgress);
        String trimmedGradeProgress = gradeProgress.trim();
        if (!GradeProgress.isValidGradeProgress(trimmedGradeProgress)) {
            throw new ParseException(GradeProgress.MESSAGE_CONSTRAINTS);
        }
        return new GradeProgress(trimmedGradeProgress);
    }

    /**
     * Parses a {@code String attendance} into an {@code Attendance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendance} is invalid.
     */
    public static Attendance parseAttendance(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        if (!Attendance.isValidAttendance(trimmedAttendance)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        try {
            return new Attendance(trimmedAttendance);
        } catch (IllegalArgumentException iae) {
            throw new ParseException(Attendance.MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Parses a {@code String Session} into a {@code Session}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code session} is invalid.
     */
    public static Session parseSession(String session) throws ParseException {
        requireNonNull(session);
        String trimmedSession = session.trim();
        if (!Session.isValidSession(trimmedSession)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }
        return new Session(trimmedSession);
    }

    /**
     * Parses a {@code String text} and disassembles it into index and description.
     *
     * @throws ParseException if the input is less than 2, or does not start with an integer.
     */
    public static String[] parseIndexedEdit(String text) throws ParseException {
        String[] args = text.split(" ", 2);
        if (args.length < 2 || !StringUtil.isNonZeroUnsignedInteger(args[0])) {
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
