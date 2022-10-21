package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.testutil.TutorBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("cs2100");
        List<String> secondPredicateKeywordList = Arrays.asList("cs2100", "cs2105");

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> return true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tutor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new TutorBuilder().withTags("friends").build()));

        // Mixed case keywords
        predicate = new TagContainsKeywordsPredicate(Collections.singletonList("frIeNds"));
        assertTrue(predicate.test(new TutorBuilder().withTags("friends").build()));

        // Partial keywords
        predicate = new TagContainsKeywordsPredicate(Collections.singletonList("iend"));
        assertTrue(predicate.test(new TutorBuilder().withTags("friends").build()));
    }

    @Test
    public void test_moduleDoesNotContainKeywords_returnFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("senior"));
        assertFalse(predicate.test(new TutorBuilder().withTags("friends").build()));

        // Keywords match phone and email, but does not match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("99999999", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new TutorBuilder().withName("Alice").withPhone("99999999")
                .withEmail("alice@email.com").withTags("friends").build()));
    }
}
