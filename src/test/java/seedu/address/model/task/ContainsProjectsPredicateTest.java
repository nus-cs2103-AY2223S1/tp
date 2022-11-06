package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class ContainsProjectsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateContact = List.of("first");
        List<String> secondPredicateContact = List.of("second");

        ContainsProjectsPredicate firstPredicate = new ContainsProjectsPredicate(firstPredicateContact);
        ContainsProjectsPredicate secondPredicate = new ContainsProjectsPredicate(secondPredicateContact);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsProjectsPredicate firstPredicateCopy = new ContainsProjectsPredicate(firstPredicateContact);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different teammate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsProject_returnsTrue() {
        List<String> singleProject = List.of("Test Project");

        ContainsProjectsPredicate predicate = new ContainsProjectsPredicate(singleProject);

        Task testTask =
                new TaskBuilder().withTitle("Test").withProject("Test Project").build();

        assertTrue(predicate.test(testTask));

        List<String> multipleProject = List.of("Test Project", "Another Project");
        ContainsProjectsPredicate multiPredicate = new ContainsProjectsPredicate(multipleProject);
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_doesNotContainProject_returnsFalse() {
        List<String> singleProject = List.of("Test Project");

        // Matching None
        ContainsProjectsPredicate predicate = new ContainsProjectsPredicate(singleProject);
        assertFalse(
                predicate.test(new TaskBuilder().withTitle("Test").withProject("Another Project").build())
        );
    }

}

