package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.ActivityBuilder;

public class NameOrDescContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameOrDescContainsKeywordsPredicate firstPredicate = 
                new NameOrDescContainsKeywordsPredicate(firstPredicateKeywordList);
        NameOrDescContainsKeywordsPredicate secondPredicate = 
                new NameOrDescContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameOrDescContainsKeywordsPredicate firstPredicateCopy = 
                new NameOrDescContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different activity -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameOrDescContainsKeywordsPredicate predicate = 
                new NameOrDescContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Alice Bob").build()));

        // Matching description
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Main", "Street"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Alice").withDescription("Main Street").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrDescContainsKeywordsPredicate predicate = 
                new NameOrDescContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ActivityBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Alice Bob").build()));
        
        // Non-matching keyword in name and description
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Alice Bob").withDescription("Charlie David").build()));
    }
}
