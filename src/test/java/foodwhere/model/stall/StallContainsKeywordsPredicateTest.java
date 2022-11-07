package foodwhere.model.stall;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;

public class StallContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        StallContainsKeywordsPredicate firstPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("first")));
        StallContainsKeywordsPredicate secondPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("first")));

        StallContainsKeywordsPredicate thirdPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("second")));
        StallContainsKeywordsPredicate fourthPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("second")));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StallContainsKeywordsPredicate firstPredicateCopy =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("first")));
        ;
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different stall -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        StallContainsKeywordsPredicate predicate =
                new StallContainsKeywordsPredicate(Arrays.asList(new Name("Alice")), Collections.emptyList());

        assertTrue(predicate.test(new StallBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new StallContainsKeywordsPredicate(Arrays.asList(new Name("Alice"),
                new Name("Bob")), Collections.emptyList());
        assertTrue(predicate.test(new StallBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new StallContainsKeywordsPredicate(Arrays.asList(new Name("Carol"),
                new Name("Bob")), Collections.emptyList());
        assertTrue(predicate.test(new StallBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new StallContainsKeywordsPredicate(Arrays.asList(new Name("aLIce"),
                new Name("boB")), Collections.emptyList());
        assertTrue(predicate.test(new StallBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameAndTagsContainsKeywords_returnsTrue() {
        //contains name and tag keywords
        StallContainsKeywordsPredicate predicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("Alice Bob")),
                        Collections.singleton(new Tag("Bob")));

        assertTrue(predicate.test(new StallBuilder().withName("Alice Bob").withTags("Bob").build()));

        //contains only name keywords
        predicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));

        assertTrue(predicate.test(new StallBuilder().withName("Alice").build()));

        //contains only tag keywords
        predicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));

        assertTrue(predicate.test(new StallBuilder().withTags("Bob").build()));
    }

    @Test
    public void test_nameAndTagsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        StallContainsKeywordsPredicate predicate = new StallContainsKeywordsPredicate(Collections.emptyList(),
                Collections.emptyList());
        assertFalse(predicate.test(new StallBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new StallContainsKeywordsPredicate(Arrays.asList(new Name("Carol")),
                Collections.emptyList());
        assertFalse(predicate.test(new StallBuilder().withName("Alice Bob").build()));

        // name_keywords match address, but does not match name
        predicate = new StallContainsKeywordsPredicate(Arrays.asList(new Name("Main"), new Name("Street")),
                Collections.emptyList());
        assertFalse(predicate.test(new StallBuilder().withName("Alice")
                .withAddress("Main Street").build()));

        // name_keywords match tag, but does not match name
        predicate = new StallContainsKeywordsPredicate(Arrays.asList(new Name("Main"), new Name("Street")),
                Collections.emptyList());
        assertFalse(predicate.test(new StallBuilder().withName("Alice")
                .withTags("Main", "Street").build()));

        //tag_keyword match name, but does not match tag
        predicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("Alice")),
                        Collections.singleton(new Tag("Bob")));
        assertFalse(predicate.test(new StallBuilder().withName("Bob")
                .withTags("Alice").build()));
    }
}
