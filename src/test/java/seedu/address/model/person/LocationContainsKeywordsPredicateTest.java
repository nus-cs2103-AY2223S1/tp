package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

public class LocationContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstKeyword = Arrays.asList("first");
        List<String> secondKeyword = Arrays.asList("second");

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
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Singapore"));
        Person alice = new PersonBuilder().withName("Alice Bob").withLocation("Singapore").buildBuyer();
        assertTrue(predicate.test(alice));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("sinGApORe"));
        Person bob = new PersonBuilder().withName("Bob Bob").withLocation("Singapore").buildBuyer();
        assertTrue(predicate.test(bob));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // One keyword
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Arrays.asList("London"));
        Person alice = new PersonBuilder().withName("Alice Bob").withLocation("Singapore").buildBuyer();
        assertFalse(predicate.test(alice));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("lOnDOn"));
        Person bob = new PersonBuilder().withName("Bob Bob").withLocation("Singapore").buildBuyer();
        assertFalse(predicate.test(bob));
    }
}
