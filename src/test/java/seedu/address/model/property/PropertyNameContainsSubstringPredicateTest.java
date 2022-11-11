package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.buyer.BuyerNameContainsSubstringPredicate;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.PropertyBuilder;

public class PropertyNameContainsSubstringPredicateTest {

    @Test
    public void equals() {
        String firstPredicateString = "first";
        String secondPredicateString = "first second";

        seedu.address.model.property.PropertyNameContainsSubstringPredicate firstPredicate =
                new seedu.address.model.property.PropertyNameContainsSubstringPredicate(firstPredicateString);
        seedu.address.model.property.PropertyNameContainsSubstringPredicate secondPredicate =
                new seedu.address.model.property.PropertyNameContainsSubstringPredicate(secondPredicateString);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        seedu.address.model.property.PropertyNameContainsSubstringPredicate firstPredicateCopy =
                new seedu.address.model.property.PropertyNameContainsSubstringPredicate(firstPredicateString);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different property -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsSubstring_returnsTrue() {
        BuyerNameContainsSubstringPredicate predicate;

        // Mixed-case substring of one word
        predicate = new BuyerNameContainsSubstringPredicate("pEAk");
        assertTrue(predicate.test(new BuyerBuilder().withName("Peak Residence").build()));

        // Mixed-case substring across words
        predicate = new BuyerNameContainsSubstringPredicate("pEaK r");
        assertTrue(predicate.test(new BuyerBuilder().withName("Peak Residence").build()));
    }

    @Test
    public void test_nameDoesNotContainSubstring_returnsFalse() {
        PropertyNameContainsSubstringPredicate predicate =
                new PropertyNameContainsSubstringPredicate("pEaK l");
        assertFalse(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        predicate = new PropertyNameContainsSubstringPredicate("pEAl");
        assertFalse(predicate.test(new PropertyBuilder().withName("Peak Residence").build()));

        // Keywords match price, address and description, but does not match name
        predicate =
                new PropertyNameContainsSubstringPredicate("3000000 Main Street condo");
        assertFalse(predicate.test(new PropertyBuilder().withName("Peak Residence").withPrice("3000000")
              .withAddress("Main Street").withDescription("condo").build()));
    }
}
