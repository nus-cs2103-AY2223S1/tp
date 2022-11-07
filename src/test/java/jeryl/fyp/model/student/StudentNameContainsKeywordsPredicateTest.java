package jeryl.fyp.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jeryl.fyp.testutil.StudentBuilder;

public class StudentNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StudentNameContainsKeywordsPredicate firstPredicate = new StudentNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        StudentNameContainsKeywordsPredicate secondPredicate = new StudentNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentNameContainsKeywordsPredicate firstPredicateCopy = new StudentNameContainsKeywordsPredicate(
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
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        StudentNameContainsKeywordsPredicate predicate = new StudentNameContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withStudentName("Alice Bob").build()));

        // Multiple keywords
        predicate = new StudentNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withStudentName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new StudentNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withStudentName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new StudentNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withStudentName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentNameContainsKeywordsPredicate predicate = new StudentNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withStudentName("Alice").build()));

        // Non-matching keyword
        predicate = new StudentNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withStudentName("Alice Bob").build()));

        // Keywords match studentId, email and project name, but does not match name
        predicate = new StudentNameContainsKeywordsPredicate(
                Arrays.asList("A1234567X", "alice@email.com", "neural", "net"));
        assertFalse(predicate.test(new StudentBuilder().withStudentName("Alice").withStudentId("A1234567X")
                .withEmail("alice@email.com").withProjectName("neural net").build()));
    }
}
