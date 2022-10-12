package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PropertyBuilder;

public class PropertyNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropertyNameContainsKeywordsPredicate firstPredicate = new PropertyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        PropertyNameContainsKeywordsPredicate secondPredicate = new PropertyNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyNameContainsKeywordsPredicate firstPredicateCopy = new PropertyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different property -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PropertyNameContainsKeywordsPredicate predicate = new PropertyNameContainsKeywordsPredicate(Collections.singletonList("Peak"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        // Multiple keywords
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("Peak", "Residence"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        // Only one matching keyword
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("University", "Residence"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        // Mixed-case keywords
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("pEaK", "rESiDEnce"));
        assertTrue(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyNameContainsKeywordsPredicate predicate = new PropertyNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        // Non-matching keyword
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("Hut"));
        assertFalse(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        // Keywords match price, address and description, but does not match name
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("3000000", "Main", "Street", "condo"));
        assertFalse(predicate.test(new PropertyBuilder().withName("Peak Residence").withPrice("3000000")
              .withAddress("Main Street").withDescription("condo").build()));
    }
}
