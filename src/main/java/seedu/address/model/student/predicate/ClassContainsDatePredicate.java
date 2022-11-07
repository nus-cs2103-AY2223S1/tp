package seedu.address.model.student.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Class} matches the date given.
 */
public class ClassContainsDatePredicate implements Predicate<Student> {
    private final String keyword;

    public ClassContainsDatePredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWord(student.getAClass().classDateTime, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassContainsDatePredicate // instanceof handles nulls
                && keyword.equals(((ClassContainsDatePredicate) other).keyword)); // state check
    }

}
