package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTagsContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonTagsContainsKeywordsPredicate firstPredicate =
                new PersonTagsContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonTagsContainsKeywordsPredicate secondPredicate =
                new PersonTagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagsContainsKeywordsPredicate firstPredicateCopy =
                new PersonTagsContainsKeywordsPredicate(firstPredicateKeywordList);
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
        PersonTagsContainsKeywordsPredicate predicate =
                new PersonTagsContainsKeywordsPredicate(Collections.singletonList("Tech"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Tech").build()));

        // Multiple keywords
        predicate = new PersonTagsContainsKeywordsPredicate(Arrays.asList("Tech", "Finance"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Tech").build()));

        // Only one matching keyword
        predicate = new PersonTagsContainsKeywordsPredicate(Arrays.asList("Finance", "Operations"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Finance").build()));

        // Mixed-case keywords
        predicate = new PersonTagsContainsKeywordsPredicate(Arrays.asList("TeCh", "fINANce"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Finance").build()));

        // Multiple tags
        predicate = new PersonTagsContainsKeywordsPredicate(Arrays.asList("Operations", "Tech"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Finance", "Operations", "Tech").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonTagsContainsKeywordsPredicate predicate =
                new PersonTagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("Tech").build()));

        // Non-matching keyword
        predicate = new PersonTagsContainsKeywordsPredicate(Arrays.asList("Operations"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Tech").build()));

        // Keywords match phone, email and address, but do not match any tags
        predicate =
                new PersonTagsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Tech").build()));
    }
}
