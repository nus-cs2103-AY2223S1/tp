package seedu.address.logic.commands;

import static java.util.Objects.hash;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;

/**
 * Stores the details to edit the task with. Each non-empty field value will replace the
 * corresponding field value of the task.
 */
public class EditTaskDescriptor {
    private Description description;
    private Deadline deadline;
    private Boolean isDone;
    private Boolean isArchived;
    private Set<Tag> tags;
    private Id id;

    public EditTaskDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditTaskDescriptor(EditTaskDescriptor toCopy) {
        setDescription(toCopy.description);
        setDeadline(toCopy.deadline);
        setCompletionStatus(toCopy.isDone);
        setArchivalStatus(toCopy.isArchived);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(description, deadline, isDone, isArchived, tags);
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Optional<Description> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public Optional<Deadline> getDeadline() {
        return Optional.ofNullable(deadline);
    }

    public void setCompletionStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    public Optional<Boolean> getCompletionStatus() {
        return Optional.ofNullable(isDone);
    }

    public void setArchivalStatus(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    public Optional<Boolean> getArchivalStatus() {
        return Optional.ofNullable(isArchived);
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Optional<Id> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public int hashCode() {
        return hash(description, deadline, isDone, isArchived, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskDescriptor)) {
            return false;
        }

        // state check
        EditTaskDescriptor e = (EditTaskDescriptor) other;

        return getDescription().equals(e.getDescription())
                && getDeadline().equals(e.getDeadline())
                && getCompletionStatus().equals(e.getCompletionStatus())
                && getArchivalStatus().equals(e.getArchivalStatus())
                && getTags().equals(e.getTags());
    }
}
