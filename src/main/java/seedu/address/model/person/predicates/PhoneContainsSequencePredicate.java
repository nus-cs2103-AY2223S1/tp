package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Phone} contains the sequence given.
 */
public class PhoneContainsSequencePredicate implements Predicate<Person> {
    private final String sequence;

    public PhoneContainsSequencePredicate(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getPhone().value, sequence);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsSequencePredicate // instanceof handles nulls
                && sequence.equals(((PhoneContainsSequencePredicate) other).sequence)); // state check
    }
}
