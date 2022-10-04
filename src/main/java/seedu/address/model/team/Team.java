package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Team in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTeamName(String)}
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS = "Team names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String teamName;
    public final Set<Person> teamMembers = new HashSet<>();

    /**
     * Constructs a {@code Team}.
     *
     * @param teamName A valid team name.
     * @param teamMembers A set of valid persons to be added as members.
     */
    public Team(String teamName, Set<Person> teamMembers) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
        this.teamMembers.addAll(teamMembers);
    }

    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns an immutable team set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Person> getTeamMembers() {
        return Collections.unmodifiableSet(teamMembers);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTeamName(String test) {
        return test.matches(VALIDATION_REGEX);
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
                && otherTeam.getTeamMembers().equals(getTeamMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, teamMembers);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTeamName());

        Set<Person> members = getTeamMembers();
        if (!members.isEmpty()) {
            builder.append("; Members: ");
            members.forEach(builder::append);
        }
        return builder.toString();
    }

}
