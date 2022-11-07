package foodwhere.model.review;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;

public class ReviewContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        ReviewContainsKeywordsPredicate firstPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("first")));
        ReviewContainsKeywordsPredicate secondPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("first")));

        ReviewContainsKeywordsPredicate thirdPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("second")));
        ReviewContainsKeywordsPredicate fourthPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("second")));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ReviewContainsKeywordsPredicate firstPredicateCopy =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("first")));
        ;
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different reviews -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ReviewContainsKeywordsPredicate predicate =
                new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("Alice")), Collections.emptyList());

        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("Alice"),
                new Name("Bob")), Collections.emptyList());
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("Carol"),
                new Name("Bob")), Collections.emptyList());
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("aLIce"),
                new Name("boB")), Collections.emptyList());
        assertTrue(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameAndTagsContainsKeywords_returnsTrue() {
        //contains name and tag keywords
        ReviewContainsKeywordsPredicate predicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));

        assertTrue(predicate.test(new ReviewBuilder().withName("Alice").withTags("Bob").build()));

        //contains only name keywords
        predicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));

        assertTrue(predicate.test(new ReviewBuilder().withName("Alice").build()));

        //contains only tag keywords
        predicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));

        assertTrue(predicate.test(new ReviewBuilder().withTags("Bob").build()));
    }

    @Test
    public void test_nameAndTagsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        ReviewContainsKeywordsPredicate predicate = new ReviewContainsKeywordsPredicate(Collections.emptyList(),
                Collections.emptyList());
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("Carol")),
                Collections.emptyList());
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice Bob").build()));

        // name_keywords match content, but does not match name
        predicate = new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("Main"), new Name("Street")),
                Collections.emptyList());
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice")
                .withContent("Main Street").build()));

        // name_keywords match tag, but does not match name
        predicate = new ReviewContainsKeywordsPredicate(Arrays.asList(new Name("Main"), new Name("Street")),
                Collections.emptyList());
        assertFalse(predicate.test(new ReviewBuilder().withName("Alice")
                .withTags("Main", "Street").build()));

        //tag_keyword match name, but does not match tag
        predicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));
        assertFalse(predicate.test(new ReviewBuilder().withName("Bob")
                .withTags("Alice").build()));
    }
}
