package hobbylist.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import hobbylist.commons.core.index.Index;
import hobbylist.commons.util.StringUtil;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.activity.Status;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;


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
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
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
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     * The String date is in yyyy-mm-dd format
     * @throws ParseException if the given {@code date} is invalid or out of calendar.
     */
    public static Optional<Date> parseDate(Optional<String> optionalDateString) throws ParseException {
        requireNonNull(optionalDateString);
        Optional<Date> optionalDate = Optional.empty();
        if (optionalDateString.isPresent()) {
            String dateString = optionalDateString.get();
            String trimmedD = dateString.trim();
            if (!Date.isValidDateString(trimmedD)) {
                throw new ParseException(Date.MESSAGE_EXCEPTION);
            }
            try {
                optionalDate = Optional.of(new Date(dateString));
            } catch (DateTimeParseException de) {
                throw new ParseException("Sorry, the input date is out of human calendar scope.");
            }

        } else {
            optionalDate = Optional.ofNullable(null);
        }
        return optionalDate;
    }

    // Solution adapted from https://github.com/AY2021S1-CS2103T-F11-3/tp/pull/124/files

    /**
     * Parses a {@code String status} into a {@code Status}
     *
     * @throws ParseException
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String completionStatus = status.trim().toUpperCase();
        if (completionStatus.equals(Status.STATUS_UPCOMING)) {
            return new Status(Status.STATUS_UPCOMING);
        }
        if (completionStatus.equals(Status.STATUS_ONGOING)) {
            return new Status(Status.STATUS_ONGOING);
        }
        if (completionStatus.equals(Status.STATUS_COMPLETED)) {
            return new Status(Status.STATUS_COMPLETED);
        }
        throw new ParseException(Status.MESSAGE_CONSTRAINT);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            if (tagName.length() > 15) {
                throw new ParseException(Tag.TAG_NAME_TOO_LONG);
            }
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
