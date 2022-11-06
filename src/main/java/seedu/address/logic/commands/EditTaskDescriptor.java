package seedu.address.logic.commands;

import static java.util.Objects.hash;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.ArchivalStatus;
import seedu.address.model.task.CompletionStatus;
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
    private CompletionStatus completionStatus;
    private ArchivalStatus archivalStatus;
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
        setCompletionStatus(toCopy.completionStatus);
        setArchivalStatus(toCopy.archivalStatus);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(description, deadline, completionStatus, archivalStatus, tags);
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

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public Optional<CompletionStatus> getCompletionStatus() {
        return Optional.ofNullable(completionStatus);
    }

    public void setArchivalStatus(ArchivalStatus archivalStatus) {
        this.archivalStatus = archivalStatus;
    }

    public Optional<ArchivalStatus> getArchivalStatus() {
        return Optional.ofNullable(archivalStatus);
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
        return hash(description, deadline, completionStatus, archivalStatus, tags);
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
