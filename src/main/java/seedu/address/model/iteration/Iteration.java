package seedu.address.model.iteration;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents an Iteration in the ArtBuddy.
 * Guarantees: date and description are present and not null, field values are validated, immutable.
 */
public class Iteration {

    // Identity fields
    private final Date date;
    private final IterationDescription description;
    private final Feedback feedback;
    private final Path imagePath;

    /**
     * Constructs an Iteration.
     * Every field must be present and not null. Throws a NullPointerException if
     * any of the fields are null.
     *
     * @param date The creation date of the iteration.
     * @param description The description of the iteration.
     */
    public Iteration(Date date, IterationDescription description, Path imagePath, Feedback feedback) {
        requireAllNonNull(date, description, imagePath, feedback);
        this.date = date;
        this.description = description;
        this.imagePath = imagePath;
        this.feedback = feedback;
    }

    public Date getDate() {
        return date;
    }

    public IterationDescription getDescription() {
        return description;
    }

    public Path getImagePath() {
        return imagePath;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    /**
     * Returns true if both iterations have the same imagePath.
     * This defines a weaker notion of equality between two iterations.
     */
    public boolean isSameIteration(Iteration otherIteration) {
        if (otherIteration == this) {
            return true;
        }

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
            && otherIteration.getImagePath().equals(getImagePath())
            && otherIteration.getFeedback().equals(getFeedback());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, imagePath, feedback);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(getDate())
                .append("; Description: ")
                .append(getDescription())
                .append("; ImagePath: ")
                .append(getImagePath())
                .append("; Feedback: ")
                .append(getFeedback());

        return builder.toString();
    }
}
