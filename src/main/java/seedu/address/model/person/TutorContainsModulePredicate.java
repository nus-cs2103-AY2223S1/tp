package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.module.ModuleCode;

/**
 * Tests that a {@code Tutors}'s {@code ModuleCode} matches any of the keywords given.
 */
public class TutorContainsModulePredicate implements Predicate<Student> {

    private final ModuleCode keyword;

    public TutorContainsModulePredicate(ModuleCode keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student tutor) {
        return tutor.getTeachingAssistantInfo().stream().filter(module -> module.equals(keyword)).count() > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorContainsModulePredicate // instanceof handles nulls
                && keyword.equals(((TutorContainsModulePredicate) other).keyword)); // state check
    }
}
