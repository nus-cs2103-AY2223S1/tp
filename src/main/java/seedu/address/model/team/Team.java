package seedu.address.model.team;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Team in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Team {

    // Identity fields
    private final Name name;



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



    /**
     * Returns true if both team have the same name.
     * This defines a weaker notion of equality between two team.
     */
    public boolean isSamePerson(seedu.address.model.team.Team otherTeam) {
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



