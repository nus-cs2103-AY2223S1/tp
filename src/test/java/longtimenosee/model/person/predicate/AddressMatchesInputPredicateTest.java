package longtimenosee.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import longtimenosee.testutil.PersonBuilder;

public class AddressMatchesInputPredicateTest {
    @Test
    public void test_equals() {
        String firstPredicateInput = "bedok";
        String secondPredicateInput = "Blk 522";

        AddressContainsInputPredicate firstPredicate = new AddressContainsInputPredicate(firstPredicateInput);
        AddressContainsInputPredicate secondPredicate = new AddressContainsInputPredicate(secondPredicateInput);

        // EP: null value
        assertFalse(firstPredicate.equals(null)); // Boundary value

        // EP: same object
        assertTrue(firstPredicate.equals(firstPredicate));

        // EP: same internal address
        AddressContainsInputPredicate firstPredicateCopy = new AddressContainsInputPredicate(firstPredicateInput);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // EP: different internal address
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsInput_returnsTrue() {
        // EP: single spacing
        // Note: Spacings wil be matched as if it is a character
        AddressContainsInputPredicate predicate = new AddressContainsInputPredicate(" ");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));

        // EP: address contains whole keyword
        predicate = new AddressContainsInputPredicate("Bedok");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));

        // EP: address contains whole input with multiple keywords
        predicate = new AddressContainsInputPredicate("123 Bedok");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));

        predicate = new AddressContainsInputPredicate("123 Bedok St");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));

        // EP: address contains input with different casing
        predicate = new AddressContainsInputPredicate("123 beDOK");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));
    }

    @Test
    public void test_addressDoesNotContainInput_returnsFalse() {
        // EP: address does not contain input
        AddressContainsInputPredicate predicate = new AddressContainsInputPredicate("Orchard");
        assertFalse(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));

        // EP: address only contains part of input
        predicate = new AddressContainsInputPredicate("Blk 55 Bedok");
        assertFalse(predicate.test(new PersonBuilder().withAddress("Blk 123 Bedok St 54").build()));
    }
}
