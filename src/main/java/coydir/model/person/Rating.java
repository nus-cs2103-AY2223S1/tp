package coydir.model.person;

import static coydir.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import coydir.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's rating in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Rating {
    public static final String MESSAGE_CONSTRAINTS =
            "Ratings can take any integer values from 1 - 5 inclusive, and it should not be blank\n"
          + "5: Outstanding | 4: Exceeds Expectations | 3: Satisfactory | 2: Needs Improvement | 1: Unsatisfactory.";
    public static final String MESSAGE_CONSTRAINTS_TIMESTAMP = "Invalid Timestamp, please follow dd-MM-yyyy";
    public static final String VALIDATION_REGEX = "[1-5]";
    private static final Rating NULL = new Rating();
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate timestamp;
    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        this.value = rating;
        this.timestamp = LocalDate.now();
    }

    /**
     * Constructs a {@code Rating}.
     * @param rating A valid rating.
     * @param timestamp Timestamp when rating is added.
     * @throws ParseException
     */
    public Rating(String rating, String timestamp) throws ParseException {
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        this.value = rating;
        try {
            this.timestamp = LocalDate.parse(timestamp, FORMAT);
        } catch (DateTimeParseException dtpe) {
            throw new ParseException(MESSAGE_CONSTRAINTS_TIMESTAMP);
        }
    }

    /**
     * Bypass input validation, allows actual null values.
     */
    public Rating() {
        this.value = "N/A";
        this.timestamp = LocalDate.now();
    }

    public static Rating getNullRating() {
        return Rating.NULL;
    }

    public LocalDate getTime() {
        return this.timestamp;
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && this.value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, timestamp);
    }
}
