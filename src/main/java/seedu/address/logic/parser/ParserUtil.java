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
import seedu.address.model.consultation.ConsultationDescription;
import seedu.address.model.consultation.ConsultationModule;
import seedu.address.model.consultation.ConsultationName;
import seedu.address.model.consultation.ConsultationTimeslot;
import seedu.address.model.consultation.ConsultationVenue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.ReminderDeadline;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialDay;
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
     * Parses a {@code String name} into a {@code ReminderName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ReminderName} is invalid.
     */
    public static ReminderName parseReminderName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ReminderName.isValidName(trimmedName)) {
            throw new ParseException(ReminderName.MESSAGE_CONSTRAINTS);
        }
        return new ReminderName(trimmedName);
    }

    /**
     * Parses a {@code String deadline} into a {@code ReminderDeadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ReminderDeadline} is invalid.
     */
    public static ReminderDeadline parseReminderDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!ReminderDeadline.isValidTimeslot(trimmedDeadline)) {
            throw new ParseException(ReminderDeadline.MESSAGE_CONSTRAINTS);
        }
        return new ReminderDeadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String description} into a {@code ReminderDescription}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ReminderDescription parseReminderDescription(String description) {
        String trimmedDescription = description.trim();
        return new ReminderDescription(trimmedDescription);
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

    /**
     * Parses a {@code String day} into a {@code TutorialDay}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static TutorialDay parseTutorialDay(String day) throws ParseException {
        requireNonNull(day);
        String trimmedValue = day.trim();
        if (!TutorialDay.isValidDay(trimmedValue)) {
            throw new ParseException(TutorialDay.MESSAGE_CONSTRAINTS);
        }
        return new TutorialDay(trimmedValue);
    }

    /**
     * Parses a {@code String name} into a {@code TutorialName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ConsultationName parseConsultationName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ConsultationName.isValidName(trimmedName)) {
            throw new ParseException(ConsultationName.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationName(trimmedName);
    }

    /**
     * Parses a {@code String moduleName} into a {@code ConsultationModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ConsultationModule parseConsultationModule(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedName = moduleName.trim();
        if (!ConsultationModule.isValidModule(trimmedName)) {
            throw new ParseException(ConsultationModule.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationModule(trimmedName);
    }

    /**
     * Parses a {@code String venue} into a {@code ConsultationVenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static ConsultationVenue parseConsultationVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedName = venue.trim();
        if (!ConsultationVenue.isValidVenue(trimmedName)) {
            throw new ParseException(ConsultationVenue.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationVenue(trimmedName);
    }

    /**
     * Parses a {@code String timeslot} into a {@code ConsultationTimeslot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeslot} is invalid.
     */
    public static ConsultationTimeslot parseConsultationTimeslot(String timeslot) throws ParseException {
        requireNonNull(timeslot);
        String trimmedName = timeslot.trim();
        if (!ConsultationTimeslot.isValidTimeslot(trimmedName)) {
            throw new ParseException(ConsultationTimeslot.MESSAGE_CONSTRAINTS);
        }
        if (!ConsultationTimeslot.isValidDuration(trimmedName)) {
            throw new ParseException(ConsultationTimeslot.MESSAGE_INVALID_DURATION);
        }
        return new ConsultationTimeslot(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code TutorialName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ConsultationDescription parseConsultationDescription(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ConsultationDescription.isValidDescription(trimmedName)) {
            throw new ParseException(ConsultationName.MESSAGE_CONSTRAINTS);
        }
        return new ConsultationDescription(trimmedName);
    }
}
