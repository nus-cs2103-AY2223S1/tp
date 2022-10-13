package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Email} contains the sequence given; case-insensitive.
 */
public class EmailContainsSequencePredicate implements Predicate<Person> {
    private final String sequence;

    public EmailContainsSequencePredicate(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getEmail().value, sequence);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsSequencePredicate // instanceof handles nulls
                && sequence.equals(((EmailContainsSequencePredicate) other).sequence)); // state check
    }
}
