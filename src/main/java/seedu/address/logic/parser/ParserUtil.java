package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.InternshipId;
import seedu.address.model.internship.InternshipRole;
import seedu.address.model.internship.InternshipStatus;
import seedu.address.model.internship.InterviewDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonId;
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
     * Parses {@code zeroBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseLinkIndex(String zeroBasedIndex) throws ParseException {
        if (zeroBasedIndex == null) {
            return null;
        }
        String trimmedIndex = zeroBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromZeroBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String personId} into a {@code PersonId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code personId} is invalid.
     */
    public static PersonId parsePersonId(String personId) throws ParseException {
        if (personId == null) {
            return null;
        }
        String trimmedPersonId = personId.trim();
        if (!PersonId.isValidId(trimmedPersonId)) {
            throw new ParseException(PersonId.MESSAGE_CONSTRAINTS);
        }
        return new PersonId(trimmedPersonId);
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
     * Parses a {@code String internshipId} into a {@code InternshipId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code internshipId} is invalid.
     */
    public static InternshipId parseInternshipId(String internshipId) throws ParseException {
        if (internshipId == null) {
            return null;
        }
        String trimmedInternshipId = internshipId.trim();
        if (!InternshipId.isValidId(trimmedInternshipId)) {
            throw new ParseException(InternshipId.MESSAGE_CONSTRAINTS);
        }
        return new InternshipId(trimmedInternshipId);
    }

    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedCompanyName = companyName.trim();
        if (!CompanyName.isValidName(trimmedCompanyName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedCompanyName);
    }

    /**
     * Parses a {@code String internshipRole} into a {@code InternshipRole}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code internshipRole} is invalid.
     */
    public static InternshipRole parseInternshipRole(String internshipRole) throws ParseException {
        requireNonNull(internshipRole);
        String trimmedInternshipRole = internshipRole.trim();
        if (!InternshipRole.isValidName(trimmedInternshipRole)) {
            throw new ParseException(InternshipRole.MESSAGE_CONSTRAINTS);
        }
        return new InternshipRole(trimmedInternshipRole);
    }

    /**
     * Parses a {@code String internshipStatus} into a {@code InternshipStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code internshipStatus} is invalid.
     */
    public static InternshipStatus parseInternshipStatus(String internshipStatus) throws ParseException {
        requireNonNull(internshipStatus);
        String trimmedInternshipStatus = internshipStatus.trim();
        if (!InternshipStatus.isValidStatus(trimmedInternshipStatus)) {
            throw new ParseException(InternshipStatus.MESSAGE_CONSTRAINTS);
        }
        return new InternshipStatus(InternshipStatus.State.valueOf(trimmedInternshipStatus));
    }

    /**
     * Parses a {@code String interviewDate} into a {@code InterviewDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewDate} is invalid.
     */
    public static InterviewDate parseInterviewDate(String interviewDate) throws ParseException {
        requireNonNull(interviewDate);
        String trimmedInterviewDate = interviewDate.trim();
        if (!InterviewDate.isValidDatetimeStr(trimmedInterviewDate)) {
            throw new ParseException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDate(trimmedInterviewDate);
    }
}
