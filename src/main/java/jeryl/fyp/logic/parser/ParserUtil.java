package jeryl.fyp.logic.parser;

import static java.util.Objects.requireNonNull;
import static jeryl.fyp.commons.core.Datetimes.ACCEPTABLE_DATETIME_FORMATS;
import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_DEADLINE_RANK;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jeryl.fyp.commons.core.index.Index;
import jeryl.fyp.commons.util.StringUtil;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.student.DeadlineName;
import jeryl.fyp.model.student.Email;
import jeryl.fyp.model.student.ProjectName;
import jeryl.fyp.model.student.ProjectStatus;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.StudentName;
import jeryl.fyp.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATETIME = "Invalid datetime format given!\n"
            + "Consider using \"dd-MM-yyyy HH:mm\"\n"
            + "Example: 20-10-2022 13:57";
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
     * Parses a {@code String name} into a {@code StudentName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code StudentName} is invalid.
     */
    public static StudentName parseStudentName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!StudentName.isValidStudentName(trimmedName)) {
            throw new ParseException(StudentName.MESSAGE_CONSTRAINTS);
        }
        return new StudentName(trimmedName);
    }

    /**
     * Parses {@code String studentId} into an {@code StudentId} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code StudentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedId);
    }

    /**
     * Parses a {@code String projectName} into a {@code ProjectName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code projectName} is invalid.
     */
    public static ProjectName parseProjectName(String projectName) throws ParseException {
        requireNonNull(projectName);
        String trimmedProjectName = projectName.trim();
        if (!ProjectName.isValidProjectName(trimmedProjectName)) {
            throw new ParseException(ProjectName.MESSAGE_CONSTRAINTS);
        }
        return new ProjectName(trimmedProjectName);
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

    /**
     * Parses {@code String studentId} into an {@code StudentId} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified studentId is invalid(not of the format "A" + 7 numbers + 1 letter).
     */
    public static ProjectStatus parseProjectStatus(String projectStatus) throws ParseException {
        requireNonNull(projectStatus);
        String trimmedProjectStatus = projectStatus.trim();
        if (!ProjectStatus.isValidProjectStatus(trimmedProjectStatus)) {
            throw new ParseException(ProjectStatus.MESSAGE_CONSTRAINTS);
        }
        return new ProjectStatus(trimmedProjectStatus);
    }

    /**
     * Parses a {@code String deadlineName} into a {@code deadlineName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadlineName} is invalid.
     */
    public static DeadlineName parseDeadlineName(String deadlineName) throws ParseException {
        requireNonNull(deadlineName);
        String trimmedDeadlineName = deadlineName.trim();
        if (!DeadlineName.isValidDeadlineName(trimmedDeadlineName)) {
            throw new ParseException(DeadlineName.MESSAGE_CONSTRAINTS);
        }
        return new DeadlineName(trimmedDeadlineName);
    }

    /**
     * Converts a datetime string to a LocalDateTime Object.
     * @param dateTime A string in format: dd-MM-yyyy HH:mm.
     * @return A LocalDateTime variable implied by the string.
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static LocalDateTime parseDeadlineDatetime(String dateTime) throws ParseException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(dateTime,
                        DateTimeFormatter.ofPattern(dateTimeFormat)
                                .withResolverStyle(ResolverStyle.SMART));
            } catch (DateTimeParseException dtpe) {
                // Go to the next dateTime format
            }
        }
        throw new ParseException(MESSAGE_INVALID_DATETIME);
    }

    /**
     * Parses a {@code String rank} into a {@code Integer rank}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rank} is invalid.
     */
    public static Integer parseRank(String rank) throws ParseException {
        requireNonNull(rank);
        String trimmedRank = rank.trim();
        try {
            return Integer.valueOf(trimmedRank);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DEADLINE_RANK);
        }
    }
}
