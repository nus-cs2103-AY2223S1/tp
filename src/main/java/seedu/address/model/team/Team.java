package seedu.address.model.team;

import seedu.address.model.person.Name;

/**
 * Represents a Team in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Team {

    // Identity fields
    private final Name name;

    public Team(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns true if both teams have the same name.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Team)) {
            return false;
        }

        //TODO check identity and data fields?
        Team otherTeam = (Team) other;
        return otherTeam.getName().equals(getName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }
}
