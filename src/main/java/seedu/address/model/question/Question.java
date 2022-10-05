package seedu.address.model.question;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Question in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Question {

    // Identity fields
    private final Description description;


    /**
     * Every field must be present and not null.
     */
    public Question(Description description) {
        requireAllNonNull(description);
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }



    /**
     * Returns true if both Questions have the same description.
     * This defines a weaker notion of equality between two Questions.
     */
    public boolean isSameQuestion(Question otherQuestion) {
        if (otherQuestion == this) {
            return true;
        }

        return otherQuestion != null
                && otherQuestion.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Question)) {
            return false;
        }

        Question otherQuestion = (Question) other;
        return otherQuestion.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription());
        return builder.toString();
    }

}
