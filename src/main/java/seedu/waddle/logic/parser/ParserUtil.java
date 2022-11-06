package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.commons.util.StringUtil;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.DayNumber;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.People;


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
        Index index = Index.fromOneBased(Integer.parseInt(trimmedIndex));
        if (index.getZeroBased() < 0) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return index;
    }

    /**
     * Parses {@code oneBasedMultiIndex} into an {@code MultiIndex} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified MultiIndex is invalid (not non-zero unsigned integer).
     */
    public static MultiIndex parseMultiIndex(String oneBasedMultiIndex) throws ParseException {
        String trimmedMultiIndex = oneBasedMultiIndex.trim();
        if (!MultiIndex.isValidMultiIndex(trimmedMultiIndex)) {
            throw new ParseException(MultiIndex.MESSAGE_CONSTRAINTS);
        }
        String[] oneBasedIndexList = trimmedMultiIndex.split("\\.", 2);
        MultiIndex multiIndex = new MultiIndex();
        for (String oneBasedIndex : oneBasedIndexList) {
            Index index = parseIndex(oneBasedIndex);
            multiIndex.addIndex(index);
        }
        return multiIndex;
    }


    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Description parseDescription(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Description.isValidDescription(trimmedName)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedName);
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
     * Parses a {@code String duration} into an {@code ItineraryDuration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static ItineraryDuration parseItineraryDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        if (!ItineraryDuration.isValidDuration(trimmedDuration)) {
            throw new ParseException(ItineraryDuration.MESSAGE_CONSTRAINTS);
        }
        return new ItineraryDuration(trimmedDuration);
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
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        Integer stars = Integer.parseInt(trimmedPriority);
        if (!Priority.isValidPriority(stars)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(stars);
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

    /**
     * Parses a {@code int Day Number}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static DayNumber parseDayNumber(String dayNumber) throws ParseException {
        requireNonNull(dayNumber);
        String trimmedDayNumber = dayNumber.trim();
        if (!DayNumber.isValidDayNumber(trimmedDayNumber)) {
            throw new ParseException(DayNumber.MESSAGE_CONSTRAINTS);
        }
        return new DayNumber(trimmedDayNumber);
    }

    /**
     * Parses a {@code String startTime} into a {@code StartTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static LocalTime parseStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        LocalTime time;
        try {
            time = LocalTime.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new ParseException("Start time should be written in HH:MM:SS format. For example, 10:15 or 10:15:30");
        }
        return time;
    }

}
