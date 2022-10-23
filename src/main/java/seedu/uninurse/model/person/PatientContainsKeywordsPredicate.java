package seedu.uninurse.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Patient} matches any of the keywords given.
 */
public class PatientContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;
    private final PersonContainsKeywordsPredicate personContainsKeywordsPredicate;

    /**
     * Constructs a {@code PatientContainsKeywordsPredicate}
     * which tests {@code Patient} to any of the keywords given.
     */
    public PatientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.personContainsKeywordsPredicate = new PersonContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean test(Patient person) {
        return personContainsKeywordsPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PatientContainsKeywordsPredicate) other).keywords)); // state check
    }
}
