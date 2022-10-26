package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.student.Student;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code Student}'s or {@code TuitionClass}'s {@code Level} matches any of the keywords given.
 */
public class LevelContainsKeywordsPredicate<T> implements Predicate<T> {
    private final List<String> keywords;

    public LevelContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Student) {
            Student student = (Student) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getLevel().level, keyword));
        }

        if (t instanceof TuitionClass) {
            TuitionClass tuitionClass = (TuitionClass) t;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tuitionClass.getLevel().level, keyword));
        }
        throw new ClassCastException("LevelContainsKeyword predicate can only be applied to Person.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LevelContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LevelContainsKeywordsPredicate) other).keywords)); // state check
    }
}
