package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

public class Request {
    public static final String MESSAGE_CONSTRAINTS = "The request should not be null";
    public final String value;

    public Request() {
        value = "Nothing";
    }

    public Request(String str) {
        value = str;
    }

    public static boolean isValidRequest(String str) {
        return !(str.trim().equals(""));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Request // instanceof handles nulls
                && value.equals(((Request) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}