package seedu.address.model.patient;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicatePatient implements Predicate<Patient> {
    private final String predicateName;

    public NameContainsKeywordsPredicatePatient(String predicateName) {
        this.predicateName = predicateName;
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getName().fullName.toLowerCase().contains(predicateName.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicatePatient // instanceof handles nulls
                && predicateName.equals(((NameContainsKeywordsPredicatePatient) other).predicateName)); // state check
    }

}
