package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.DateOutOfRangeException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.team.TeamName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";


    private static final String DATE_FORMAT = "dd-MM-uuuu";

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
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
    public static PersonName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!PersonName.isValidName(trimmedName)) {
            throw new ParseException(PersonName.MESSAGE_CONSTRAINTS);
        }
        return PersonName.of(trimmedName);
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
     * Parses a {@code String name} into a task {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given task {@code name} is invalid.
     */
    public static TaskName parseTaskName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TaskName.isValidName(trimmedName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return TaskName.of(trimmedName);
    }

    /**
     * Parses a {@code String name} into a team {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given team {@code name} is invalid.
     */
    public static TeamName parseTeamName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TeamName.isValidName(trimmedName)) {
            throw new ParseException(TeamName.MESSAGE_CONSTRAINTS);
        }
        return TeamName.of(trimmedName);
    }

    /**
     * Parses a {@code String deadline} into a task {@code deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given task {@code deadline} is in an invalid format.
     */
    public static Optional<LocalDate> parseDeadline(String deadline) throws ParseException {
        String trimmedDeadline;

        try {
            trimmedDeadline = deadline.trim();
        } catch (NullPointerException e) {
            return Optional.empty();
        }

        try {
            LocalDate date = LocalDate.parse(trimmedDeadline, DATE_TIME_FORMAT);
            if (!Task.isValidDeadline(date)) {
                throw new DateOutOfRangeException();
            }
            return Optional.ofNullable(date);
        } catch (DateOutOfRangeException e) {
            throw new ParseException(Task.MESSAGE_INVALID_DATE_VALUE);
        } catch (DateTimeParseException e) {
            throw new ParseException(Task.MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
