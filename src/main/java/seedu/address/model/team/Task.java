package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Tasks are used to track the progress of a team.
 */
public class Task {

    public static final String MESSAGE_CONSTRAINTS =
        "Task names should not be blank and cannot begin with a whitespace";

    /*
     * The first character of the task name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * Name of the task.
     */
    private String name;

    /**
     * Team member(s) assigned to be in charge of this task.
     */
    private List<Person> assignees;

    /**
     * Deadline of the task.
     */
    private LocalDateTime deadline;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid task name.
     */
    public Task(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        assignees = new ArrayList<>();
        deadline = null;
    }

    /**
     * Returns true if a given string is a valid name for a task.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isValidIndex(int test) {
        return test < assignees.size();
    }

    @Override
    public String toString() {
        return name + getAssigneesAsString() + getDeadlineAsString();
    }

    public String getName() {
        return name;
    }

    public List<Person> getAssignees() {
        return assignees;
    }

    public String getAssigneesAsString() {
        if (assignees.isEmpty()) {
            return " (Not assigned to any member yet)";
        } else {
            StringBuilder assigneeNames = new StringBuilder(" (Assigned to: ");
            assigneeNames.append(assignees.get(0).getName());
            for (int i = 1; i < assignees.size(); i++) {
                assigneeNames.append(", ").append(assignees.get(i).getName());
            }
            assigneeNames.append(")");
            return assigneeNames.toString();
        }
    }

    public String getDeadlineAsString() {
        if (deadline == null) {
            return "";
        } else {
            return String.format("(By %s)", deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
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

    public void assignTo(Person assignee) {
        assignees.add(assignee);
    }

    /**
     * Checks if task has already been assigned to the specified assignee.
     * @param assignee
     * @return true if the task has been assigned to the assignee before, false otherwise.
     */
    public boolean checkAssignee(Person assignee) {
        return this.assignees.contains(assignee);
    }

    public void setDeadline(LocalDateTime date) {
        this.deadline = date;
    }
}
