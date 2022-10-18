package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's telegram handle in the SETA application.
 */
public class Telegram {

    public static final String MESSAGE_CONSTRAINTS =
            "Telegram handle should start with '@' and only contain alphanumeric characters or underscore afterwards, "
                    + "it should not be blank or have spaces.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "@" + "[\\p{Alnum}][\\p{Alnum}'_']*";

    public final String telegram;

    /**
     * Constructs a {@code Telegram}.
     *
     * @param telegram A valid telegram handle.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        this.telegram = telegram;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     */
    public static boolean isValidTelegram(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return telegram;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Telegram // instanceof handles nulls
                && telegram.equals(((Telegram) other).telegram)); // state check
    }

    @Override
    public int hashCode() {
        return telegram.hashCode();
    }
}
