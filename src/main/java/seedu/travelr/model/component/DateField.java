package seedu.travelr.model.component;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Trip's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateField {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should follow the format dd-mm-yyyy, and it should not be blank";

    public final LocalDate dateValue;
    private final static DateTimeFormatter df = DateTimeFormatter.ofPattern( "dd-MM-uuuu" ) ;
    
    private final static String DEFAULT_DATE_VALUE = "01-01-0000";
    private final static DateField DEFAULT_DATE_FIELD = new DateField(DEFAULT_DATE_VALUE);
    
    
    /**
     * Constructs a {@code Name}.
     *
     * @param date A valid name.
     */
    public DateField(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        dateValue = LocalDate.parse(date, df);
    }
    
    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, df);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    public static String getDefaultValue() {
        return DEFAULT_DATE_VALUE;
    }
    
    public static DateField getDefaultDateField() {
        return DEFAULT_DATE_FIELD;
    }
    
    public boolean isDefaultValue() {
        return this.equals(getDefaultDateField());
    }

    @Override
    public String toString() {
        return dateValue.format(df);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateField // instanceof handles nulls
                && dateValue.equals(((DateField) other).dateValue)); // state check
    }

    @Override
    public int hashCode() {
        return dateValue.hashCode();
    }

    public int compareTo(DateField dateField) {
        return dateValue.compareTo(dateField.dateValue);
    }

}
