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
    private boolean status;

    /**
     * Constructs a {@code Tutorial}.
     * Every field must be present and not null.
     */
    public Tutorial(Group group, Content content, Time time, boolean status) {
        requireAllNonNull(group, content, time, status);
        this.group = group;
        this.content = content;
        this.time = time;
        this.status = status;
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

    public String getTimeOrDateStr(String s) {

        return s.equalsIgnoreCase("time") ? time.getUiStringTime() : time.getUiStringDate();
    }

    public boolean getStatus() {
        return status;
    }

    /**
     * Returns true if both tutorials have the same time.
     * This defines a weaker notion of equality between two tutorials.
     */
    public boolean isSameTutorial(Tutorial otherTutorial) {
        if (otherTutorial == this) {
            return true;
        }

        return otherTutorial != null
                && otherTutorial.getTime().equals(getTime());
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
                && otherTutorial.getTime().equals(getTime())
                && (otherTutorial.getStatus() == this.getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(group, content, time, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Tutorial Information")
                .append(": Group: ")
                .append(getGroup())
                .append("; Content: ")
                .append(getContent())
                .append("; Time: ")
                .append(getTimeOrDateStr("time"))
                .append("; Date: ")
                .append(getTimeOrDateStr("date"))
                .append(getStatus() ? "\n[X]" : "\n[ ]");

        return builder.toString();
    }
}
