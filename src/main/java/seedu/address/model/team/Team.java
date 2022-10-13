package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a Team in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTeamName(String)}
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS = "Team names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String teamName;
    private final UniquePersonList teamMembers = new UniquePersonList();
    private final TaskList taskList = new TaskList();

    /**
     * Constructs a {@code Team}.
     *
     * @param teamName A valid team name.
     * @param teamMembers A list of persons to be added as members.
     */
    public Team(String teamName, List<Person> teamMembers) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
        this.teamMembers.setPersons(teamMembers);
    }

    /**
     * Constructs a {@code Team}
     * @param teamName A valid team name
     * @param teamMembers A list of persons to be added as members
     * @param tasks A list of tasks for the team to do
     */
    public Team(String teamName, List<Person> teamMembers, List<Task> tasks) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
        this.teamMembers.setPersons(teamMembers);
        this.taskList.setTasks(tasks);
    }
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns an immutable team set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Person> getTeamMembers() {
        return teamMembers.asUnmodifiableObservableList();
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the team.
     */
    public boolean hasMember(Person person) {
        return teamMembers.contains(person);
    }

    /**
     * Adds a person to the team.
     * The person must not already exist in the team.
     */
    public void addMember(Person person) {
        teamMembers.add(person);
    }

    /**
     * Removes {@code Person} from this {@code Team}.
     * {@code person} must exist in the address book.
     */
    public void removeMember(Person person) {
        teamMembers.remove(person);
    }

    public ObservableList<Task> getTaskList() {
        return taskList.asUnmodifiableObservableList();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean hasTask(Task task) {
        return taskList.contains(task);
    }

    /**
     * Sets a specified team member as the assignee for the task.
     * @param task The specified task to be assigned
     * @param person The specified member that task is assigned to
     */
    public void assignTask(Task task, Person person) {
        task.assignTo(person);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTeamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both teams have the same name.
     */
    public boolean isSameTeam(Team otherTeam) {
        if (otherTeam == this) {
            return true;
        } else {
            return otherTeam != null
                    && otherTeam.getTeamName().equals(this.getTeamName());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Team)) {
            return false;
        }

        Team otherTeam = (Team) other;
        return otherTeam.getTeamName().equals(getTeamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, teamMembers, taskList);
    }

    /**
     * Returns a string representation of the task list.
     * @return string representation of task list.
     */
    public String getTasksAsString() {
        return taskList.toString();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTeamName());

        List<Person> members = getTeamMembers();
        if (!members.isEmpty()) {
            builder.append("; Members: ");
            members.forEach(builder::append);
        }
        builder.append(getTasksAsString());
        return builder.toString();
    }

}
