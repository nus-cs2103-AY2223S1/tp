package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Patient's home-visit's date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in yyyy-MM-dd";

    /*
     * https://mkyong.com/regular-expressions/how-to-validate-date-with-regular-expression/
     * The Date can only be in yyyy-mm-dd format without any space.
     */
    public static final String VALIDATION_REGEX= "((?:20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid datetime.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }


    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
