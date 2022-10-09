package seedu.address.model.iteration;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Iteration's feedback in the address book.
 * Guarantees: immutable; any feedback is valid
 */
public class Feedback {

    private final String feedback;

    /**
     * Constructs a {@code Feedback}.
     *
     * @param feedback A non-null feedback.
     */
    public Feedback(String feedback) {
        requireNonNull(feedback);
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return feedback;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Feedback)) {
            return false;
        }

        Feedback otherFeedback = (Feedback) other;
        return otherFeedback.feedback.equals(feedback);
    }

    @Override
    public int hashCode() {
        return feedback.hashCode();
    }
}
