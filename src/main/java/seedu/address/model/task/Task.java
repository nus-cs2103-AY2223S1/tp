package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Task in the address book.
 */
public class Task {

    private final Name name;
    private boolean isDone;

    /**
     * Constructs an unfinished {@code Task}.
     *
     * @param name A valid name.
     */
    public Task(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.isDone = false;
    }

    public Name getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns true if both task have the same description and both are done or both are not done.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && name.equals(((Task) other).getName())); // state check
    }

    @Override
    public String toString() {
        return this.name.toString();
    }
}
