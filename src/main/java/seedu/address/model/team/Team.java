package seedu.address.model.team;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


/**
 * Represents a Team in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Team {

    // Identity fields
    private final Name name;
    private ArrayList<Person> members;
    private ArrayList<Task> tasks;



    /**
     * Every field must be present and not null.
     */
    public Team(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
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

    public void addTasK(Task t) {
        tasks.add(t);
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



