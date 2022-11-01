package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a person's Appointment in the address group.
 */
public class Appointment {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String NO_APPOINTMENT_SCHEDULED = "No appointment scheduled!";
    public static final String MESSAGE_CONSTRAINTS = "Appointment dates have to be of format dd-MM-yyyy HHmm! "
            + "Please also ensure this is a valid date!";
    public static final String MESSAGE_DATE_PAST = "Appointment dates have to be scheduled in the future!";

    private final LocalDateTime appointmentDate;

    /**
     * Constructs an appointment with a given date.
     *
     * @param appointmentDate Date of the appointment.
     */
    private Appointment(String appointmentDate) {
        requireNonNull(appointmentDate);
        checkArgument(isValidDateFormat(appointmentDate), MESSAGE_INVALID_DATE_FORMAT);
        checkArgument(isFutureDate(appointmentDate), MESSAGE_DATE_PAST);
        this.appointmentDate = LocalDateTime.parse(appointmentDate, DATE_FORMAT);
    }

    /**
     * Constructs an empty Appointment.
     */
    private Appointment() {
        this.appointmentDate = null;
    }

    /**
     * Returns true if the appointment date is in the valid format.
     *
     * @param testDate Appointment date to be tested.
     * @return True if the appointment date is in the valid format.
     */
    public static boolean isValidDateFormat(String testDate) {
        try {
            LocalDateTime.parse(testDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the date is in the past.
     *
     * @param testDate Date to be tested
     * @return
     */
    public static boolean isFutureDate(String testDate) {
        LocalDateTime currDate = LocalDateTime.now();
        LocalDateTime actualDate = LocalDateTime.parse(testDate, DATE_FORMAT);
        // Use !isBefore to allow date to be today.
        return !actualDate.isBefore(currDate);
    }

    /**
     * Returns true is appointment is valid.
     *
     * @param test Input args for appointment test.
     * @return True if valid.
     */
    public static boolean isValidAppointment(String test) {
        return test.equals(NO_APPOINTMENT_SCHEDULED) || isValidDateFormat(test);
    }

    /**
     * Factory method for the construction of an appointment from the JSON storage file.
     *
     * @param in Json storage format of an appointment.
     * @return An appointment with the input field.
     */
    public static Appointment of(String in) {
        if (in.equals(NO_APPOINTMENT_SCHEDULED)) {
            return new Appointment();
        } else {
            return new Appointment(in);
        }
    }

    /**
     * Converts the appointment into string format for Jackson storage.
     *
     * @return Storage format for an appointment.
     */
    public String storageFormat() {
        if (this.appointmentDate == null) {
            return NO_APPOINTMENT_SCHEDULED;
        } else {
            return this.toString();
        }
    }

    @Override
    public String toString() {
        return appointmentDate == null ? NO_APPOINTMENT_SCHEDULED : appointmentDate.format(DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && appointmentDate.equals(((Appointment) other).appointmentDate));
    }

    @Override
    public int hashCode() {
        return appointmentDate.hashCode();
    }
}
