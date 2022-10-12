package jeryl.fyp.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jeryl.fyp.testutil.StudentBuilder;

public class ProjectNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ProjectNameContainsKeywordsPredicate firstPredicate = new ProjectNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        ProjectNameContainsKeywordsPredicate secondPredicate = new ProjectNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProjectNameContainsKeywordsPredicate firstPredicateCopy = new ProjectNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_projectNameContainsKeywords_returnsTrue() {
        // One keyword
        ProjectNameContainsKeywordsPredicate predicate = new ProjectNameContainsKeywordsPredicate(
                Collections.singletonList("neural"));
        assertTrue(predicate.test(new StudentBuilder().withProjectName("neural network").build()));

        // Multiple keywords
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("neural", "network"));
        assertTrue(predicate.test(new StudentBuilder().withProjectName("neural network").build()));

        // Only one matching keyword
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("graph", "network"));
        assertTrue(predicate.test(new StudentBuilder().withProjectName("neural network").build()));

        // Mixed-case keywords
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("neUrAl", "NetWORK"));
        assertTrue(predicate.test(new StudentBuilder().withProjectName("neural network").build()));
    }

    @Test
    public void test_projectNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ProjectNameContainsKeywordsPredicate predicate = new ProjectNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withProjectName("neural network").build()));

        // Non-matching keyword
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("graph"));
        assertFalse(predicate.test(new StudentBuilder().withProjectName("neural network").build()));

        // Keywords match name, studentId, and email, but does not match name
        predicate = new ProjectNameContainsKeywordsPredicate(Arrays.asList("Alice", "A1234567X", "alice@email.com"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withStudentId("A1234567X")
                .withEmail("alice@email.com").withProjectName("neural net").build()));
    }
}
