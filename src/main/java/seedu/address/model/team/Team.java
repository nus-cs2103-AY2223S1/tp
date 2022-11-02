package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.team.Description.DEFAULT_DESCRIPTION;
import static seedu.address.model.team.Description.NO_DESCRIPTION;
import static seedu.address.model.team.TeamName.DEFAULT_NAME;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a Team in the TruthTable.
 */
public class Team {

    private final TeamName teamName;
    private final Description description;
    private final UniquePersonList teamMembers = new UniquePersonList();

    private final DisplayList<Person> filteredMembers;
    private final TaskList taskList = new TaskList();

    private final DisplayList<Task> filteredTasks;
    private final UniqueLinkList links = new UniqueLinkList();

    /**
     * Constructs a {@code Team}.
     *
     * @param teamName A valid team name.
     */
    public Team(TeamName teamName) {
        requireNonNull(teamName);
        this.teamName = teamName;
        this.description = NO_DESCRIPTION;
        filteredMembers = new DisplayList<>(getTeamMembers());
        filteredTasks = new DisplayList<>(getTaskList());
    }

    /**
     * Constructs a {@code Team}.
     *
     * @param teamName    A valid team name.
     * @param description A valid team description.
     */
    public Team(TeamName teamName, Description description) {
        requireNonNull(teamName);
        this.teamName = teamName;
        this.description = description;
        filteredMembers = new DisplayList<>(getTeamMembers());
        filteredTasks = new DisplayList<>(getTaskList());
    }

    /**
     * Constructs a {@code Team}.
     *
     * @param teamName    A valid team name.
     * @param description A valid team description.
     * @param teamMembers A list of persons to be added as members.
     */
    public Team(TeamName teamName, Description description, List<Person> teamMembers) {
        this(teamName, description);
        this.teamMembers.setPersons(teamMembers);
    }

    /**
     * Constructs a {@code Team}
     *
     * @param teamName    A valid team name
     * @param description A valid team description.
     * @param teamMembers A list of persons to be added as members
     * @param tasks       A list of tasks for the team to do
     */
    public Team(TeamName teamName, Description description, List<Person> teamMembers, List<Task> tasks) {
        this(teamName, description, teamMembers);
        this.taskList.setTasks(tasks);
    }

    /**
     * Constructs a {@code Team}
     *
     * @param teamName    A valid team name
     * @param description A valid team description.
     * @param teamMembers A list of persons to be added as members
     * @param tasks       A list of tasks for the team to do
     * @param links       A list of links that the team should keep track of
     */
    public Team(TeamName teamName, Description description,
                List<Person> teamMembers, List<Task> tasks, List<Link> links) {
        this(teamName, description, teamMembers, tasks);
        this.links.setLinks(links);
    }

    /**
     * This method creates a default team in TruthTable.
     */
    public static Team createDefaultTeam() {
        return new Team(DEFAULT_NAME, DEFAULT_DESCRIPTION,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public TeamName getTeamName() {
        return teamName;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable team set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Person> getTeamMembers() {
        return teamMembers.asUnmodifiableObservableList();
    }

    public FilteredList<Person> getFilteredMemberList() {
        return filteredMembers.getFilteredDisplayList();
    }

    /**
     * Updates the displayed members that matches a certain criteria.
     */
    public void updateFilteredMembersList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredMembers.setPredicate(predicate);
    }

    public void sortMembers(Comparator<Person> comparator) {
        filteredMembers.setComparator(comparator);
    }

    public FilteredList<Task> getFilteredTaskList() {
        return filteredTasks.getFilteredDisplayList();
    }

    public void sortTasks(Comparator<Task> comparator) {
        filteredTasks.setComparator(comparator);
    }

    /**
     * Updates the displayed tasks that matches a certain criteria.
     */
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
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
     * {@code person} must exist in the TruthTable.
     */
    public void removeMember(Person person) {
        teamMembers.remove(person);
        // when a member is removed from a team, all corresponding tasks have to remove member as assignee.
        taskList.removeAssigneeIfExists(person);
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
     * Removes {@code Task} from this {@code Team}.
     * {@code task} must exist in the TruthTable.
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);
        taskList.setTask(target, editedTask);
    }

    /**
     * Sets a specified team member as the assignee for the task.
     *
     * @param task   The specified task to be assigned
     * @param person The specified member that task is assigned to
     */
    public void assignTask(Task task, Person person) {
        task.assignTo(person);
    }

    //// link related operations
    public boolean hasLink(Link link) {
        return links.contains(link);
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public void setLink(Link target, Link editedLink) {
        requireNonNull(editedLink);
        links.setLink(target, editedLink);
    }

    public void deleteLink(Link link) {
        links.remove(link);
    }

    public ObservableList<Link> getLinkList() {
        return links.asUnmodifiableObservableList();
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
        return otherTeam.getTeamName().equals(getTeamName())
                && otherTeam.getDescription().equals(getDescription())
                && otherTeam.getTeamMembers().equals(getTeamMembers())
                && otherTeam.getTaskList().equals(getTaskList())
                && otherTeam.getLinkList().equals(getLinkList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, teamMembers, taskList);
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return string representation of task list.
     */
    public String getTasksAsString() {
        return taskList.toString();
    }

    public String getCompletedTasksAsString() {
        return taskList.getCompletedTasksString();
    }

    public String getIncompleteTasksAsString() {
        return taskList.getIncompleteTasksString();
    }

    /**
     * Returns a map representing the number of tasks assigned to each person.
     *
     * @return Map of person to number of tasks assigned
     */
    public Map<Person, Integer> getNumTasksPerPerson() {
        return taskList.getNumTasksPerPerson();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTeamName());
        builder.append("; Description: ");
        builder.append(getDescription());
        return builder.toString();
    }

}
