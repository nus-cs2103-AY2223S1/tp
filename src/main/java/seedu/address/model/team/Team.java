package seedu.address.model.team;

import seedu.address.model.person.Name;

import java.util.regex.Pattern;

/**
 * Represents a Team in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS = "A team name should only consist "
            + "of alphanumeric characters and should not include whitespaces or "
            + "any backslashes.\n";

    // Identity fields
    private final Name name;

    public Team(Name name) {
        this.name = name;
    }

    /**
     * Checks if the team name is valid.
     *
     * @param teamName for a specific team.
     * @return true if the team name is valid, false otherwise.
     */
    public static boolean isValidTeamName(String teamName) {
        return !containsBackslash(teamName);
    }

    /**
     * Checks if a team name contains backslash.
     *
     * @param teamName that is given to a specific team.
     * @return true if the team name contains a backslash, false otherwise.
     */
    private static boolean containsBackslash(String teamName) {
        return Pattern.matches("\\\\", teamName);
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
