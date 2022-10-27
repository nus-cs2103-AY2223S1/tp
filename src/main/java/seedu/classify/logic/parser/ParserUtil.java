package seedu.classify.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.classify.commons.core.index.Index;
import seedu.classify.commons.util.StringUtil;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.Email;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_FILTER = "Value for filter should either be 'on' or 'off'";

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
     * Parses a {@code String id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses a {@code String className} into a {@code Class}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code className} is invalid.
     */
    public static Class parseClass(String className) throws ParseException {
        requireNonNull(className);
        String trimmedClassName = className.trim();
        if (!Class.isValidClassName(trimmedClassName)) {
            throw new ParseException(Class.MESSAGE_CONSTRAINTS);
        }
        return new Class(trimmedClassName);
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
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String exam} into a {@code Exam}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code exam} is invalid.
     */
    public static Exam parseExam(String exam) throws ParseException {
        requireNonNull(exam);
        String trimmedExam = exam.trim();
        if (!Exam.isValidFormat(trimmedExam)) {
            throw new ParseException(Exam.MESSAGE_CONSTRAINTS);
        }
        String[] args = trimmedExam.split("\\s+");
        String name = args[0].toUpperCase();
        String score = args[1];
        if (!Exam.isValidName(name)) {
            throw new ParseException(Exam.MESSAGE_NAME_CONSTRAINTS);
        }
        if (!Exam.isValidScore(score)) {
            throw new ParseException(Exam.MESSAGE_SCORE_CONSTRAINTS);
        }
        return new Exam(trimmedExam);
    }

    /**
     * Parses {@code Collection<String> exams} into a {@code Set<Exam>}.
     */
    public static Set<Exam> parseExams(Collection<String> exams) throws ParseException {
        requireNonNull(exams);
        Set<Exam> examSet = new HashSet<>();
        for (String exam : exams) {
            Exam temp = parseExam(exam);
            examSet.remove(temp); // Removes duplicate exam and overrides it with the latest one.
            examSet.add(temp);
        }
        return examSet;
    }

    /**
     * Parses {@code String exam} and ensures that it is a valid exam name.
     */
    public static String parseExamQuery(String exam) throws ParseException {
        requireNonNull(exam);
        String trimmedExam = exam.trim();
        if (!Exam.isValidName(trimmedExam.toUpperCase())) {
            throw new ParseException(Exam.MESSAGE_NAME_CONSTRAINTS);
        }
        return trimmedExam.toUpperCase();
    }

    /**
     * Parses a {@code String filter} into a boolean value.
     * Leading and trailing white spaces will be trimmed.
     *
     * @throws ParseException if the given {@code filter} is invalid.
     */
    public static boolean parseFilter(String filter) throws ParseException {
        String trimmedFilter = filter.trim();
        if (trimmedFilter.equals("ON")) {
            return true;
        } else if (trimmedFilter.equals("OFF")) {
            return false;
        } else {
            throw new ParseException(MESSAGE_INVALID_FILTER);
        }
    }

}
