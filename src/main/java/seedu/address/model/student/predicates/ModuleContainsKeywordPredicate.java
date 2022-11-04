package seedu.address.model.student.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.student.Student;


/**
 * Tests that a {@code Student}'s {@code module} matches the keyword given.
 */
public class ModuleContainsKeywordPredicate implements Predicate<Student> {
    private final String keyword;

    public ModuleContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.containsWordIgnoreCase(student.getModuleCode().moduleCode, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((ModuleContainsKeywordPredicate) other).keyword)); // state check
    }

}
