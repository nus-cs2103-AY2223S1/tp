package seedu.address.model.patient;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Patient> {

    private final String predicateEmail;

    public EmailContainsKeywordsPredicate(String predicateEmail) {
        this.predicateEmail = predicateEmail;
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getEmail().toString().toLowerCase().contains(predicateEmail.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && predicateEmail.equals(((EmailContainsKeywordsPredicate) other).predicateEmail)); // state check
    }

}
