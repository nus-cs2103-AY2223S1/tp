package seedu.uninurse.model.medication;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.commons.util.StringUtil;
import seedu.uninurse.model.person.Patient;

/**
 * Tests that at least one of the Patient's Medication matches any of the keywords given.
 */
public class MedicationContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public MedicationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient person) {
        return keywords.stream().anyMatch(keyword ->
                person.getMedications().getInternalList().stream().anyMatch(
                    medication -> StringUtil.containsIgnoreCase(medication.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MedicationContainsKeywordsPredicate) other).keywords)); // state check
    }
}
