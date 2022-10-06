package seedu.address.model.tutorial;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Tutorial in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tutorial {

    private final Group group;
    private final Content content;
    private final Time time;

    public Tutorial(Group group, Content content, Time time) {
        requireAllNonNull(group, content, time);
        this.group = group;
        this.content = content;
        this.time = time;
    }

    public Group getGroup() {
        return group;
    }

    public Content getContent() {
        return content;
    }

    public Time getTime() {
        return time;
    }

    /**
     * Returns true if both tutorials have the same fields.
     * This defines a stronger notion of equality between two persons.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutorial)) {
            return false;
        }

        Tutorial otherTutorial = (Tutorial) other;

        return other != null
                && otherTutorial.getGroup().equals(getGroup())
                && otherTutorial.getContent().equals(getContent())
                && otherTutorial.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(group, content, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Tutorial")
                .append("; Group: ")
                .append(getGroup())
                .append("; Content: ")
                .append(getContent())
                .append("; Time: ")
                .append(getTime());

        return builder.toString();
    }
}
