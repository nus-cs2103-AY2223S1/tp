package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicatePatient implements Predicate<Patient> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicatePatient(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicatePatient // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicatePatient) other).keywords)); // state check
    }

}
