package seedu.address.model.student.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Phone} matches the phone given.
 */
public class NokPhoneContainsNumberPredicate implements Predicate<Student> {
    private String keyword;

    public NokPhoneContainsNumberPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWord(student.getNokPhone().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NokPhoneContainsNumberPredicate // instanceof handles nulls
                && keyword.equals(((NokPhoneContainsNumberPredicate) other).keyword)); // state check
    }
}
