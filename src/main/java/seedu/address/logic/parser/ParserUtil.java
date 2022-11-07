package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tutorial;
import seedu.address.model.social.Social;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        oneBasedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(oneBasedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(oneBasedIndex));
    }

    /**
     * Parses {@code trimmedField} and returns it if valid.
     *
     * @throws ParseException if the specified field is invalid.
     */
    public static String parseField(String trimmedField) throws ParseException {
        if (StringUtil.isEmptyString(trimmedField)) {
            return null;
        } else if (!StringUtil.isValidField(trimmedField)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return trimmedField;
    }

    /**
     * Parses {@code social} into a String and returns it
     *
     * @param social
     * @return String
     * @throws ParseException
     */
    public static String parseSocial(String social) throws ParseException {
        requireNonNull(social);
        String trimmedsocial = social.trim();
        if (!Social.isValidSocial(trimmedsocial)) {
            throw new ParseException(Social.MESSAGE_CONSTRAINTS);
        }
        return trimmedsocial;
    }

    /**
     * Parses {@code occupation} into an {@code Occupation} and returns it
     *
     * @param occupation
     * @return Occupation
     * @throws ParseException
     */
    public static Occupation parseOccupation(String occupation) throws ParseException {
        requireNonNull(occupation);
        String trimmedOccupation = occupation.trim();
        if (!Occupation.isValidOccupation(trimmedOccupation)) {
            throw new ParseException(Occupation.MESSAGE_CONSTRAINTS);
        }
        return new Occupation(trimmedOccupation);
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
     * Parses a {@code String tutorial} into an {@code Tutorial}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tutorial tutorial
     * @return a valid tutorial
     * @throws ParseException if the given {@code tutorial} is invalid.
     */
    public static Tutorial parseTutorial(String tutorial) throws ParseException {
        requireNonNull(tutorial);
        String trimmedTutorial = tutorial.trim();
        if (!Tutorial.isValidTutorial(trimmedTutorial)) {
            throw new ParseException(Tutorial.MESSAGE_CONSTRAINTS);
        }
        return new Tutorial(trimmedTutorial);
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
     * Parses a {@code String group} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code group} is invalid.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        String trimmedGroup = group.trim();
        if (!Group.isValidGroupName(trimmedGroup)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedGroup);
    }

    /**
     * Parses {@code Collection<String> groups} into a {@code Set<Group>}.
     */
    public static Set<Group> parseGroups(Collection<String> groups) throws ParseException {
        requireNonNull(groups);
        final Set<Group> groupSet = new HashSet<>();
        for (String groupName : groups) {
            groupSet.add(parseGroup(groupName));
        }
        return groupSet;
    }
}
