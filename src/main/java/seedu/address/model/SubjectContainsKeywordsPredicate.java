package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code Tuition Class}'s {@code Subject} matches any of the keywords given.
 */
public class SubjectContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public SubjectContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof TuitionClass) {
            TuitionClass tuitionClass = (TuitionClass) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tuitionClass.getSubject().subject, keyword));
        }
        throw new ClassCastException("SubjectContainsKeyword predicate can only be applied to Person.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SubjectContainsKeywordsPredicate) other).keywords)); // state check
    }
}
