package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagsContainsKeywordsPredicate firstPredicate = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword to one tag
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(
            Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        //One keyword to multiple tags
        predicate = new TagsContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // Multiple keywords to one tag
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Multiple matching keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "school").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("frIENdS", "cOLleAgUeS"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Arrays.asList("school"));
        assertFalse(predicate.test(new PersonBuilder().withTags("friends", "colleagues").build()));

        // Keywords match phone, email and address, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
            .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }
}
