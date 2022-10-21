package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.util.StringUtil;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.*;


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
     * Parses a {@code String country} into a {@code Country}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code country} is invalid.
     */
    public static Country parseCountry(String country) throws ParseException {
        requireNonNull(country);
        String trimmedCountry = country.trim();
        if (!Country.isValidCountry(trimmedCountry)) {
            throw new ParseException(Country.MESSAGE_CONSTRAINTS);
        }
        return new Country(trimmedCountry);
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String people} into an {@code People}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code people} is invalid.
     */
    public static People parsePeople(String people) throws ParseException {
        requireNonNull(people);
        String trimmedPeople = people.trim();
        if (!People.isValidPeople(trimmedPeople)) {
            throw new ParseException(People.MESSAGE_CONSTRAINTS);
        }
        return new People(trimmedPeople);
    }

    /**
     * Parses a {@code String budget} into an {@code Budget}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code budget} is invalid.
     */
    public static Budget parseBudget(String budget) throws ParseException {
        requireNonNull(budget);
        String trimmedBudget = budget.trim();
        if (!Budget.isValidBudget(trimmedBudget)) {
            throw new ParseException(Budget.MESSAGE_CONSTRAINTS);
        }
        return new Budget(trimmedBudget);
    }

    /**
     * Parses a {@code String description}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        // TODO: implement Description class
        /*
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }

        return new Description(trimmedDescription);
         */
        return trimmedDescription;
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String cost} into a {@code Cost}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cost} is invalid.
     */
    public static Cost parseCost(String cost) throws ParseException {
        requireNonNull(cost);
        String trimmedCost = cost.trim();
        if (!Cost.isValidCost(trimmedCost)) {
            throw new ParseException(Cost.MESSAGE_CONSTRAINTS);
        }
        return new Cost(trimmedCost);
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        if (!Duration.isValidDuration(trimmedDuration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        return new Duration(trimmedDuration);
    }

}
