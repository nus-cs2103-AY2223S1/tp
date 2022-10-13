package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Verifies that {@code Person} is the same Person as the Person to check.
 */
public class SamePersonPredicate implements Predicate<Person> {
    private final Person personToCheck;

    public SamePersonPredicate(Person personToCheck) {
        this.personToCheck = personToCheck;
    }

    @Override
    public boolean test(Person person) {
        return personToCheck.isSamePerson(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Person // instanceof handles nulls
                && personToCheck.isSamePerson(((SamePersonPredicate) other).personToCheck)); // state check
    }
}
