package seedu.address.model.student.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Phone} matches the phone given.
 */
public class PhoneContainsNumberPredicate implements Predicate<Student> {
    private String keyword;

    public PhoneContainsNumberPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWord(student.getPhone().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsNumberPredicate // instanceof handles nulls
                && keyword.equals(((PhoneContainsNumberPredicate) other).keyword)); // state check
    }
}
