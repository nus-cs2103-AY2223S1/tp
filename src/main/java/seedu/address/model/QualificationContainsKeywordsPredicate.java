package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;
import seedu.address.model.person.tutor.Tutor;

/**
 * Tests that a {@code Tutor}'s {@code Qualification} matches any of the keywords given.
 */
public class QualificationContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public QualificationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Tutor) {
            Tutor tutor = (Tutor) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutor.getQualification()
                            .qualification, keyword));
        }
        throw new ClassCastException("QualificationContainsKeyword predicate can only be applied to Person.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QualificationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((QualificationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
