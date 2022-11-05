package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team's name in the addressbook
 * Guarantees: immutable; is valid as declared in {@link #isValidTeamName(String)}
 */
public class TeamName {

    public static final String MESSAGE_CONSTRAINTS =
            "Team description should only contain alphanumeric characters, cannot contain spaces "
                    + "and it should not be blank";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public static final TeamName DEFAULT_NAME = new TeamName("default");

    public final String teamName;

    /**
     * Constructs a {@code TeamName}
     *
     * @param teamName A valid team name.
     */
    public TeamName(String teamName) {
        requireNonNull(teamName);
        checkArgument(isValidTeamName(teamName), MESSAGE_CONSTRAINTS);
        this.teamName = teamName;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidTeamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return teamName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamName // instanceof handles nulls
                && teamName.equals(((TeamName) other).teamName)); // state check
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

}
