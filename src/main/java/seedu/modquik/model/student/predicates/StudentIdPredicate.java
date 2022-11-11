package seedu.modquik.model.student.predicates;

import java.util.function.Predicate;

import seedu.modquik.commons.util.StringUtil;
import seedu.modquik.model.student.Student;


/**
 * Tests that a {@code Student}'s {@code studentId} matches the keyword given.
 */
public class StudentIdPredicate implements Predicate<Student> {
    private final String keyword;

    public StudentIdPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWordIgnoreCase(student.getId().id, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentIdPredicate // instanceof handles nulls
                && keyword.equals(((StudentIdPredicate) other).keyword)); // state check
    }

}
