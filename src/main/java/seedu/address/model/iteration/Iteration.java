package seedu.address.model.iteration;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Iteration in the address book.
 * Guarantees: date and description are present and not null, field values are validated, immutable.
 */
public class Iteration {

    // Identity fields
    private final Date date;
    private final Description description;
    // TODO add artwork (image) attribute

    // Optional fields
    private final Feedback feedback;

    /**
     * Constructs an Iteration.
     * Every field must be present and not null. Throws a NullPointerException if
     * any of the fields are null.
     *
     * @param date The creation date of the iteration.
     * @param description The description of the iteration.
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

    /**
     * Returns true if both iterations have the same image.
     * This defines a weaker notion of equality between two commissions.
     */
    public boolean isSameIteration(Iteration otherIteration) {
        if (otherIteration == this) {
            return true;
        }

        // return otherIteration != null
        //         && otherIteration.getArt().equals(getArt());
        // TODO implement isSameIteration once the artwork/ image attribute has been added
        return otherIteration != null
                && otherIteration.getDescription().equals(getDescription());
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
