package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.team.Description;
import seedu.address.model.team.TeamName;
import seedu.address.model.team.Url;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATETIME = "DateTime should have the following format yyyy-MM-dd HH:mm"
            + " (e.g. 2022-01-01 10:01)";

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
     * Parses {@code String url} into a {@code Url}
     */
    public static Url parseUrl(String url) throws ParseException {
        requireNonNull(url);
        String trimmedUrl = url.trim();

        String trimmedUrlWithHttps = Url.isValidUrl(trimmedUrl)
                ? trimmedUrl //If it is already a valid url, do not append.
                : trimmedUrl.startsWith("http") | trimmedUrl.startsWith("https")
                ? trimmedUrl //If it starts with http or https, do not append.
                : "https://" + trimmedUrl;

        if (!Url.isValidUrl(trimmedUrlWithHttps)) {
            throw new ParseException(Url.MESSAGE_CONSTRAINTS);
        }
        return new Url(trimmedUrlWithHttps);
    }

    /**
     * Parses {@code String datetime} into a {@code LocalDateTime}
     */
    public static LocalDateTime parseLocalDateTime(String datetime) throws ParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_DATETIME);
        }
    }

    /**
     * Parses a {@code String name} into a {@code TeamName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teamName} is invalid.
     */
    public static TeamName parseTeamName(String teamName) throws ParseException {
        requireNonNull(teamName);
        String trimmedName = teamName.trim();
        if (!TeamName.isValidTeamName(trimmedName)) {
            throw new ParseException(TeamName.MESSAGE_CONSTRAINTS);
        }
        return new TeamName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedName = description.trim();
        if (!Description.isValidTeamDescription(trimmedName)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedName);
    }

    /**
     * Parses {@code String order} into a {@code Order}
     */
    public static Order parseOrder(String order) throws ParseException {
        switch (order.trim().toLowerCase()) {
        case "asc":
            return Order.ASCENDING;
        case "dsc":
            return Order.DESCENDING;
        case "res":
            return Order.RESET;
        default:
            throw new ParseException("Enter a valid order to sort by!");
        }
    }

}
