package seedu.address.model.patient;

import java.util.function.Predicate;


/**
 * Tests that a {@code Patient}'s {@code Remark} matches any of the keywords given.
 */
public class RemarkContainsKeywordsPredicate implements Predicate<Patient> {
    private final String predicateRemark;

    public RemarkContainsKeywordsPredicate(String predicateRemark) {
        this.predicateRemark = predicateRemark;
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getRemark().toString().toLowerCase().contains(predicateRemark.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkContainsKeywordsPredicate // instanceof handles nulls
                && predicateRemark.equals(((RemarkContainsKeywordsPredicate) other).predicateRemark)); // state check
    }

}
