package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a deadline for a task.
 */
public class Deadline {
    public static final Deadline UNSPECIFIED = new Deadline((LocalDate) null);
    public static final String UNSPECIFIED_DEADLINE_IDENTIFIER = "UNSPECIFIED";

    public static final String MESSAGE_CONSTRAINTS = "Deadline should be in DD-MM-YYYY format";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final LocalDate date;

    private Deadline(LocalDate date) {
        this.date = date;
    }

    /**
     * Creates a Deadline with the given string.
     * @param date the string representing the deadline
     */
    private Deadline(String date) throws ParseException {
        requireNonNull(date);
        try {
            this.date = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException pe) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    public static Deadline of(String date) throws ParseException {
        return new Deadline(date);
    }

    public static Deadline of(LocalDate date) {
        return new Deadline(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && date.equals(((Deadline) other).date));
    }

    public boolean isUnspecified() {
        return this == Deadline.UNSPECIFIED;
    }
    @Override
    public String toString() {
        if (this.isUnspecified()) {
            return UNSPECIFIED_DEADLINE_IDENTIFIER;
        }
        return this.date.format(formatter);
    }

    public LocalDate getDate() {
        return this.date;
    }

}

