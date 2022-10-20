package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.Workload;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonGroup;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String workload} into a {@code Workload}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Workload parseWorkload(String workload) throws ParseException {
        requireNonNull(workload);
        String trimmedWorkload = workload.trim();
        Workload wl;
        //Workload specified is not low, medium or high
        try {
            wl = Workload.valueOf(trimmedWorkload);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Workload.MESSAGE_CONSTRAINTS);
        }
        return wl;
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        LocalDateTime dateTime;
        //Check if deadline is in yyyy-mm-dd HH:mm or yyyy-mm-dd format
        try {
            dateTime = DateTimeParser.getDateTime(trimmedDeadline);
        } catch (DateTimeException e) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(dateTime);
    }

    /**
     * Parses a {@code String assignment} into a {@code Assignment}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Assignment parseAssignment(String assignment) throws ParseException {
        requireNonNull(assignment);
        String trimmedAssignment = assignment.trim();
        if (!Assignment.isValidAssignment(trimmedAssignment)) {
            throw new ParseException(Assignment.MESSAGE_CONSTRAINTS);
        }
        return new Assignment(trimmedAssignment);
    }

    /**
     * Parses a {@code String assignment} and {@code String workload} into a {@code Assignment}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Assignment parseAssignmentWithWorkload(String assignment, Workload workload)
            throws ParseException {
        requireAllNonNull(assignment, workload);
        String trimmedAssignment = assignment.trim();
        if (!Assignment.isValidAssignment(trimmedAssignment)) {
            throw new ParseException(Assignment.MESSAGE_CONSTRAINTS);
        }
        return new Assignment(trimmedAssignment, workload);
    }

    /**
     * Parses a {@code String assignment}, {@code String workload}, {@code String deadline}
     * into a {@code Assignment}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Assignment parseAssignmentWithDeadline(
            String assignment, Workload workload, Deadline deadline) throws ParseException {
        requireAllNonNull(assignment, workload, deadline);
        String trimmedAssignment = assignment.trim();
        if (!Assignment.isValidAssignment(trimmedAssignment)) {
            throw new ParseException(Assignment.MESSAGE_CONSTRAINTS);
        }
        return new Assignment(trimmedAssignment, workload, deadline);
    }

    /**
     * Parses a {@code String PersonGroup} into a {@code PersonGroup}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static PersonGroup parsePersonGroup(String personGroup) {
        requireNonNull(personGroup);
        String trimmedPersonGroup = personGroup.trim();

        return new PersonGroup(trimmedPersonGroup);
    }

    /**
     * Parses a {@code String groupname} into a {@code GroupName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code groupname} is invalid.
     */
    public static GroupName parseGroupName(String groupname) throws ParseException {
        requireNonNull(groupname);
        String trimmedName = groupname.trim();
        if (!GroupName.isValidName(trimmedName)) {
            throw new ParseException(GroupName.MESSAGE_CONSTRAINTS);
        }
        return new GroupName(trimmedName);
    }
}
