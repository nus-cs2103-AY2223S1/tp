package longtimenosee.model.person.predicate;

import java.util.function.Predicate;

import longtimenosee.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Email} matches the input given.
 */
public class EmailMatchesInputPredicate implements Predicate<Person> {
    private final String input;

    /**
     * Constructs an EmailMatchesInputPredicate object, which consists of an input.
     *
     * @param input is the input by the user to be compared.
     */
    public EmailMatchesInputPredicate(String input) {
        assert input.length() != 0;
        assert input.contains("@");
        this.input = input;
    }

    @Override
    public boolean test(Person person) {
        return person.getEmail().value.equalsIgnoreCase(input);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof EmailMatchesInputPredicate) {
                return input.equals(((EmailMatchesInputPredicate) other).input);
            } else {
                return false;
            }
        }
    }
}
