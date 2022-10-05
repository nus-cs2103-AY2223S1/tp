package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Telegram {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handle should not have any spaces in between";
    public final String telegram;
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";


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
