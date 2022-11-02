package seedu.travelr.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.commons.util.StringUtil;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_EMPTY_INDEX = "Index cannot be empty.";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer. "
            + "(Index must be an integer more than 1, but less than or equal to 2147483647)";
    public static final String EVENT_DESCRIPTION_PLACEHOLDER =
            "This is a placeholder text for the event description.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        if (oneBasedIndex.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_INDEX);
        }

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
    public static Title parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Title.isValidTitle(trimmedName)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedName);
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.titleIsNotEmpty(trimmedTitle)) {
            throw new ParseException(Title.TITLE_EMPTY_MESSAGE);
        }
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
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
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static DateField parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!DateField.isValidDate(trimmedDate)) {
            throw new ParseException(DateField.MESSAGE_CONSTRAINTS);
        }
        return new DateField(trimmedDate);
    }

    /**
     * Parses a {@code String event} into a {@code Event}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Event parseEvent(String event) throws ParseException {
        requireNonNull(event);
        String trimmedTag = event.trim();
        if (!Event.isValidEventTitle(trimmedTag)) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }
        return new Event(new Title(trimmedTag), new Description(EVENT_DESCRIPTION_PLACEHOLDER));
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Event> parseEvents(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Event> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseEvent(tagName));
        }
        return tagSet;
    }
}
