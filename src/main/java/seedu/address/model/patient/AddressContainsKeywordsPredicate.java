package seedu.address.model.patient;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Patient> {
    private final String predicateAddress;

    public AddressContainsKeywordsPredicate(String predicateAddress) {
        this.predicateAddress = predicateAddress;
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getAddress().toString().toLowerCase().contains(predicateAddress.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && predicateAddress.equals(((AddressContainsKeywordsPredicate) other).predicateAddress)); // state check
    }

}
