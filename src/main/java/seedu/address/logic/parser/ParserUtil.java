package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.ClassGroup;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String INFO_NOT_AVAILABLE = "NA";

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
    public static Phone parsePhone(Optional<String> phone) throws ParseException {
        String trimmedPhone = phone.orElse(INFO_NOT_AVAILABLE).trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses {@code String attendance} into an {@code Attendance attendance}
     * @param attendance with the input
     * @return Attendance
     * @throws ParseException
     */
    public static Attendance parseAttendance(Optional<String> attendance) throws ParseException {
        String trimmedAttendance = attendance.orElse(INFO_NOT_AVAILABLE).trim();
        if (!Attendance.isValidMark(trimmedAttendance)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(trimmedAttendance);
    }

    public static String parseSize(String size) throws ParseException {
        requireNonNull(size);
        String trimmedSize = size.trim();
        if (!AttendanceList.isValidSize(trimmedSize)) {
            throw new ParseException(AttendanceList.MESSAGE_CONSTRAINTS);
        }
        return trimmedSize;
    }


    /**
     * Parses a {@code String address} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static StudentId parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!StudentId.isValidStudentId(trimmedAddress)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(Optional<String> email) throws ParseException {
        String trimmedEmail = email.orElse(INFO_NOT_AVAILABLE).trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String classGroup} into an {@code ClassGroup}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static ClassGroup parseClassGroup(Optional<String> classGroup) throws ParseException {
        String trimmedClassGroup = classGroup.orElse(INFO_NOT_AVAILABLE).trim();
        return new ClassGroup(trimmedClassGroup);
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
     * Parses a {@code String title} into a {@code TaskTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static TaskTitle parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!TaskTitle.isValidTitle(trimmedTitle)) {
            throw new ParseException(TaskTitle.MESSAGE_CONSTRAINTS);
        }
        return new TaskTitle(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code TaskDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static TaskDescription parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!TaskDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        return new TaskDescription(trimmedDescription);
    }
}
