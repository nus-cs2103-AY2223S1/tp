package seedu.address.model.iteration;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Iteration in the address book.
 * Guarantees: all details are present and not null, field values are validated, immutable.
 */
public class Iteration {

    // Identity fields
    private final Date date;
    private final Description description;
    private final Feedback feedback;
    // TODO add artwork (image) attribute

    /**
     * Constructs an Iteration.
     * Every field must be present and not null. Throws a NullPointerException if
     * any of the fields are null.
     *
     * @param date The creation date of the iteration.
     * @param description The description of the iteration.
     * @param feedback The feedback given to the iteration by the customer.
     */
    public Iteration(Date date, Description description, Feedback feedback) {
        requireAllNonNull(date, description, feedback);
        this.date = date;
        this.description = description;
        this.feedback = feedback;
    }

    public Date getDate() {
        return date;
    }

    public Description getDescription() {
        return description;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof Iteration)) {
            return false;
        }

        Iteration otherIteration = (Iteration) object;
        return otherIteration.getDate().equals(getDate())
            && otherIteration.getDescription().equals(getDescription())
            && otherIteration.getFeedback().equals(getFeedback());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, feedback);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(getDate())
                .append("; Description: ")
                .append(getDescription())
                .append("; Feedback: ")
                .append(getFeedback());

        return builder.toString();
    }
}
