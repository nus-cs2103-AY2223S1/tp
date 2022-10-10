package gim.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import gim.testutil.ExerciseBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different exercise -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Alice Bench Press").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bench"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Alice Bench Press").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bench", "Carol"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bENCH"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Alice Bench Press").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ExerciseBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ExerciseBuilder().withName("Alice Bench Press").build()));

        // Keywords match weight, sets and reps, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "1", "1"));
        assertFalse(predicate.test(new ExerciseBuilder().withName("Alice").withWeight("12345")
                .withSets("1").withReps("1").build()));
    }
}
