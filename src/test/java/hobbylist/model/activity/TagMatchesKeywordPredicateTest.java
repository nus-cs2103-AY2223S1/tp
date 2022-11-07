package hobbylist.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.ActivityBuilder;

public class TagMatchesKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagMatchesKeywordPredicate firstPredicate = new TagMatchesKeywordPredicate(firstPredicateKeywordList);
        TagMatchesKeywordPredicate secondPredicate = new TagMatchesKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagMatchesKeywordPredicate firstPredicateCopy = new TagMatchesKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different activity -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagDoesNotMatchKeyword_returnsFalse() {
        // Zero keywords
        TagMatchesKeywordPredicate predicate = new TagMatchesKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ActivityBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new TagMatchesKeywordPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Alice Bob").build()));

        // Keywords match description, but does not match name
        predicate = new TagMatchesKeywordPredicate(Arrays.asList("Main", "Street"));
        assertFalse(predicate.test(new ActivityBuilder().withName("Alice").withDescription("Main Street").build()));
    }
}
