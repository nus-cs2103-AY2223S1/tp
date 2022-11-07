package seedu.address.model.person;

import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Tests whether a {@code Person}'s is included in Set of {@code Person}.
 */
public class SamePersonPredicate implements Predicate<Person> {
    private HashSet<Person> personSet = new HashSet<>();

    public SamePersonPredicate(HashSet<Person> personSet) {
        this.personSet = personSet;
    }
    @Override
    public boolean test(Person person) {
        return personSet.contains(person);
    }
}
