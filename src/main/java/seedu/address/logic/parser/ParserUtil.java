package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String INFO_NOT_AVAILABLE = "NA";
    public static final String BLANK_STUDENT_LIST = "Student list cannot be blank. "
            + "Add at least one student for an assignment task.";

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
        if (trimmedPhone.isBlank()) {
            return new Phone(INFO_NOT_AVAILABLE);
        }
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
    /**
     * Parses {@code String size}
     * @param size with the input
     * @return String
     * @throws ParseException
     */
    public static String parseSize(String size) throws ParseException {
        requireNonNull(size);
        String trimmedSize = size.trim();
        if (!AttendanceList.isValidSize(trimmedSize)) {
            throw new ParseException(AttendanceList.MESSAGE_CONSTRAINTS);
        }
        return trimmedSize;
    }


    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(Optional<String> email) throws ParseException {
        String trimmedEmail = email.orElse(INFO_NOT_AVAILABLE).trim();
        if (trimmedEmail.isBlank()) {
            return new Email(INFO_NOT_AVAILABLE);
        }
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
        return new ClassGroup(trimmedClassGroup.isBlank() ? INFO_NOT_AVAILABLE : trimmedClassGroup);
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

    /**
     * Parse a {@code String date} into a {@code Formatted Date}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static FormatDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!FormatDate.isValidDate(trimmedDate)) {
            throw new ParseException(FormatDate.MESSAGE_CONSTRAINTS);
        }
        return new FormatDate(trimmedDate);
    }

    /**
     * Parse a {@code String students} into a {@code List<String>}.
     * Leading and trailing whitespaces will be trimmed for each student name.
     */
    public static List<String> parseStudents(String students) throws ParseException {
        requireNonNull(students);
        String[] arr = students.split(",");
        if (students.isBlank() || arr.length == 0) {
            throw new ParseException(BLANK_STUDENT_LIST);
        }
        ArrayList<String> result = Arrays.stream(arr).map(String::trim).filter(str -> !str.isBlank())
                .collect(Collectors.toCollection(ArrayList::new));
        if (result.size() == 0) {
            throw new ParseException(BLANK_STUDENT_LIST);
        }
        return result;
    }
}
