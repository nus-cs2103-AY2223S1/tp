package seedu.address.logic.parser.tutorial;

import java.util.Objects;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Venue;
import seedu.address.model.tutorial.TutorialName;


/**
 * Contains utility methods used for parsing Tutorial and related subclasses.
 */
public class TutorialParserUtil {
    /**
     * Parses a {@code String name} into a {@code TutorialName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TutorialName parseTutorialName(String name) throws ParseException {
        Objects.requireNonNull(name);
        String trimmedName = name.trim();
        if (!TutorialName.isValidName(trimmedName)) {
            throw new ParseException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        String formattedName = trimmedName.toUpperCase();
        return new TutorialName(formattedName);
    }

    /**
     * Parses a {@code String venue} into a {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Venue parseTutorialVenue(String venue) throws ParseException {
        Objects.requireNonNull(venue);
        String trimmedName = venue.trim();
        if (!Venue.isValidVenue(trimmedName)) {
            throw new ParseException(Venue.MESSAGE_CONSTRAINTS);
        }
        return new Venue(trimmedName);
    }
}
