package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.AppUtil.checkArgument;

public class Request {
    public static final String MESSAGE_CONSTRAINTS = "The request should not be null";
    public final String value;

    /**
     * The constructor with no specific request.
     */
    public Request() {
        value = "Nothing";
    }

    /**
     * The constructor with specific request.
     * @param str the specific request
     */
    public Request(String str) {
        requireNonNull(str);
        checkArgument(isValidRequest(str), MESSAGE_CONSTRAINTS);
        value = str;
    }

    /**
     * To check whether the request is valid.
     * @param str
     * @return boolean
     */
    public static boolean isValidRequest(String str) {
        return (!str.equals(""));
    }

    /**
     * return the String representation of request.
     * @return
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * to check whether this request is the same as another.
     * @param other other object, might be requeest
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Request // instanceof handles nulls
                && value.equals(((Request) other).value)); // state check
    }

    /**
     * To override hashcode.
     * @return integer
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
