package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Bill's date in the HealthContact.
 * Guarantees: immutable; is valid as declared in {@link #isValidBillDate  (String)}
 */
public class BillDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Bill date should be like 'yyyy-MM-dd'";

    public final LocalDate localDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a {@code Slot}.
     *
     * @param date A valid date of a bill date.
     */
    public BillDate(String date) {
        requireNonNull(date);
        checkArgument(isValidBillDate(date), MESSAGE_CONSTRAINTS);
        this.localDate = LocalDate.parse(date, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidBillDate(String input) {
        try {
            LocalDate test = LocalDate.parse(input, FORMATTER);
            return test.format(FORMATTER).equals(input);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return localDate.format(FORMATTER);
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
