package seedu.modquik.model.student.predicates;

import java.util.function.Predicate;

import seedu.modquik.commons.util.StringUtil;
import seedu.modquik.model.student.Student;


/**
 * Tests that a {@code Student}'s {@code tutorial} matches the keyword given.
 */
public class TutorialContainsKeywordPredicate implements Predicate<Student> {
    private final String keyword;

    public TutorialContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWordIgnoreCase(student.getTutorialName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((TutorialContainsKeywordPredicate) other).keyword)); // state check
    }

}
