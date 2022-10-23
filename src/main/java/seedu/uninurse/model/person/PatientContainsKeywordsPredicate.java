package seedu.uninurse.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.uninurse.model.condition.ConditionContainsKeywordsPredicate;
import seedu.uninurse.model.task.TaskContainsKeywordsPredicate;

/**
 * Tests that a {@code Patient} matches any of the keywords given.
 */
public class PatientContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;
    private final PersonContainsKeywordsPredicate personContainsKeywordsPredicate;
    private final ConditionContainsKeywordsPredicate conditionContainsKeywordsPredicate;
    private final TaskContainsKeywordsPredicate taskContainsKeywordsPredicate;


    /**
     * Constructs a {@code PatientContainsKeywordsPredicate}
     * which tests {@code Patient} to any of the keywords given.
     */
    public PatientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.personContainsKeywordsPredicate = new PersonContainsKeywordsPredicate(keywords);
        this.conditionContainsKeywordsPredicate = new ConditionContainsKeywordsPredicate(keywords);
        this.taskContainsKeywordsPredicate = new TaskContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean test(Patient person) {
        return personContainsKeywordsPredicate.test(person)
                || conditionContainsKeywordsPredicate.test(person)
                || taskContainsKeywordsPredicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PatientContainsKeywordsPredicate) other).keywords)); // state check
    }
}
