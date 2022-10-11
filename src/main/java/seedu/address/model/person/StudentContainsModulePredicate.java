package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.module.ModuleCode;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class StudentContainsModulePredicate implements Predicate<Person> {
    private final ModuleCode keyword;

    public StudentContainsModulePredicate(ModuleCode keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        Student temp;
        if (person instanceof Student) {
            temp = (Student) person;
        } else {
            return false;
        }
        return temp.getStudentModuleInfo().stream().filter(module -> module.equals(keyword)).count() > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentContainsModulePredicate // instanceof handles nulls
                && keyword.equals(((StudentContainsModulePredicate) other).keyword)); // state check
    }
}
