package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_UNEXPECTED_INDEX_COUNT = "The amount of indexes parsed is not as expected.";

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
     * Parses a string of {@code oneBasedIndex}es seperated by spaces into a list of {@code Index} of exactly size
     * {@code parseCount} and returns it. Leading and trailing whitespaces will be trimmed.
     *
     * @param parseCount The exact amount of indexes to parse.
     * @throws ParseException if the specified indexes is invalid (not non-zero unsigned integer), or if the amount of
     *     indexes is not exactly {@code parseCount}
     */
    public static List<Index> parseIndexes(String oneBasedIndexes, int parseCount) throws ParseException {
        String indexSeparator = "\\s+";
        String trimmedIndexes = oneBasedIndexes.trim();
        String[] indexes = trimmedIndexes.split(indexSeparator);

        if (indexes.length != parseCount) {
            throw new ParseException(MESSAGE_UNEXPECTED_INDEX_COUNT);
        }

        List<Index> indexList = new ArrayList<>();
        for (String index : indexes) {
            indexList.add(parseIndex(index));
        }

        return indexList;
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
        if (address.isEmpty()) {
            return new Address("");
        }
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
        if (email.isEmpty()) {
            return new Email("");
        }
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
        return Tag.convertToTag(trimmedTag);
    }

    /**
     * Parses a {@code String reason} and {@code String dateTime} into a {@code Appointment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reason} or {@code dateTime} is invalid.
     */
    public static Appointment parseAppointment(String reason, String dateTime, String period, Set<Tag> tags)
            throws ParseException {
        requireNonNull(dateTime);
        requireNonNull(reason);
        String trimmedReason = reason.trim();
        String trimmedDateTime = dateTime.trim();
        String trimmedPeriod = period.trim();
        if (!Appointment.isValidReason(trimmedReason)) {
            throw new ParseException(Appointment.REASON_MESSAGE_CONSTRAINTS);
        }

        if (!Appointment.isValidDateTime(dateTime)) {
            throw new ParseException(Appointment.DATE_MESSAGE_CONSTRAINTS);
        }

        if (!period.isEmpty() && !Appointment.isValidTimePeriod(trimmedPeriod)) {
            throw new ParseException(Appointment.TIME_PERIOD_MESSAGE_CONSTRAINTS);
        }

        return new Appointment(trimmedReason, trimmedDateTime, trimmedPeriod, tags, false);
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
