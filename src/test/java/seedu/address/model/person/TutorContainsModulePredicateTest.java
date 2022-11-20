package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.StudentBuilder;

public class TutorContainsModulePredicateTest {
    @Test
    public void equals() {
        ModuleCode firstPredicateKeyword = new ModuleCode("CS1101S");
        ModuleCode secondPredicateKeyword = new ModuleCode("CS1101S");

        TutorContainsModulePredicate firstPredicate = new TutorContainsModulePredicate(firstPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorContainsModulePredicate firstPredicateCopy = new TutorContainsModulePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_tutorContainsModule_returnsTrue() {
        // One Module
        TutorContainsModulePredicate predicate = new TutorContainsModulePredicate(new ModuleCode("CS2030S"));
        assertTrue(predicate.test(new StudentBuilder().withTeachingInfo("CS2030S").build()));

        // Multiple Modules
        predicate = new TutorContainsModulePredicate(new ModuleCode("CS2030S"));
        assertTrue(predicate.test(new StudentBuilder().withTeachingInfo("CS2040S", "CS2030S", "CS2100").build()));
    }

    @Test
    public void test_tutorDoesNotContainModule_returnsFalse() {
        // Non-matching keyword
        TutorContainsModulePredicate predicate = new TutorContainsModulePredicate(new ModuleCode("CS2040S"));
        assertFalse(predicate.test(new StudentBuilder().withTeachingInfo("CS2030S").build()));

        // Keywords match teaching info but not student info
        predicate = new TutorContainsModulePredicate(new ModuleCode("CS2030S"));
        assertFalse(predicate.test(new StudentBuilder().withStudentInfo("CS2030S").build()));
    }
}
