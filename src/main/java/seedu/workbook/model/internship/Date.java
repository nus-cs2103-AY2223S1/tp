package seedu.workbook.model.internship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date in WorkBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    private static final String datePattern = "dd-MM-yyyy HH:mm";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
    private static final String showcaseDatePattern = "d-MMM-yyyy HH:mm";
    private static final DateTimeFormatter showcaseDateFormatter = DateTimeFormatter.ofPattern(showcaseDatePattern);

    public static final String MESSAGE_CONSTRAINTS = "Date should be dd-mm-yyyy hh:mm";

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        if (!date.isEmpty()) {
            checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
            String str = String.join(" ", date.split("\\s+", 2));
            this.value = LocalDateTime.parse(str, dateFormatter).format(showcaseDateFormatter);
        } else {
            this.value = "";
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            String str = String.join(" ", test.split("\\s+", 2));
            LocalDate.parse(str, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
