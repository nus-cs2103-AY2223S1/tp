package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Appointment's slot in the health contact.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime (String)}
 */
public class Slot {


    public static final String MESSAGE_CONSTRAINTS =
            "Slot should be like 'yyyy-MM-dd HH:mm'";

    public final LocalDateTime localDateTime;

    /**
     * Constructs a {@code Slot}.
     *
     * @param dateTime A valid dateTime of a slot.
     */
    public Slot(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));;
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Slot // instanceof handles nulls
                && localDateTime.equals(((Slot) other).localDateTime)); // state check
    }

    @Override
    public int hashCode() {
        return localDateTime.hashCode();
    }
}
