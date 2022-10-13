package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} contains the sequence given; case-insensitive.
 */
public class NameContainsSequencePredicate implements Predicate<Person> {
    private final String sequence;

    public NameContainsSequencePredicate(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getName().fullName, sequence);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsSequencePredicate // instanceof handles nulls
                && sequence.equals(((NameContainsSequencePredicate) other).sequence)); // state check
    }
}
