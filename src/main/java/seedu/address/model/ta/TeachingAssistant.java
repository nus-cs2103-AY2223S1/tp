package seedu.address.model.ta;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class TeachingAssistant {

    // Identity fields
    private final TeachingAssistantName name;
    private final TeachingAssistantId id;

    /**
     * Every field must be present and not null.
     */
    public TeachingAssistant(TeachingAssistantName name, TeachingAssistantId id) {
        requireAllNonNull(name, id);
        this.name = name;
        this.id = id;
    }

    public TeachingAssistantName getName() {
        return name;
    }

    public TeachingAssistantId getId() {
        return id;
    }

    /**
     * Returns true if both teaching assistants have the same id.
     * This defines a weaker notion of equality between two teaching assistants.
     */
    public boolean isSameTeachingAssistant(TeachingAssistant otherTeachingAssistant) {
        if (otherTeachingAssistant == this) {
            return true;
        }

        return otherTeachingAssistant != null
                && otherTeachingAssistant.getId().equals(getId());
    }

    /**
     * Returns true if both teaching assistants have the same identity and data fields.
     * This defines a stronger notion of equality between two teaching assistants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TeachingAssistant)) {
            return false;
        }

        TeachingAssistant otherTeachingAssistant = (TeachingAssistant) other;
        return otherTeachingAssistant.getName().equals(getName())
                && otherTeachingAssistant.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Student ID: ")
                .append(getId());

        return builder.toString();
    }
}
