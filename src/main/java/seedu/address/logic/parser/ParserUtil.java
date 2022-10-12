package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddCommand.Entity;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;

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
     * Parses {@code String entity} into an {@code Entity} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified {@code entity} is not one of the accepted entity types.
     */
    public static Entity parseEntity(String entity) throws ParseException {
        requireNonNull(entity);
        String trimmedEntity = entity.trim();
        if (!Entity.isValidEntity(trimmedEntity)) {
            throw new ParseException(Entity.MESSAGE_CONSTRAINTS);
        }
        return Entity.fromString(trimmedEntity);
    }

    /**
     * Parses {@code String school} into an {@code School} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code school} is invalid.
     */
    public static School parseSchool(String school) throws ParseException {
        requireNonNull(school);
        String trimmedSchool = school.trim();
        if (!School.isValidSchool(trimmedSchool)) {
            throw new ParseException(School.MESSAGE_CONSTRAINTS);
        }
        return new School(trimmedSchool);
    }

    /**
     * Parses {@code String level} into an {@code Level} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Level parseLevel(String level) throws ParseException {
        requireNonNull(level);
        String trimmedLevel = level.trim();
        if (!Level.isValidLevel(trimmedLevel)) {
            throw new ParseException(Level.MESSAGE_CONSTRAINTS);
        }
        return Level.createLevel(trimmedLevel);
    }

    /**
     * Parses {@code String nextOfKin} into an {@code NextOfKin} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if the given {@code nextOfKin} is invalid.
     */
    public static NextOfKin parseNextOfKin(String nextOfKin) throws ParseException {
        requireNonNull(nextOfKin);
        String trimmedNextOfKin = nextOfKin.trim();
        if (!NextOfKin.isValidNextOfKin(trimmedNextOfKin)) {
            throw new ParseException(NextOfKin.MESSAGE_CONSTRAINTS);
        }
        return new NextOfKin(trimmedNextOfKin);
    }

    /**
     * Parses {@code String qualification} into an {@code Qualification} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     * @throws ParseException if the given {@code qualification} is invalid.
     */
    public static Qualification parseQualification(String qualification) throws ParseException {
        requireNonNull(qualification);
        String trimmedQualification = qualification.trim();
        if (!Qualification.isValidQualification(trimmedQualification)) {
            throw new ParseException(Qualification.MESSAGE_CONSTRAINTS);
        }
        return new Qualification(trimmedQualification);
    }

    /**
     * Parses {@code String institution} into an {@code Institution} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the given {@code institution} is invalid.
     */
    public static Institution parseInstitution(String institution) throws ParseException {
        requireNonNull(institution);
        String trimmedInstitution = institution.trim();
        if (!Institution.isValidInstitution(trimmedInstitution)) {
            throw new ParseException(Institution.MESSAGE_CONSTRAINTS);
        }
        return new Institution(trimmedInstitution);
    }

    /**
     * Parses {@code String subject} into an {@code Subject} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return Subject.createSubject(trimmedSubject);
    }

    /**
     * Parses {@code String day} into an {@code Day} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static Day parseDay(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDay = day.trim();
        if (!Day.isValidDay(trimmedDay)) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
        return Day.createDay(trimmedDay);
    }

    /**
     * Parses {@code String time} into an {@code Time} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        String[] splitTime = trimmedTime.split("-");
        return new Time(splitTime[0], splitTime[1]);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.person.Name parsePersonName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!seedu.address.model.person.Name.isValidName(trimmedName)) {
            throw new ParseException(seedu.address.model.person.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.person.Name(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.tuitionclass.Name parseClassName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!seedu.address.model.tuitionclass.Name.isValidName(trimmedName)) {
            throw new ParseException(seedu.address.model.tuitionclass.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.tuitionclass.Name(trimmedName);
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
}
