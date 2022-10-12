package seedu.address.model.team;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;


/**
 * Represents a Team in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Team {

    // Identity fields
    private final Name name;
    private UniquePersonList members = new UniquePersonList();
    private UniqueTaskList tasks = new UniqueTaskList();

    /**
     * Every field must be present and not null.
     */
    public Team(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.tasks = new UniqueTaskList();
        this.members = new UniquePersonList();
    }

    /**
     * Create a team with certain preset {@code task}.
     * @param name A valid team name.
     * @param tasks A list with tasks.
     * @param members A list of team members.
     */
    public Team(Name name, List<Task> tasks, List<Person> members) {
        this.name = name;
        this.tasks.addAll(tasks);
        this.members.addAll(members);
    }

    public Name getName() {
        return name;
    }

    public UniqueTaskList getTasks() {
        return tasks;
    }

    public void addMember(Person p) {
        members.add(p);
    }

    /**
     * Remove the person from the team if said person is in the team
     * @param p person to be removed
     */
    public void removeMember(Person p) {
        if (members.contains(p)) {
            members.remove(p);
        }
    }

    /**
     * Returns list of members in the team
     * @return members
     */

    public UniquePersonList getMembers() {
        return members;
    }

    public ObservableList<Person> getMemberList() {
        return members.asUnmodifiableObservableList();
    }

    /**
     * Adds a task to this team.
     * @param t
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Marks a task of the team as done.
     * @param t
     */
    public void markTask(Task t) {
        tasks.mark(t);
    }

    /**
     * Marks a task of the team as not done.
     * @param t
     */
    public void unmarkTask(Task t) {
        tasks.unmark(t);
    }

    /**
     * Deletes a task to this team.
     * @param index The index of the {@code task} to be deleted.
     */
    public void deleteTask(int index) {
        tasks.delete(index);
    }

    /**
     * Returns true if both team have the same name.
     * This defines a weaker notion of equality between two team.
     */
    public boolean isSameTeam(seedu.address.model.team.Team otherTeam) {
        if (otherTeam == this) {
            return true;
        }

        return otherTeam != null
                && otherTeam.getName().equals(getName());
    }

    /**
     * Returns true if both team have the same identity and data fields.
     * This defines a stronger notion of equality between two team.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.address.model.team.Team)) {
            return false;
        }

        seedu.address.model.team.Team otherTeam = (seedu.address.model.team.Team) other;
        return otherTeam.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        return builder.toString();
    }

}



