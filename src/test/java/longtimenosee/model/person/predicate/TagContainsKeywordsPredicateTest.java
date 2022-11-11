package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateInput = List.of("friends");
        List<String> secondPredicateInput = List.of("family", "colleagues");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateInput);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal tags
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal tags
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // EP: matching single tag
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(List.of("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // EP: matching multiple tags
        predicate = new TagContainsKeywordsPredicate(List.of("friends", "colleagues", "family"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues", "family").build()));

        // EP: matching single tag with different casing
        predicate = new TagContainsKeywordsPredicate(List.of("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Friends").build()));

        // EP: matching multiple tags with different casing
        predicate = new TagContainsKeywordsPredicate(List.of("Friends", "FAMILY", "colleaGUES"));
        assertTrue(predicate.test(new PersonBuilder().withTags("family", "colleagues", "friends").build()));

        // EP: single keyword match one of many tags
        predicate = new TagContainsKeywordsPredicate(List.of("Friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("family", "colleagues", "friends").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // EP: single tag does not match
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(List.of("family"));
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // EP: partial match of keywords
        predicate = new TagContainsKeywordsPredicate(List.of("family", "friends", "colleagues"));
        assertFalse(predicate.test(new PersonBuilder().withTags("family", "friends").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("colleagues", "friends").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));
    }
}
