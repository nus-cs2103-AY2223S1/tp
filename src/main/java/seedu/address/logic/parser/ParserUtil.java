package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.FilePath;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.NetWorth;
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
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Description parseDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toUpperCase();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a {@code String netWorth} into a {@code NetWorth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code NetWorth} is invalid.
     */
    public static NetWorth parseNetWorth(String netWorth) throws ParseException {
        requireNonNull(netWorth);
        String trimmedNetWorth = netWorth.trim();
        if (!NetWorth.isValidNetWorth(trimmedNetWorth)) {
            throw new ParseException(NetWorth.MESSAGE_CONSTRAINTS);
        }
        return new NetWorth(trimmedNetWorth);
    }

    /**
     * Parses a {@code String meetingTime} into a {@code MeetingTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code MeetingTime} is invalid.
     */
    public static MeetingTime parseMeetingTime(String meetingTime) throws ParseException {
        requireNonNull(meetingTime);
        String trimmedMeetingTime = meetingTime.replaceAll("\\s", "");
        if (!MeetingTime.isValidMeetingTime(trimmedMeetingTime)) {
            throw new ParseException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
        if (!MeetingTime.isValidDayMonth(trimmedMeetingTime)) {
            throw new ParseException(MeetingTime.MESSAGE_INVALID_DATE);
        }
        return new MeetingTime(trimmedMeetingTime);
    }

    /**
     * Parses a {@code String filePath} into a {@code FilePath}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static FilePath parseFilePath(String filePath) {
        requireNonNull(filePath);
        String trimmedFilePath = filePath.trim();
        return new FilePath(trimmedFilePath);
    }

    /**
     * Parses {@code Collection<String> meetingTimes} into a {@code Set<MeetingTime>}.
     */
    public static Set<MeetingTime> parseMeetingTimes(Collection<String> meetingTimes) throws ParseException {
        requireNonNull(meetingTimes);
        final Set<MeetingTime> meetingTimeSet = new HashSet<>();
        for (String meetingTime : meetingTimes) {
            meetingTimeSet.add(parseMeetingTime(meetingTime));
        }
        return meetingTimeSet;
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
