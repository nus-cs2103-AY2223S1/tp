package seedu.modquik.model.student.predicates;

import java.util.function.Predicate;

import seedu.modquik.commons.util.StringUtil;
import seedu.modquik.model.student.Student;


/**
 * Tests that a {@code Student}'s {@code module} matches the keyword given.
 */
public class ModulePredicate implements Predicate<Student> {
    private final String keyword;

    public ModulePredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWordIgnoreCase(student.getModuleCode().moduleCode, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModulePredicate // instanceof handles nulls
                && keyword.equals(((ModulePredicate) other).keyword)); // state check
    }

}
