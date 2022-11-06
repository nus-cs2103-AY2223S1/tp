package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.model.order.Order.ORDER_ASC;
import static seedu.studmap.model.order.Order.ORDER_DSC;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.util.StringUtil;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.order.Order;
import seedu.studmap.model.student.Assignment;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Email;
import seedu.studmap.model.student.GitName;
import seedu.studmap.model.student.Module;
import seedu.studmap.model.student.Name;
import seedu.studmap.model.student.Participation;
import seedu.studmap.model.student.Phone;
import seedu.studmap.model.student.StudentID;
import seedu.studmap.model.student.TeleHandle;
import seedu.studmap.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String RECEIVED = "Received: ";

    public static String getInvalidMessage(String invalidMessage, String receivedInput) {
        return invalidMessage + " " + RECEIVED + receivedInput;
    }

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
     * Separates {@code preamble} into an array of strings for processing.
     */
    public static String[] separatePreamble(String preamble) {
        return preamble.split("\\s+");
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
            throw new ParseException(getInvalidMessage(Name.MESSAGE_CONSTRAINTS, name));
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
            throw new ParseException(getInvalidMessage(Phone.MESSAGE_CONSTRAINTS, phone));
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
            throw new ParseException(getInvalidMessage(Email.MESSAGE_CONSTRAINTS, email));
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String module} into a {@code Module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Module} is invalid.
     */
    public static Module parseModule(String module) throws ParseException {
        requireNonNull(module);
        String trimmedModule = module.trim().toUpperCase();
        if (!Module.isValidModule(trimmedModule)) {
            throw new ParseException(getInvalidMessage(Module.MESSAGE_CONSTRAINTS, module));
        }
        return new Module(trimmedModule);
    }

    /**
     * Parses a {@code String StudentId} into an {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code StudentId} is invalid.
     */
    public static StudentID parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!StudentID.isValidStudentID(trimmedId)) {
            throw new ParseException(getInvalidMessage(StudentID.MESSAGE_CONSTRAINTS, id));
        }
        return new StudentID(trimmedId);
    }

    /**
     * Parses a {@code String gitName} into an {@code GitName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gitName} is invalid.
     */
    public static GitName parseGitName(String gitName) throws ParseException {
        requireNonNull(gitName);
        String trimmedGit = gitName.trim();
        if (!GitName.isValidGitName(trimmedGit)) {
            throw new ParseException(getInvalidMessage(GitName.MESSAGE_CONSTRAINTS, gitName));
        }
        return new GitName(trimmedGit);
    }

    /**
     * Parses a {@code String handle} into an {@code TeleHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid.
     */
    public static TeleHandle parseHandle(String handle) throws ParseException {
        requireNonNull(handle);
        String trimmedHandle = handle.trim();
        if (!TeleHandle.isValidTeleHandle(trimmedHandle)) {
            throw new ParseException(getInvalidMessage(TeleHandle.MESSAGE_CONSTRAINTS, handle));
        }
        return new TeleHandle(trimmedHandle);
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
            throw new ParseException(getInvalidMessage(Tag.MESSAGE_CONSTRAINTS, tag));
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
     * Parses a {@code String className} checking for any errors.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code className} is invalid.
     */
    public static String parseClassName(String className) throws ParseException {
        requireNonNull(className);
        String trimmedClass = className.trim();
        if (!Attendance.isValidClassName(trimmedClass)) {
            throw new ParseException(getInvalidMessage(Attendance.MESSAGE_CONSTRAINTS, className));
        }
        return trimmedClass;
    }

    /**
     * Parses a {@code String assignmentName} checking for any errors.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code assignmentName} is invalid.
     */
    public static String parseAssignmentName(String assignmentName) throws ParseException {
        requireNonNull(assignmentName);
        String trimmedAssignment = assignmentName.trim();
        if (!Assignment.isValidAssignmentName(trimmedAssignment)) {
            throw new ParseException(getInvalidMessage(Assignment.MESSAGE_CONSTRAINTS, assignmentName));
        }
        return trimmedAssignment;
    }

    /**
     * Parses a {@code String order} into a {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static Order parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (!Order.isValidOrderName(trimmedOrder)) {
            throw new ParseException(getInvalidMessage(Order.MESSAGE_CONSTRAINTS, order));
        }
        return trimmedOrder.matches("asc") ? ORDER_ASC : ORDER_DSC;
    }

    /**
     * Parses a {@code String participationComponent} checking for any errors.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code participationComponent} is invalid.
     */
    public static String parseParticipationComponent(String participationComponent) throws ParseException {
        requireNonNull(participationComponent);
        String trimmedPartCom = participationComponent.trim();
        if (!Participation.isValidParticipationName(trimmedPartCom)) {
            throw new ParseException(getInvalidMessage(Participation.MESSAGE_CONSTRAINTS, participationComponent));
        }
        return trimmedPartCom;
    }
}
