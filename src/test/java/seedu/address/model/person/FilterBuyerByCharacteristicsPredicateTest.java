package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.characteristics.Characteristics;
import seedu.address.testutil.PersonBuilder;

public class FilterBuyerByCharacteristicsPredicateTest {

    @Test
    public void equals() {
        Characteristics oneCharacteristic = new Characteristics("HDB");
        Characteristics twoCharacteristic = new Characteristics("HDB;5-room");

        FilterBuyerByCharacteristicsPredicate firstPredicate =
                new FilterBuyerByCharacteristicsPredicate(oneCharacteristic);
        FilterBuyerByCharacteristicsPredicate secondPredicate =
                new FilterBuyerByCharacteristicsPredicate((twoCharacteristic));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterBuyerByCharacteristicsPredicate firstPredicateCopy =
                new FilterBuyerByCharacteristicsPredicate(oneCharacteristic);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> returns false
        assertFalse(firstPredicate.equals("HDB"));

        //null -> returns false
        assertFalse(firstPredicate.equals(null));

        //different characteristics -> false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_buyerContainsCharacteristics_returnTrue() {
        //One characteristics
        FilterBuyerByCharacteristicsPredicate predicate =
                new FilterBuyerByCharacteristicsPredicate(new Characteristics("Bishan"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("Bishan").build()));

        // Contains one characteristics
        predicate = new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));

        // Different Space
        predicate = new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB;5-room"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));

        // Different Case
        predicate = new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("hdb").build()));

        // Multiple characteristics
        predicate = new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));
    }

    @Test
    public void test_buyerDoesNotContainCharacteristics_returnFalse() {
        //No matching characteristics
        FilterBuyerByCharacteristicsPredicate predicate =
                new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB"));
        assertFalse(predicate.test(new PersonBuilder().withDesiredCharacteristics("Condo").build()));

        // Only match one characteristics
        predicate = new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB").build()));

        // No characteristics
        predicate = new FilterBuyerByCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PersonBuilder().withNoDesiredCharacteristics().build()));
    }
}
