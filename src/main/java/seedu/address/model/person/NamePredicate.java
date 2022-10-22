package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} has the exact {@code Name} specified.
 */
public class NamePredicate implements Predicate<Person> {
    private final Name name;

    public NamePredicate(Name name) {
        this.name = name;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().equals(name);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NamePredicate // instanceof handles nulls
                && name.equals(((NamePredicate) other).name)); // state check
    }

}
