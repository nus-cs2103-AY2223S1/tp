package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.team.Team;

/**
 * Tests that a {@code Person} belongs to any of the {@code Team} specified.
 */
public class TeamPredicate implements Predicate<Person> {
    private final List<Team> teams;

    public TeamPredicate(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean test(Person person) {
        return teams.stream()
                .anyMatch(team -> team.hasMember(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamPredicate // instanceof handles nulls
                && teams.equals(((TeamPredicate) other).teams)); // state check
    }

}
