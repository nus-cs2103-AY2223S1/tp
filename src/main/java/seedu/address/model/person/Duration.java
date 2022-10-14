package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Duration {

    public static final String MESSAGE_CONSTRAINTS =
            "Duration should only be in the format of HH:mm-HH:mm";

    public static final String VALIDATION_REGEX = "^(\\d){2}:(\\d){2}-(\\d){2}:(\\d){2}$";

    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm-HH-mm");
    public final LocalTime time;

    public Duration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(duration, DTF);
    }

    public static boolean isValidDuration(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return time.format(DTF);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        } else if (!(other instanceof Duration)) { //instanceof handles nulls
            return false;
        }
        Duration temp = (Duration) other;
        return temp.toString().equalsIgnoreCase(this.toString());
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}