package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.team.Team;

/**
 * Tests that a {@code Person} belongs to the {@code Team} specified.
 */
public class TeamPredicate implements Predicate<Person> {
    private final Team team;

    public TeamPredicate(Team team) {
        this.team = team;
    }

    @Override
    public boolean test(Person person) {
        return team.hasMember(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TeamPredicate // instanceof handles nulls
            && team.equals(((TeamPredicate) other).team)); // state check
    }

}
