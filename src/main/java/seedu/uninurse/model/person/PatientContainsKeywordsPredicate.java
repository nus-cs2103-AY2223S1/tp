package seedu.uninurse.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.model.condition.ConditionContainsKeywordsPredicate;
import seedu.uninurse.model.medication.MedicationContainsKeywordsPredicate;
import seedu.uninurse.model.task.TaskContainsKeywordsPredicate;

/**
 * Tests that a {@code Patient} matches any of the keywords given.
 */
public class PatientContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;
    private final List<Predicate<? super Patient>> predicates;


    /**
     * Constructs a {@code PatientContainsKeywordsPredicate}
     * which tests {@code Patient} to any of the keywords given.
     */
    public PatientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.predicates = new ArrayList<>();
        this.predicates.add(new PersonContainsKeywordsPredicate(keywords));
        this.predicates.add(new ConditionContainsKeywordsPredicate(keywords));
        this.predicates.add(new TaskContainsKeywordsPredicate(keywords));
        this.predicates.add(new MedicationContainsKeywordsPredicate(keywords));
    }

    @Override
    public boolean test(Patient person) {
        return predicates.stream().anyMatch(x -> x.test(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PatientContainsKeywordsPredicate) other).keywords)); // state check
    }
}
