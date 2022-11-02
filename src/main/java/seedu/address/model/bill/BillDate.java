package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Bill's date in the HealthContact.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate  (String)}
 */
public class BillDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Bill date should be like 'yyyy-MM-dd'";

    public final LocalDate localDate;

    /**
     * Constructs a {@code Slot}.
     *
     * @param date A valid date of a bill date.
     */
    public BillDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BillDate // instanceof handles nulls
                && localDate.equals(((BillDate) other).localDate)); // state check
    }

    @Override
    public int hashCode() {
        return localDate.hashCode();
    }
}
