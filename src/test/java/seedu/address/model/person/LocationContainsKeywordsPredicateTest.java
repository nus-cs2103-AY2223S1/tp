package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class LocationContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        String firstKeyword = "first";
        String secondKeyword = "second";

        LocationContainsKeywordsPredicate firstPredicate = new LocationContainsKeywordsPredicate(firstKeyword);
        LocationContainsKeywordsPredicate secondPredicate = new LocationContainsKeywordsPredicate(secondKeyword);

        assertTrue(firstPredicate.equals(firstPredicate));

        LocationContainsKeywordsPredicate firstPredicateCopy = new LocationContainsKeywordsPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));

        assertFalse(firstPredicate.equals(null));

        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate("Singapore");
        Person alice = new PersonBuilder().withName("Alice Bob").withLocation("Singapore").buildBuyer();
        assertTrue(predicate.test(alice));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate("sinGApORe");
        Person bob = new PersonBuilder().withName("Bob Bob").withLocation("Singapore").buildBuyer();
        assertTrue(predicate.test(bob));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // One keyword
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate("London");
        Person alice = new PersonBuilder().withName("Alice Bob").withLocation("Singapore").buildBuyer();
        assertFalse(predicate.test(alice));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate("lOnDOn");
        Person bob = new PersonBuilder().withName("Bob Bob").withLocation("Singapore").buildBuyer();
        assertFalse(predicate.test(bob));
    }
}
