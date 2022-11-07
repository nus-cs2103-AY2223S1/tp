package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.predicate.AddressContainsKeywordsPredicate;
import seedu.address.testutil.StudentBuilder;

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
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy =
                new AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        AddressContainsKeywordsPredicate predicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("Labrador"));
        assertTrue(predicate.test(new StudentBuilder().withAddress("Tampines Labrador").build()));

        // Multiple keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Tampines", "Labrador"));
        assertTrue(predicate.test(new StudentBuilder().withAddress("Tampines Labrador").build()));

        // Only one matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Labrador", "Caldecott"));
        assertTrue(predicate.test(new StudentBuilder().withAddress("Labrador Caldecott").build()));

        // Mixed-case keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("tAMpiNes", "lAbrAdOR"));
        assertTrue(predicate.test(new StudentBuilder().withAddress("Tampines Labrador").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withAddress("Tampines").build()));

        // Non-matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Caldecott"));
        assertFalse(predicate.test(new StudentBuilder().withAddress("Tampines Labrador").build()));

        // Keywords match phone, email and name, but does not match address
        predicate =
                new AddressContainsKeywordsPredicate(Arrays.asList("81234567", "alice@email.com", "Alice", "Streep"));
        assertFalse(predicate.test(new StudentBuilder().withAddress("21 main street Downing Road").withPhone("81234567")
                .withEmail("alice@email.com").withName("Alice Streep").build()));
    }
}
