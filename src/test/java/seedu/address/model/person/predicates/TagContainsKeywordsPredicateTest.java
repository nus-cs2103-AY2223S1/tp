package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("Offered"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Offered").build()));

        // keyword in only 1 tag
        predicate = new TagContainsKeywordsPredicate(Collections.singletonList("Interviewed"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));

        // Multiple matching keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Offered", "KIV", "To", "be", "Interviewed"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Rejected", "Interviewing", "KIV"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("ReJectEd", "inTerVieWInG", "kiV"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));

        // Non-matching keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Rejected", "Interviewing"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));

        // Keywords are substrings
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("IV", "view"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Offered", "KIV", "To be Interviewed").build()));

        // Keywords match name, phone, email and address, but does not match Tag
        predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Offered", "KIV").build()));
    }
}
