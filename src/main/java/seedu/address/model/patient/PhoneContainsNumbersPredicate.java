package seedu.address.model.patient;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient}'s {@code Phone} matches any of the numbers given.
 */
public class PhoneContainsNumbersPredicate implements Predicate<Patient> {
    private final String predicateNumber;

    public PhoneContainsNumbersPredicate(String predicateNumber) {
        this.predicateNumber = predicateNumber;
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getPhone().value.contains(predicateNumber);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsNumbersPredicate // instanceof handles nulls
                && predicateNumber.equals(((PhoneContainsNumbersPredicate) other).predicateNumber)); // state check
    }

}
