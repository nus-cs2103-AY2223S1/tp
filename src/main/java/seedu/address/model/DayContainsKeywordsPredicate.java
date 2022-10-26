package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.student.Student;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code Tuition Class}'s {@code Level} matches any of the keywords given.
 */
public class DayContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public DayContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof TuitionClass) {
            TuitionClass tuitionClass = (TuitionClass) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tuitionClass.getDay().day, keyword));
        }
        throw new ClassCastException("DayContainsKeyword predicate can only be applied to Person.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DayContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DayContainsKeywordsPredicate) other).keywords)); // state check
    }
}
