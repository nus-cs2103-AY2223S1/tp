package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = ("first");
        String secondPredicateKeywordList = "second";

        TagsContainsKeywordsPredicate firstPredicate = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice").build()));

        // Multiple keywords
        predicate = new TagsContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate("Carol");
        assertTrue(predicate.test(new PersonBuilder().withTags("Bob", "Carol").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate("ALIce");
        assertTrue(predicate.test(new PersonBuilder().withTags("Alice", "Bob").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withTags("Alice").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new TagsContainsKeywordsPredicate("Alice");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withGithub("alice")
                .withTags("Friend").build()));
    }
}
