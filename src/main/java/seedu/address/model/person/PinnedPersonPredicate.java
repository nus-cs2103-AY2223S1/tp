package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code pinned} is true or false to see if a contact is pinned.
 */
public class PinnedPersonPredicate implements Predicate<Person> {

    public PinnedPersonPredicate() {

    }

    @Override
    public boolean test(Person person) {
        boolean pinned = true;
        return person.getPin() == pinned;
    }
}
