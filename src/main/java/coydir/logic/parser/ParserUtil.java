package coydir.logic.parser;

import static coydir.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import coydir.commons.core.index.Index;
import coydir.commons.util.StringUtil;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.Address;
import coydir.model.person.Department;
import coydir.model.person.Email;
import coydir.model.person.Leave;
import coydir.model.person.Name;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.model.person.Rating;
import coydir.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_NUMBER =
            "Number should be a positive integer (non-zero, not too large).\n"
            + "Check out our User Guide for more information by entering 'help'!";
    public static final String MESSAGE_INVALID_ARGUMENT = "Keyword argument cannot be empty.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a user-specified keyword into an {@code String} and returns it trimmed and in lower-case.
     * @throws ParseException if the specified keyword is empty.
     */
    public static String parseKeyword(String keyword) throws ParseException {
        String trimmedKeyword = keyword.trim();
        if (trimmedKeyword.equals("")) {
            throw new ParseException(MESSAGE_INVALID_ARGUMENT);
        }
        return trimmedKeyword;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code String} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer less than MAX_INT).
     */
    public static String parseNumber(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER);
        }
        return trimmedIndex;
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
     * Parses a {@code String position} into an {@code Position}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the  given {@code position} is invalid.
     */
    public static Position parsePosition(String position) throws ParseException {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        if (!Position.isValidPosition(trimmedPosition)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }
        return new Position(trimmedPosition);
    }

    /**
     * Parses a {@code String department} into an {@code Department}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the  given {@code department} is invalid.
     */
    public static Department parseDepartment(String department) throws ParseException {
        requireNonNull(department);
        String trimmedDepartment = department.trim();
        if (!Department.isValidDepartment(trimmedDepartment)) {
            throw new ParseException(Department.MESSAGE_CONSTRAINTS);
        }
        return new Department(trimmedDepartment);
    }

    /**
     * Parses a {@code String Rating} into an {@code Rating}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the  given {@code leave period} is invalid.
     */
    public static Rating parseRating(String rating) throws ParseException {
        requireNonNull(rating);
        String trimmedRating = rating.trim();
        if (!Rating.isValidRating(trimmedRating)) {
            throw new ParseException(Rating.MESSAGE_CONSTRAINTS);
        }

        return new Rating(trimmedRating);
    }

    /**
     * Parses a {@code String leave period} into an {@code Leave}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the  given {@code leave period} is invalid.
     */
    public static Leave parseLeave(String leaveStart, String leaveEnd) throws ParseException {
        requireAllNonNull(leaveStart, leaveEnd);
        String start = leaveStart.trim();
        String end = leaveEnd.trim();
        if (!Leave.isValidLeave(start, end)) {
            throw new ParseException(Leave.MESSAGE_CONSTRAINTS);
        }

        return new Leave(start, end);
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
     * Parses a {@code String filename}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code filename} length is less than 1.
     */
    public static String parseFileName(String filename) throws ParseException {
        requireNonNull(filename);
        String trimmedFileName = filename.trim();
        if (trimmedFileName.length() < 1) {
            throw new ParseException("Fail");
        }
        return trimmedFileName;
    }
}
