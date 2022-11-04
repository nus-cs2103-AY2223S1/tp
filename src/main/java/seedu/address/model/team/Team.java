package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
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
    private Name name;
    private UniqueTaskList tasks = new UniqueTaskList();
    private UniquePersonList members = new UniquePersonList();


    /**
     * Every field must be present and not null.
     */
    public Team(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    /**
     * Creates a team with certain preset {@code task} and {@code member}.
     *
     * @param name A valid team name.
     * @param tasks A list with tasks.
     * @param members A list of team members.
     */
    public Team(Name name, List<Task> tasks, List<Person> members) {
        this.name = name;
        this.tasks.addAll(tasks);
        this.members.addAll(members);
    }

    /**
     * Creates a team with certain preset {@code task} and {@code member}.
     *
     * @param name A valid team name.
     * @param tasks A UniqueTaskList.
     * @param members A UniquePersonList.
     */
    public Team(Name name, UniqueTaskList tasks, UniquePersonList members) {
        this.name = name;
        this.tasks = tasks;
        this.members = members;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public UniqueTaskList getTasks() {
        return tasks;
    }

    public ObservableList<Task> getTasksList() {
        return tasks.asUnmodifiableObservableList();
    }

    public Task getTask(int n) {
        return tasks.get(n);
    }

    /**
     * Add a person into the team.
     *
     * @param p person to add.
     */
    public void addMember(Person p) {
        requireNonNull(p);
        members.add(p);
    }

    /**
     * Remove the person from the team if said person is in the team.
     *
     * @param p person to be removed.
     */
    public void removeMember(Person p) {
        if (members.contains(p)) {
            members.remove(p);
        }
    }

    /**
     * Edit the information of a member.
     *
     * @param target person to be edited.
     * @param editedPerson person after edit.
     */
    public void setMember(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        members.setPerson(target, editedPerson);
    }

    /**
     * Check if a person is a member of this team.
     *
     * @param p person to check.
     * @return true if person is a member, else false.
     */
    public boolean containMember(Person p) {
        return members.contains(p);
    }

    public UniquePersonList getMembers() {
        return members;
    }

    public ObservableList<Person> getMemberList() {
        return members.asUnmodifiableObservableList();
    }

    public boolean containTask(Task t) {
        return tasks.contains(t);
    }

    /**
     * Adds a task to this team.
     * @param t task to add
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Marks a task of the team as done.
     * @param index The index of the {@code task} to be marked as done.
     */
    public void markTask(int index) {
        tasks.mark(index);
    }

    /**
     * Marks a task of the team as not done.
     * @param index The index of the {@code task} to be marked as not done.
     */
    public void unmarkTask(int index) {
        tasks.unmark(index);
    }

    /**
     * Deletes a task to this team.
     * @param index The index of the {@code task} to be deleted.
     */
    public void deleteTask(int index) {
        tasks.delete(index);
    }

    /**
     * Edits a task of the team.
     * @param index The index of the {@code task} to be deleted.
     * @param newName The new name of the task.
     */
    public void editTask(int index, seedu.address.model.task.Name newName, LocalDate newDeadline) {
        tasks.edit(index, newName, newDeadline);
    }

    /**
     * get number of tasks that is done.
     *
     * @return number of tasks that is done.
     */
    public int getNoOfCompletedTasK() {
        return tasks.getNoOfCompletedTasks();
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
        return otherTeam.getName().equals(getName()) && otherTeam.getTasks().equals(getTasks())
                && otherTeam.getMembers().equals(getMembers());
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



