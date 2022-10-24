package seedu.uninurse.model.medication;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.commons.util.StringUtil;
import seedu.uninurse.model.person.Person;

/**
 * Tests that a {@code Patient}'s {@code Medication} matches any of the keywords given.
 */
public class MedicationContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MedicationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream().anyMatch(keyword ->
                person.getTags().stream().anyMatch(
                    medication -> StringUtil.containsIgnoreCase(medication.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MedicationContainsKeywordsPredicate) other).keywords)); // state check
    }
}
