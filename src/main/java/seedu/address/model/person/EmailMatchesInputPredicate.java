package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Email} matches the input given.
 */
public class EmailMatchesInputPredicate implements Predicate<Person> {
    private final String input;

    public EmailMatchesInputPredicate(String input) {
        this.input = input;
    }

    @Override
    public boolean test(Person person) {
        return person.getEmail().value.equals(input);
    }
}
