package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.index.Index.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.util.StringUtil.capitaliseOnlyFirstLetter;
import static seedu.address.commons.util.StringUtil.convertShortFormLevel;
import static seedu.address.commons.util.StringUtil.formatTime;
import static seedu.address.commons.util.StringUtil.removeDuplicateComma;
import static seedu.address.commons.util.StringUtil.removeDuplicateWhitespace;
import static seedu.address.commons.util.StringUtil.removeWhitespaceForLevel;

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
import seedu.address.model.person.nextofkin.Relationship;
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
        String trimmedEntity = entity.trim().toLowerCase();

        if (!Entity.isValidEntity(trimmedEntity)) {
            // did you mean? message prompted when string contains entity full word missing at most 2 chars
            // or if string starts with at least 2 chars of the entity full word
            if (trimmedEntity.matches("(?i).*stude.*|st.*")) {
                throw new ParseException(Entity.MESSAGE_CONSTRAINTS + Entity.MESSAGE_DID_YOU_MEAN_STUDENT);
            } else if (trimmedEntity.matches("(?i).*tut.*|tu.*")) {
                throw new ParseException(Entity.MESSAGE_CONSTRAINTS + Entity.MESSAGE_DID_YOU_MEAN_TUTOR);
            } else if (trimmedEntity.matches("(?i).*cla.*|cl.*")) {
                throw new ParseException(Entity.MESSAGE_CONSTRAINTS + Entity.MESSAGE_DID_YOU_MEAN_CLASS);
            }

            throw new ParseException(Entity.MESSAGE_CONSTRAINTS);
        }

        // allow shortforms: s, t, c
        if (trimmedEntity.matches("(?i)s")) {
            trimmedEntity = "student";
        } else if (trimmedEntity.matches("(?i)t")) {
            trimmedEntity = "tutor";
        } else if (trimmedEntity.matches("(?i)c")) {
            trimmedEntity = "class";
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
        // a school name should be capitalised and no dup whitespace
        trimmedSchool = removeDuplicateWhitespace(trimmedSchool);
        trimmedSchool = capitaliseOnlyFirstLetter(trimmedSchool);
        return new School(trimmedSchool);
    }

    /**
     * Parses {@code String level} into an {@code Level} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Level parseLevel(String level) throws ParseException {
        requireNonNull(level);
        String trimmedLevel = level.trim().toLowerCase();


        if (!Level.isValidLevel(trimmedLevel)) {
            // did you mean? message prompted when string contains level name at most 2 chars
            // or if string starts with at least 2 chars of a level full word
            if (trimmedLevel.matches("(?i).*prima.*|pr.*")) {
                throw new ParseException(Level.MESSAGE_CONSTRAINTS + Level.MESSAGE_DID_YOU_MEAN_PRIMARY);
            } else if (trimmedLevel.matches("(?i).*seconda.*|se.*")) {
                throw new ParseException(Level.MESSAGE_CONSTRAINTS + Level.MESSAGE_DID_YOU_MEAN_SECONDARY);
            }
            throw new ParseException(Level.MESSAGE_CONSTRAINTS);
        }
        //p, pri, s, sec converted to primary/secondary
        trimmedLevel = convertShortFormLevel(trimmedLevel);

        //allow primary[space]number or secondary[space]number
        trimmedLevel = removeWhitespaceForLevel(trimmedLevel);

        return Level.createLevel(trimmedLevel);
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
        // a qualification name should have no dup whitespace and comma
        trimmedQualification = removeDuplicateWhitespace(trimmedQualification);
        trimmedQualification = removeDuplicateComma(trimmedQualification);
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
        // a institution name should have no dup whitespace
        trimmedInstitution = removeDuplicateWhitespace(trimmedInstitution);
        return new Institution(trimmedInstitution);
    }

    /**
     * Parses {@code String subject} into an {@code Subject} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim().toLowerCase();
        if (!Subject.isValidSubject(trimmedSubject)) {
            // did you mean? message prompted when string contains Subject full word missing at most 2 chars
            // or if string starts with at least 2 chars of the Subject full word
            if (trimmedSubject.matches("(?i).*engli.*|en.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_ENGLISH);
            } else if (trimmedSubject.matches("(?i).*mathemati.*|ma.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_MATHEMATICS);
            } else if (trimmedSubject.matches("(?i).*physi.*|ph.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_PHYSICS);
            } else if (trimmedSubject.matches("(?i).*chemist.*|ch.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_CHEMISTRY);
            } else if (trimmedSubject.matches("(?i).*biolo.*|bi.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_BIOLOGY);
            } else if (trimmedSubject.matches("(?i).*elementa.*|el.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_EMATH);
            } else if (trimmedSubject.matches("(?i).*addition.*|ad.*")) {
                throw new ParseException(Subject.MESSAGE_CONSTRAINTS + Subject.MESSAGE_DID_YOU_MEAN_AMATH);
            }
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }

        // allow shortforms
        if (trimmedSubject.matches("(?i)english|eng")) {
            trimmedSubject = "english";
        } else if (trimmedSubject.matches("(?i)mathematics|maths{0,1}")) {
            trimmedSubject = "mathematics";
        } else if (trimmedSubject.matches("(?i)physics|phys{0,1}")) {
            trimmedSubject = "physics";
        } else if (trimmedSubject.matches("(?i)chemistry|chem")) {
            trimmedSubject = "chemistry";
        } else if (trimmedSubject.matches("(?i)biology|bio")) {
            trimmedSubject = "biology";
        } else if (trimmedSubject.matches("(?i)(e|elem|elementary)\\s*(mathematics|maths{0,1})")) {
            trimmedSubject = "emath";
        } else if (trimmedSubject.matches("(?i)(a|add|additional)\\s*(mathematics|maths{0,1})")) {
            trimmedSubject = "amath";
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
        String trimmedDay = day.trim().toLowerCase();

        if (!Day.isValidDay(trimmedDay)) {
            if (trimmedDay.matches("(?i).*mond.*|mo.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_MONDAY);
            } else if (trimmedDay.matches("(?i).*tuesd.*|tu.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_TUESDAY);
            } else if (trimmedDay.matches("(?i).*wednesd.*|we.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_WEDNESDAY);
            } else if (trimmedDay.matches("(?i).*thursd.*|th.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_THURSDAY);
            } else if (trimmedDay.matches("(?i).*frid.*|fr.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_FRIDAY);
            } else if (trimmedDay.matches("(?i).*saturd.*|sa.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_SATURDAY);
            } else if (trimmedDay.matches("(?i).*sund.*|su.*")) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS + Day.MESSAGE_DID_YOU_MEAN_SUNDAY);
            }

            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }

        // allow shortforms: mon,tue,wed,thu,fri
        if (trimmedDay.matches("(?i)mon")) {
            trimmedDay = "monday";
        } else if (trimmedDay.matches("(?i)tue|tues")) {
            trimmedDay = "tuesday";
        } else if (trimmedDay.matches("(?i)wed")) {
            trimmedDay = "wednesday";
        } else if (trimmedDay.matches("(?i)thu|thur|thurs")) {
            trimmedDay = "thursday";
        } else if (trimmedDay.matches("(?i)fri")) {
            trimmedDay = "friday";
        } else if (trimmedDay.matches("(?i)sat")) {
            trimmedDay = "saturday";
        } else if (trimmedDay.matches("(?i)sun")) {
            trimmedDay = "sunday";
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
        String[] splitTime = trimmedTime.split("\\s*(-|to|\\s)\\s*");
        try {
            splitTime[0] = formatTime(splitTime[0]);
            splitTime[1] = formatTime(splitTime[1]);
        } catch (ParseException e) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(formatTime(splitTime[0]), formatTime(splitTime[1]));
    }

    /**
     * Parses {@code String relationship} into an {@code Relationship} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code relationship} is invalid.
     */
    public static Relationship parseRelationship(String relationship) throws ParseException {
        requireNonNull(relationship);
        String trimmedRelationship = relationship.trim();
        if (!Relationship.isValidRelationship(trimmedRelationship)) {
            throw new ParseException(Relationship.MESSAGE_CONSTRAINTS);
        }
        return Relationship.createRelationship(trimmedRelationship);
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
        trimmedName = removeDuplicateWhitespace(trimmedName);
        trimmedName = capitaliseOnlyFirstLetter(trimmedName);
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
        trimmedName = removeDuplicateWhitespace(trimmedName);
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
        trimmedAddress = removeDuplicateWhitespace(trimmedAddress);
        trimmedAddress = capitaliseOnlyFirstLetter(trimmedAddress);
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
