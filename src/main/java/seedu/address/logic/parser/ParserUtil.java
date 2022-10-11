package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.MissingPrefixesException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.ta.TeachingAssistantId;
import seedu.address.model.ta.TeachingAssistantName;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialTimeslot;
import seedu.address.model.tutorial.TutorialVenue;

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
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static void assertPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes)
            throws MissingPrefixesException {
        Prefix[] missingPrefixes = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .map(prefix -> new Prefix(prefix.getPrefix()))
                .toArray(Prefix[]::new);

        if (missingPrefixes.length != 0) {
            throw new MissingPrefixesException(missingPrefixes);
        }
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
     * Parses a {@code String name} into a {@code TutorialName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TutorialName parseTutorialName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TutorialName.isValidName(trimmedName)) {
            throw new ParseException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        return new TutorialName(trimmedName);
    }

    /**
     * Parses a {@code String moduleName} into a {@code TutorialModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static TutorialModule parseTutorialModule(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedName = moduleName.trim();
        if (!TutorialModule.isValidModule(trimmedName)) {
            throw new ParseException(TutorialModule.MESSAGE_CONSTRAINTS);
        }
        return new TutorialModule(trimmedName);
    }

    /**
     * Parses a {@code String venue} into a {@code TutorialVenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static TutorialVenue parseTutorialVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedName = venue.trim();
        if (!TutorialVenue.isValidVenue(trimmedName)) {
            throw new ParseException(TutorialVenue.MESSAGE_CONSTRAINTS);
        }
        return new TutorialVenue(trimmedName);
    }

    /**
     * Parses a {@code String timeslot} into a {@code TutorialTimeslot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeslot} is invalid.
     */
    public static TutorialTimeslot parseTutorialTimeslot(String timeslot) throws ParseException {
        requireNonNull(timeslot);
        String trimmedName = timeslot.trim();
        if (!TutorialTimeslot.isValidTimeslot(trimmedName)) {
            throw new ParseException(TutorialTimeslot.MESSAGE_CONSTRAINTS);
        }
        if (!TutorialTimeslot.isValidDuration(trimmedName)) {
            throw new ParseException(TutorialTimeslot.MESSAGE_INVALID_DURATION);
        }
        return new TutorialTimeslot(trimmedName);
    }

    public static TeachingAssistantName parseTeachingAssistantName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TeachingAssistantName.isValidName(trimmedName)) {
            throw new ParseException(TeachingAssistantName.MESSAGE_CONSTRAINTS);
        }
        return new TeachingAssistantName(trimmedName);
    }

    public static TeachingAssistantId parseTeachingAssistantId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedName = id.trim();
        if (!TeachingAssistantId.isValidId(trimmedName)) {
            throw new ParseException(TeachingAssistantId.MESSAGE_CONSTRAINTS);
        }
        return new TeachingAssistantId(trimmedName);
    }
}
