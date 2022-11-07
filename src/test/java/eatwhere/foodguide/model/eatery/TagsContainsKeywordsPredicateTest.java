package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.EateryBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("firstTag");
        List<String> secondPredicateKeywordList = Arrays.asList("firstTag", "secondTag");

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

        // different eatery -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.singletonList("tag1"));
        assertTrue(predicate.test(new EateryBuilder().withTags("tag1").build()));

        // Multiple keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("tag1", "tag2"));
        assertTrue(predicate.test(new EateryBuilder().withTags("tag1", "tag2").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("tag1", "tag2"));
        assertTrue(predicate.test(new EateryBuilder().withTags("tag3", "tag2").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Tag1", "TAG2"));
        assertTrue(predicate.test(new EateryBuilder().withTags("tag1", "tag2").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EateryBuilder().withTags("tag").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("tag1"));
        assertFalse(predicate.test(new EateryBuilder().withTags("tag2", "tag3").build()));

        // Keywords match other fields, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("$$", "Eatery", "NUS", "Chinese"));
        assertFalse(predicate.test(new EateryBuilder().withName("Eatery").withPrice("$$")
                .withCuisine("Chinese").withLocation("NUS").withTags("tag1", "tag2").build()));
    }
}
