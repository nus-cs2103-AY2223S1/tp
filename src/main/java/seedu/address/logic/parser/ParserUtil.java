package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.order.Order;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.TemplateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Location;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.OfficeHour;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String DEFAULT_LOC_STRING = "DEFAULT_LOC";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses {@code order} into an {@code Order} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws IllegalArgumentException if the specified order is invalid.
     */
    public static Order parseOrder(String order) throws IllegalArgumentException {
        String trimmedOrder = order.trim();
        return Order.lexicographicalOrder(trimmedOrder);
    }

    /**
     * Parses {@code Collection<String> oneBasedIndexes} into a {@code Set<Index>}
     */
    public static Set<Index> parseIndexes(Collection<String> oneBasedIndexes) throws ParseException {
        requireNonNull(oneBasedIndexes);
        final Set<Index> indexSet = new LinkedHashSet<>();
        for (String oneBasedIndex : oneBasedIndexes) {
            indexSet.add(parseIndex(oneBasedIndex));
        }
        return indexSet;
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
     * Parses a {@code String moduleCode} into an {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses {@code Collection<String> moduleCodes} into a {@code Set<moduleCodes>}.
     */
    public static Set<ModuleCode> parseModuleCodes(Collection<String> moduleCodes) throws ParseException {
        requireNonNull(moduleCodes);
        final Set<ModuleCode> moduleCodeSet = new HashSet<>();
        for (String moduleCode : moduleCodes) {
            moduleCodeSet.add(parseModuleCode(moduleCode));
        }
        return moduleCodeSet;
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
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        if (location.equals(DEFAULT_LOC_STRING)) {
            return new Location("NUS");
        }
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(location)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String rating} into an {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Rating} is invalid.
     */
    public static Rating parseRating(String rating, Boolean isPresent) throws ParseException {
        requireNonNull(rating);
        String trimmedRating = rating.trim();
        if (!isPresent) {
            return new Rating(trimmedRating, false);
        }
        if (!Rating.isValidRating(rating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }
        return new Rating(trimmedRating, true);
    }

    /**
     * Parses a {@code String username} into an {@code GithubUsername}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code GithubUsername} is invalid.
     */
    public static GithubUsername parseGitHubUsername(String username, Boolean isPresent) throws ParseException {
        requireNonNull(username);
        String trimmedUsername = username.trim();
        if (!isPresent) {
            return new GithubUsername(trimmedUsername, false);
        }
        if (!GithubUsername.isValidUsername(username)) {
            throw new ParseException(GithubUsername.MESSAGE_CONSTRAINTS);
        }
        return new GithubUsername(trimmedUsername, true);
    }

    /**
     * Parses a {@code String person} into an {@code string}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code person} is invalid.
     */
    public static String parseTemplate(String person) throws ParseException {
        requireNonNull(person);
        String trimmedPerson = person.trim();

        if (!TemplateCommand.isValidPerson(trimmedPerson)) {
            throw new ParseException(TemplateCommand.MESSAGE_CONSTRAINTS);
        }
        return trimmedPerson;
    }

    /**
     * Parses a {@code String year} into an {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Year} is invalid.
     */
    public static Year parseYear(String year, Boolean isPresent) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!isPresent) {
            return new Year(trimmedYear, false);
        }
        if (!Year.isValidYear(year)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(trimmedYear, true);
    }

    /**
     * Parses a {@code String field} into an {@code Specialisation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Year} is invalid.
     */
    public static Specialisation parseSpecialisation(String field, Boolean isPresent) throws ParseException {
        requireNonNull(field);
        String trimmedField = field.trim();
        if (!isPresent) {
            return new Specialisation(trimmedField, false);
        }
        if (!Specialisation.isValidSpecialisation(field)) {
            throw new ParseException(Specialisation.MESSAGE_CONSTRAINTS);
        }
        return new Specialisation(trimmedField, true);
    }

    /**
     * Parses a {@code String officeHour} into an {@code OfficeHour}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code OfficeHour} is invalid.
     */
    public static OfficeHour parseOfficeHour(String officeHour, boolean isPresent) throws ParseException {
        requireNonNull(officeHour);
        String trimmedOfficeHour = officeHour.trim();
        if (!isPresent) {
            return new OfficeHour(trimmedOfficeHour, false);
        }
        if (!OfficeHour.isValidOfficeHourInstruction(officeHour)) {
            throw new ParseException(OfficeHour.MESSAGE_CONSTRAINTS);
        }

        // trimmedField: DayOfWeek-HH:mm-Duration
        // fieldArr: [DayOfWeek, HH:mm, Duration]
        String[] fieldArr = officeHour.split("-");
        DayOfWeek dayOfWeek = DayOfWeek.of(Integer.parseInt(fieldArr[0]));
        LocalTime startTime;
        LocalTime endTime;
        try {
            startTime = LocalTime.parse(fieldArr[1]);
            endTime = startTime.plusHours(Integer.parseInt(fieldArr[2]));
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage());
        }
        String formattedOfficeHour = dayOfWeek.toString()
                + ", "
                + startTime.format(TIME_FORMATTER)
                + " - "
                + endTime.format(TIME_FORMATTER);
        if (!OfficeHour.isValidOfficeHour(formattedOfficeHour)) {
            throw new ParseException(OfficeHour.MESSAGE_CONSTRAINTS);
        }
        return new OfficeHour(formattedOfficeHour, true);
    }

}
