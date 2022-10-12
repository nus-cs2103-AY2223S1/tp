package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Address} contains the sequence given; case-insensitive.
 */
public class AddressContainsSequencePredicate implements Predicate<Person> {
    private final String sequence;

    public AddressContainsSequencePredicate(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsIgnoreCase(person.getAddress().value, sequence);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsSequencePredicate // instanceof handles nulls
                && sequence.equals(((AddressContainsSequencePredicate) other).sequence)); // state check
    }
}
