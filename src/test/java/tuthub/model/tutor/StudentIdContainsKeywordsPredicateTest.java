package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class StudentIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StudentIdContainsKeywordsPredicate firstPredicate =
                new StudentIdContainsKeywordsPredicate(firstPredicateKeywordList);
        StudentIdContainsKeywordsPredicate secondPredicate =
                new StudentIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentIdContainsKeywordsPredicate firstPredicateCopy =
                new StudentIdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentIdContainsKeywords_returnsTrue() {
        // One keyword
        StudentIdContainsKeywordsPredicate predicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("A0000000X"));
        assertTrue(predicate.test(new TutorBuilder().withStudentId("A0000000X").build()));

        // Partial keywords
        predicate = new StudentIdContainsKeywordsPredicate(Collections.singletonList("a0"));
        assertTrue(predicate.test(new TutorBuilder().withStudentId("A0000000X").build()));
    }

    @Test
    public void test_studentIdDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentIdContainsKeywordsPredicate predicate = new StudentIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withStudentId("A0000000X").build()));

        // Non-matching keyword
        predicate = new StudentIdContainsKeywordsPredicate(Arrays.asList("A0000000Y"));
        assertFalse(predicate.test(new TutorBuilder().withStudentId("A0000000X").build()));
    }
}
