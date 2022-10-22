package seedu.address.model.task;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a deadline for a task.
 */
public class Deadline {
    public static final Deadline UNSPECIFIED = new Deadline((LocalDate) LocalDate.ofEpochDay(0));
    public static final String UNSPECIFIED_DEADLINE_IDENTIFIER = "UNSPECIFIED";

    public static final String MESSAGE_CONSTRAINTS = "Deadline should be in DD-MM-YYYY format";
    public static final DateTimeFormatter READABLE_FORMATTER_WITH_YEAR =
            DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");
    public static final DateTimeFormatter READABLE_FORMATTER_WITHOUT_YEAR =
            DateTimeFormatter.ofPattern("EEE, dd MMM");
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

    /**
     * Creates a Deadline with a given date object.
     * @param date the date to be converted into a Deadline
     */
    public static Deadline of(Date date) {
        LocalDate localDate =
                date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

        return new Deadline(localDate);
    }
    public static Deadline of(LocalDate date) {
        return new Deadline(date);
    }

    /**
     * Returns true if the deadline set for the task is before {@code date}.
     */
    public boolean isBefore(Deadline deadline) {
        if (isUnspecified() || deadline.isUnspecified()) {
            return true;
        }
        return date.isBefore(deadline.date);
    }

    /**
     * Returns true if the deadline set for the task is after {@code date}.
     */
    public boolean isAfter(Deadline deadline) {
        if (isUnspecified() || deadline.isUnspecified()) {
            return true;
        }
        return date.isAfter(deadline.date);
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
        if (isUnspecified()) {
            return UNSPECIFIED_DEADLINE_IDENTIFIER;
        }
        return date.format(formatter);
    }

    /**
     * Formats the Deadline into a more readable format.
     * @return
     */
    public String formatForUi() {
        LocalDate startOfYear = LocalDate.now().with(firstDayOfYear());
        LocalDate endOfYear = LocalDate.now().with(lastDayOfYear());

        if (isUnspecified()) {
            return UNSPECIFIED_DEADLINE_IDENTIFIER;
        } else if (date.isBefore(endOfYear) && date.isAfter(startOfYear)) {
            return date.format(READABLE_FORMATTER_WITHOUT_YEAR);
        }
        return date.format(READABLE_FORMATTER_WITH_YEAR);
    }

    public LocalDate getDate() {
        return date;
    }

}

