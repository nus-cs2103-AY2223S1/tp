package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.ActivityBuilder;

public class NameOrDescContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Soccer");
        List<String> secondPredicateKeywordList = Arrays.asList("Badminton", "Soccer");

        NameOrDescContainsKeywordsPredicate firstPredicate =
                new NameOrDescContainsKeywordsPredicate(firstPredicateKeywordList);
        NameOrDescContainsKeywordsPredicate secondPredicate =
                new NameOrDescContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameOrDescContainsKeywordsPredicate firstPredicateCopy =
                new NameOrDescContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different activity -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameOrDescContainsKeywordsPredicate predicate =
                new NameOrDescContainsKeywordsPredicate(Collections.singletonList("Battlefield"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Multiple keywords
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Battlefield", "4"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Only one matching keyword
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("Battlefield", "5"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Mixed-case keywords
        predicate = new NameOrDescContainsKeywordsPredicate(Collections.singletonList("Battlefield"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Matching description
        predicate = new NameOrDescContainsKeywordsPredicate(Arrays.asList("First", "person", "shooter"));
        assertTrue(predicate.test(new ActivityBuilder().withName("Battlefield 4")
                .withDescription("First person shooter by EA")
                .build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrDescContainsKeywordsPredicate predicate =
                new NameOrDescContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Non-matching keyword
        predicate = new NameOrDescContainsKeywordsPredicate(List.of("CSGO"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4").build()));

        // Non-matching keyword in name and description
        predicate = new NameOrDescContainsKeywordsPredicate(List.of("CSGO"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4")
                .withDescription("First person shooter by EA").build()));

        // Date keyword
        predicate = new NameOrDescContainsKeywordsPredicate(List.of("date/2022-02-02"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4")
                .withDescription("First person shooter by EA").build()));

        // Rating keyword
        predicate = new NameOrDescContainsKeywordsPredicate(List.of("rate/1"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Battlefield 4")
                .withDescription("First person shooter by EA").build()));
    }
}
