package seedu.address.model.issue;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Issue.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Issue {

    // Components of an issue
    private Id id;
    private Description description;
    private Deadline deadline;
    private Priority priority;
    private Status status;

    /**
     * Description field must be present and not null, but all other fields are optional.
     */
    public Issue(Description description, Deadline deadline, Priority priority, Status status) {
        requireAllNonNull(description, deadline, priority, status);
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
    }

    public Id getId() {
        return this.id;
    }

    public Description getDescription() {
        return this.description;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Status getStatus() {
        return this.status;
    }
}
