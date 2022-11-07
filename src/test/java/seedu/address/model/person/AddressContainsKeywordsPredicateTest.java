package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AddressContainsKeywordsPredicate firstPredicate =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        AddressContainsKeywordsPredicate secondPredicate =
                new AddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        AddressContainsKeywordsPredicate predicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("Kaki"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("81 Kaki Bukit Industrial Terrace").build()));

        // Multiple keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Kaki", "Bukit"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("81 Kaki Bukit Industrial Terrace").build()));

        // Only one matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Bukit", "Timah"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("81 Bukit Timah Rd").build()));

        // Mixed-case keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("kAKi", "BuKiT"));
        assertTrue(predicate.test(new PersonBuilder().withAddress("81 Kaki Bukit Industrial Terrace").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAddress("81 Kaki Bukit Industrial Terrace").build()));

        // Non-matching keyword
        predicate = new AddressContainsKeywordsPredicate(List.of("Sembawang"));
        assertFalse(predicate.test(new PersonBuilder().withAddress("81 Kaki Bukit Industrial Terrace").build()));

        // Keywords match name, phone and email, but does not match address.
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
