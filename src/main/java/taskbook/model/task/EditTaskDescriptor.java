package taskbook.model.task;

import static taskbook.commons.util.CollectionUtil.isAnyNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import taskbook.model.person.Name;
import taskbook.model.tag.Tag;
import taskbook.model.task.enums.Assignment;

/**
 * Stores the details to edit the task with.
 * Each non-empty field value will replace the corresponding field value of the task.
 */
public class EditTaskDescriptor {

    private Name name;
    private Assignment assignment;
    private Description description;
    private Boolean isDone;
    private LocalDate date;
    private Set<Tag> tags;

    public EditTaskDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditTaskDescriptor(EditTaskDescriptor toCopy) {
        setName(toCopy.name);
        setAssignment(toCopy.assignment);
        setDescription(toCopy.description);
        setIsDone(toCopy.isDone);
        setDate(toCopy.date);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return isAnyNonNull(name, assignment, description, isDone, date, tags);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Optional<Assignment> getAssignment() {
        return Optional.ofNullable(assignment);
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Optional<Description> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Optional<Boolean> getIsDone() {
        return Optional.ofNullable(isDone);
    }

    public Optional<LocalDate> getDate() {
        return Optional.ofNullable(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

        return getName().equals(e.getName())
            && getAssignment().equals(e.getAssignment())
            && getDescription().equals(e.getDescription())
            && getIsDone().equals(e.getIsDone())
            && getDate().equals(e.getDate())
            && getTags().equals(e.getTags());
    }
}
