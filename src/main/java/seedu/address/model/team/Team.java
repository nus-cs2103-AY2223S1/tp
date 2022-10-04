package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;
import java.util.Objects;

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

    public void addMember(Person person) {
        teamMembers.add(person);
    }

    public boolean hasMember(Person person) {
        return teamMembers.contains(person);
    }

    public void setMember(Person target, Person editedPerson) {
        teamMembers.setPerson(target, editedPerson);
    }

    public void removeMember(Person person) {
        teamMembers.remove(person);
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

        List<Person> members = getTeamMembers();
        if (!members.isEmpty()) {
            builder.append("; Members: ");
            members.forEach(builder::append);
        }
        return builder.toString();
    }

}
