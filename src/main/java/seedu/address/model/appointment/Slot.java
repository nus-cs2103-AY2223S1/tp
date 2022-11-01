package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Appointment's slot in the HealthContact.
 * Guarantees: immutable; is valid as declared in {@link #isValidSlot (String)}
 */
public class Slot {


    public static final String MESSAGE_CONSTRAINTS =
            "Slot should be like 'yyyy-MM-dd HH:mm'";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final LocalDateTime localDateTime;

    /**
     * Constructs a {@code Slot}.
     *
     * @param dateTime A valid dateTime of a slot.
     */
    public Slot(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidSlot(dateTime), MESSAGE_CONSTRAINTS);
        this.localDateTime = LocalDateTime.parse(dateTime, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidSlot(String input) {
        try {
            LocalDateTime.parse(input, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return localDateTime.format(FORMATTER);
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
