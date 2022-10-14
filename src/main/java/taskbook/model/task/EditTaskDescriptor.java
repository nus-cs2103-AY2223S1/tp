package taskbook.model.task;

import static taskbook.commons.util.CollectionUtil.isAnyNonNull;

import java.time.LocalDate;
import java.util.Optional;

import taskbook.model.person.Name;
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

    public EditTaskDescriptor() {}

    /**
     * Copy constructor.
     */
    public EditTaskDescriptor(EditTaskDescriptor toCopy) {
        setName(toCopy.name);
        setAssignment(toCopy.assignment);
        setDescription(toCopy.description);
        setIsDone(toCopy.isDone);
        setDate(toCopy.date);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return isAnyNonNull(name, assignment, description, isDone, date);
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
            && getDate().equals(e.getDate());
    }
}
