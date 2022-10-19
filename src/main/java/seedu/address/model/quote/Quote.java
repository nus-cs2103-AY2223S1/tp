package seedu.address.model.quote;

import static java.util.Objects.requireNonNull;

/**
 * Class representing a quote.
 */
class Quote {
    private String message;
    private String quotee;

    Quote(String message, String quotee) {
        requireNonNull(message);
        requireNonNull(quotee);
        this.message = message;
        this.message = quotee;
    }

    Quote(String message) {
        requireNonNull(message);
        this.message = message;
        this.message = "Anonymous";
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quote // instanceof handles nulls
                        && message.equals(((Quote) other).message));
    }

    @Override
    public String toString() {
        return message + " - " + quotee;
    }
}
