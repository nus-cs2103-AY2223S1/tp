package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.ModelManager;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class HiddenPersonPredicate implements Predicate<Person> {

    private static List<Person> hiddenPersons;

    public HiddenPersonPredicate(List<Person> hiddenPersons) {
        this.hiddenPersons = hiddenPersons;
    }

    @Override
    public boolean test(Person person) {
        if (hiddenPersons.contains(person)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HiddenPersonPredicate // instanceof handles nulls
                && hiddenPersons.equals(((HiddenPersonPredicate) other).hiddenPersons)); // state check
    }

}
