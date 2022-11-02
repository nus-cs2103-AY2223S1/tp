package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Tasks are used to track the progress of a team.
 */
public class Task {

    /**
     * Name of the task.
     */
    private final TaskName name;

    /**
     * Team member(s) assigned to be in charge of this task.
     */
    private final UniquePersonList assignees = new UniquePersonList();

    /**
     * Deadline of the task.
     */
    private final LocalDateTime deadline;

    /**
     * Completion status of the task.
     */
    private final boolean completionStatus;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid task name.
     */
    public Task(TaskName name, List<Person> assignees, boolean completionStatus, LocalDateTime deadline) {
        requireNonNull(name);
        this.name = name;
        this.assignees.setPersons(assignees);
        this.completionStatus = completionStatus;
        this.deadline = deadline;
    }

    public boolean isValidIndex(int test) {
        return test < getAssigneesList().size();
    }

    @Override
    public String toString() {
        return getCompletionStatus() + name + " " + getAssigneesAsString() + " " + getDeadlineAsString();
    }

    public TaskName getName() {
        return name;
    }

    public String getAssigneesAsString() {
        if (getAssigneesList().isEmpty()) {
            return "(Not assigned to any member yet)";
        } else {
            StringBuilder assigneeNames = new StringBuilder("(Assigned to: ");
            assigneeNames.append(getAssigneesList().get(0).getName());
            for (int i = 1; i < getAssigneesList().size(); i++) {
                assigneeNames.append(", ").append(getAssigneesList().get(i).getName());
            }
            assigneeNames.append(")");
            return assigneeNames.toString();
        }
    }

    public List<Person> getAssigneesList() {
        return this.assignees.asUnmodifiableObservableList();
    }

    public String getDeadlineAsString() {
        if (deadline == null) {
            return "";
        } else {
            return String.format("(By %s)", deadline.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a")));
        }
    }

    public Optional<LocalDateTime> getDeadline() {
        if (deadline != null) {
            return Optional.of(deadline);
        } else {
            return Optional.empty();
        }
    }

    public String getDeadlineStorage() {
        if (deadline == null) {
            return "";
        } else {
            return deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    /**
     * Returns true if two tasks have the same name, members that task is assigned to and deadline.
     *
     * @param other the other task to be compared with.
     * @return true if the tasks are considered equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && name.equals(((Task) other).name))
                && assignees.equals(((Task) other).assignees)
                && this.getDeadlineAsString().equals(((Task) other).getDeadlineAsString()); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Assigns a Task to a person and returns a new Task
     */
    public Task assignTo(Person assignee) {
        TaskName name = getName();
        assignees.add(assignee);
        List<Person> newAssignees = getAssigneesList();
        boolean completionStatus = isComplete();
        LocalDateTime date = this.deadline;
        return new Task(name, newAssignees, completionStatus, date);
    }

    /**
     * Checks if task has already been assigned to the specified assignee.
     *
     * @param assignee The specified assignee.
     * @return true if the task has been assigned to the assignee before, false otherwise.
     */
    public boolean checkAssignee(Person assignee) {
        return this.assignees.contains(assignee);
    }

    /**
     * Set a new deadline and returns a new Task with that deadline
     */
    public Task setDeadline(LocalDateTime date) {
        TaskName name = getName();
        List<Person> assignees = getAssigneesList();
        boolean completionStatus = isComplete();
        LocalDateTime newDate = date;
        return new Task(name, assignees, completionStatus, newDate);
    }

    /**
     * Marks a Task and returns a new Task
     */
    public Task mark(boolean completionStatus) {
        TaskName name = getName();
        List<Person> assignees = getAssigneesList();
        boolean newCompletionStatus = completionStatus;
        LocalDateTime date = this.deadline;
        return new Task(name, assignees, newCompletionStatus, date);
    }

    public boolean isComplete() {
        return completionStatus;
    }

    public String getCompletionStatus() {
        if (this.isComplete()) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    /**
     * Removes the person from the assignee list, if exists.
     *
     * @param person assignee to be removed.
     */
    public void removeAssigneeIfExists(Person person) {
        if (this.assignees.contains(person)) {
            this.assignees.remove(person);
        }
    }
}
