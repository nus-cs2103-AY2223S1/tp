package seedu.address.model.appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an appointment for a patient.
 */
public abstract class Appointment {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_FORMAT_PARSING = "dd-MM-uuuu";
    public static final String MESSAGE_CONSTRAINTS = "Date string should be of the format " + DATE_FORMAT + ". "
            + "An upcoming appointment can only be on or after the current date "
            + "and a past appointment can only be on or before the current date.";
    private final LocalDate date;

    /**
     * Constructs an {@code Appointment} for a {@code Patient}.
     * @param date of the appointment
     */
    public Appointment(LocalDate date) {
        this.date = date;
    }

    /**
     * Constructs an {@code Appointment} for a {@code Patient}.
     * @param dateString string representation of date of the appointment
     */
    public Appointment(String dateString) {
        if (dateString.equals("")) {
            this.date = null;
        } else {
            try {
                this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_FORMAT_PARSING)
                        .withResolverStyle(java.time.format.ResolverStyle.STRICT));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
            }
        }
    }

    /**
     * Returns date of {@code Appointment}.
     * @return the date of the appointment
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test == null) {
            return true;
        }
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT_PARSING)
                    .withResolverStyle(java.time.format.ResolverStyle.STRICT));
            parseLocalDate(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Parses a string into a {@code LocalDate}.
     */
    public static LocalDate parseLocalDate(String str) throws DateTimeParseException {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
