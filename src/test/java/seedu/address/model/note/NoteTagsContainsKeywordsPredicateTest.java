package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.NoteBuilder;

public class NoteTagsContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NoteTagsContainsKeywordsPredicate firstPredicate =
                new NoteTagsContainsKeywordsPredicate(firstPredicateKeywordList);
        NoteTagsContainsKeywordsPredicate secondPredicate =
                new NoteTagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NoteTagsContainsKeywordsPredicate firstPredicateCopy =
                new NoteTagsContainsKeywordsPredicate(firstPredicateKeywordList);
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
        NoteTagsContainsKeywordsPredicate predicate =
                new NoteTagsContainsKeywordsPredicate(Collections.singletonList("Tech"));
        assertTrue(predicate.test(new NoteBuilder().withTags("Tech").build()));

        // Multiple keywords
        predicate = new NoteTagsContainsKeywordsPredicate(Arrays.asList("Tech", "Finance"));
        assertTrue(predicate.test(new NoteBuilder().withTags("Tech").build()));

        // Only one matching keyword
        predicate = new NoteTagsContainsKeywordsPredicate(Arrays.asList("Finance", "Operations"));
        assertTrue(predicate.test(new NoteBuilder().withTags("Finance").build()));

        // Mixed-case keywords
        predicate = new NoteTagsContainsKeywordsPredicate(Arrays.asList("TeCh", "fINANce"));
        assertTrue(predicate.test(new NoteBuilder().withTags("Finance").build()));

        // Multiple tags
        predicate = new NoteTagsContainsKeywordsPredicate(Arrays.asList("Operations", "Tech"));
        assertTrue(predicate.test(new NoteBuilder().withTags("Finance", "Operations", "Tech").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NoteTagsContainsKeywordsPredicate predicate = new NoteTagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new NoteBuilder().withTags("Tech").build()));

        // Non-matching keyword
        predicate = new NoteTagsContainsKeywordsPredicate(Arrays.asList("Operations"));
        assertFalse(predicate.test(new NoteBuilder().withTags("Tech").build()));

        // Keywords match title and content, but do not match any tags
        predicate = new NoteTagsContainsKeywordsPredicate(Arrays.asList("Event", "Party"));
        assertFalse(predicate.test(new NoteBuilder().withTitle("Event").withContent("Party")
                .withTags("People").build()));
    }
}
