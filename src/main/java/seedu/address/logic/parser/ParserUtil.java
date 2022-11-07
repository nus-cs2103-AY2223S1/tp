package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX =
            "Index is not a non-zero unsigned integer or it exceeds 2147483647.";

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
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String birthdate} into an {@code Birthdate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthdate} is invalid.
     */
    public static Birthdate parseBirthdate(String birthdate) throws ParseException {
        requireNonNull(birthdate);
        String trimmedBirthdate = birthdate.trim();
        if (!Birthdate.isValidBirthdate(trimmedBirthdate)) {
            throw new ParseException(Birthdate.MESSAGE_CONSTRAINTS);
        }
        if (!Birthdate.isDateInTheFuture(trimmedBirthdate)) {
            throw new ParseException(Birthdate.BIRTHDATE_AFTER_TODAY);
        }
        return new Birthdate(trimmedBirthdate);
    }

    /**
     * Parses a {@code String race} into an {@code Race}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code race} is invalid.
     */
    public static Race parseRace(String race) throws ParseException {
        requireNonNull(race);
        String trimmedRace = race.trim();
        if (!Race.isValidRace(trimmedRace)) {
            throw new ParseException(Race.MESSAGE_CONSTRAINTS);
        }
        return new Race(trimmedRace);
    }

    /**
     * Parses a {@code String religion} into an {@code Religion}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code religion} is invalid.
     */
    public static Religion parseReligion(String religion) throws ParseException {
        requireNonNull(religion);
        String trimmedReligion = religion.trim();
        if (!Religion.isValidReligion(trimmedReligion)) {
            throw new ParseException(Religion.MESSAGE_CONSTRAINTS);
        }
        return new Religion(trimmedReligion);
    }

    /**
     * Parses a {@code String survey} into an {@code Survey}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code survey} is invalid.
     */
    public static Survey parseSurvey(String survey) throws ParseException {
        requireNonNull(survey);
        String trimmedSurvey = survey.trim();
        if (!Survey.isValidSurvey(trimmedSurvey)) {
            throw new ParseException(Survey.MESSAGE_CONSTRAINTS);
        }
        return new Survey(trimmedSurvey);
    }

    /**
     * Parses {@code Collection<String> surveys} into a {@code Set<Survey>}.
     */
    public static Set<Survey> parseSurveys(Collection<String> surveys) throws ParseException {
        requireNonNull(surveys);
        final Set<Survey> surveySet = new HashSet<>();
        for (String surveyName : surveys) {
            surveySet.add(parseSurvey(surveyName));
        }
        return surveySet;
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
}
