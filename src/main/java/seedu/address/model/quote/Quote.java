package seedu.address.model.quote;

import static java.util.Objects.requireNonNull;

/**
 * Class representing a quote.
 */
public class Quote {
    private String message;
    private String quotee;

    Quote(String message, String quotee) {
        requireNonNull(message);
        requireNonNull(quotee);
        this.message = message;
        this.quotee = quotee;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quote // instanceof handles nulls
                        && message.equals(((Quote) other).message)
                        && quotee.equals(((Quote) other).quotee));
    }

    @Override
    public String toString() {
        return message + " - " + quotee;
    }
}
