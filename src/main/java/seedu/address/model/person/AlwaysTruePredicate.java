package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Returns true for any {@code Person} and keyword given.
 */
public class AlwaysTruePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return true;
    }

}

