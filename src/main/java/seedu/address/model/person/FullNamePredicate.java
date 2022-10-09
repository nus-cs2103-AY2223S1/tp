package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FullNamePredicate implements Predicate<Person> {
    private final String fullName;

    public FullNamePredicate(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().toString().equals(fullName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FullNamePredicate // instanceof handles nulls
                && fullName.equals(((FullNamePredicate) other).fullName)); // state check
    }

}
