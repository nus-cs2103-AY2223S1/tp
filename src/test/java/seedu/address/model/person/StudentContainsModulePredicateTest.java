package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.StudentBuilder;

public class StudentContainsModulePredicateTest {

    @Test
    public void equals() {
        ModuleCode firstPredicateKeyword = new ModuleCode("CS1101S");
        ModuleCode secondPredicateKeyword = new ModuleCode("CS1101S");

        StudentContainsModulePredicate firstPredicate = new StudentContainsModulePredicate(firstPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentContainsModulePredicate firstPredicateCopy = new StudentContainsModulePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_studentContainsModule_returnsTrue() {
        // One Module
        StudentContainsModulePredicate predicate = new StudentContainsModulePredicate(new ModuleCode("CS2030S"));
        assertTrue(predicate.test(new StudentBuilder().withStudentInfo("CS2030S").build()));

        // Multiple Modules
        predicate = new StudentContainsModulePredicate(new ModuleCode("CS2030S"));
        assertTrue(predicate.test(new StudentBuilder().withStudentInfo("CS2040S", "CS2030S", "CS2100").build()));
    }

    @Test
    public void test_studentDoesNotContainModule_returnsFalse() {
        // Non-matching keyword
        StudentContainsModulePredicate predicate = new StudentContainsModulePredicate(new ModuleCode("CS2040S"));
        assertFalse(predicate.test(new StudentBuilder().withStudentInfo("CS2030S").build()));

        // Keywords match teaching info but not student info
        predicate = new StudentContainsModulePredicate(new ModuleCode("CS2030S"));
        assertFalse(predicate.test(new StudentBuilder().withTeachingInfo("CS2030S").build()));
    }
}
