package seedu.address.logic.parser.tutorial;

import java.util.Objects;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialVenue;


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
        return new TutorialName(trimmedName);
    }

    /**
     * Parses a {@code String moduleName} into a {@code TutorialModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static TutorialModule parseTutorialModule(String moduleName) throws ParseException {
        Objects.requireNonNull(moduleName);
        String trimmedName = moduleName.trim();
        if (!TutorialModule.isValidModule(trimmedName)) {
            throw new ParseException(TutorialModule.MESSAGE_CONSTRAINTS);
        }
        return new TutorialModule(trimmedName);
    }

    /**
     * Parses a {@code String venue} into a {@code TutorialVenue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static TutorialVenue parseTutorialVenue(String venue) throws ParseException {
        Objects.requireNonNull(venue);
        String trimmedName = venue.trim();
        if (!TutorialVenue.isValidVenue(trimmedName)) {
            throw new ParseException(TutorialVenue.MESSAGE_CONSTRAINTS);
        }
        return new TutorialVenue(trimmedName);
    }
}
