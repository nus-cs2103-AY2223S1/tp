package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ARGUMENT;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.github.GithubApi;
import seedu.address.github.exceptions.NetworkConnectionException;
import seedu.address.github.exceptions.UserInvalidException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Role;
import seedu.address.model.person.Timezone;
import seedu.address.model.person.contact.Email;
import seedu.address.model.person.contact.Phone;
import seedu.address.model.person.contact.Slack;
import seedu.address.model.person.contact.Telegram;
import seedu.address.model.person.github.User;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final GithubApi githubApi = new GithubApi();

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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, name, Name.MESSAGE_CONSTRAINTS));
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, role, Role.MESSAGE_CONSTRAINTS));
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String githubUser} into a {@code User}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code githubUser} is invalid.
     */
    public static User parseGithubUser(String githubUser)
            throws ParseException, UserInvalidException, NetworkConnectionException {
        requireNonNull(githubUser);
        String trimmedUser = githubUser.trim();
        if (!User.isValidUsername(trimmedUser)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, githubUser, User.MESSAGE_CONSTRAINTS));
        }
        return githubApi.getUser(trimmedUser);
    }

    /**
     * Parses a {@code String timezone} into a {@code Timezone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timezone} is invalid.
     */
    public static Timezone parseTimezone(String timezone) throws ParseException {
        requireNonNull(timezone);
        String trimmedTimezone = timezone.trim();
        if (!Timezone.isValidTimezone(trimmedTimezone)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, timezone, Timezone.MESSAGE_CONSTRAINTS));
        }
        return new Timezone(trimmedTimezone);
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
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, phone, Phone.MESSAGE_CONSTRAINTS));
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
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, address, Address.MESSAGE_CONSTRAINTS));
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
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, email, Email.MESSAGE_CONSTRAINTS));
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
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, tag, Tag.MESSAGE_CONSTRAINTS));
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
     * Parses a String slack into a Slack.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given slack is invalid.
     */
    public static Slack parseSlack(String slack) throws ParseException {
        requireNonNull(slack);
        String trimmedSlack = slack.trim();
        if (!Slack.isValidSlack(trimmedSlack)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, slack, Slack.MESSAGE_CONSTRAINTS));
        }
        return new Slack(trimmedSlack);
    }

    /**
     * Parses a String slack into a Slack.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given slack is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTele = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTele)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENT, telegram, Telegram.MESSAGE_CONSTRAINTS));
        }
        return new Telegram(trimmedTele);
    }
}
