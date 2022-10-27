package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.characteristics.Characteristics;
import seedu.address.testutil.PersonBuilder;

public class FilterBuyerContainingAllCharacteristicsPredicateTest {

    @Test
    public void equals() {
        Characteristics oneCharacteristic = new Characteristics("HDB");
        Characteristics twoCharacteristic = new Characteristics("HDB;5-room");

        FilterBuyerContainingAllCharacteristicsPredicate firstPredicate =
                new FilterBuyerContainingAllCharacteristicsPredicate(oneCharacteristic);
        FilterBuyerContainingAllCharacteristicsPredicate secondPredicate =
                new FilterBuyerContainingAllCharacteristicsPredicate((twoCharacteristic));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterBuyerContainingAllCharacteristicsPredicate firstPredicateCopy =
                new FilterBuyerContainingAllCharacteristicsPredicate(oneCharacteristic);
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
        FilterBuyerContainingAllCharacteristicsPredicate predicate =
                new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("Bishan"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("Bishan").build()));

        // Contains one characteristics
        predicate = new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));

        // Different Space
        predicate = new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB;5-room"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));

        // Different Case
        predicate = new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("hdb").build()));

        // Multiple characteristics
        predicate = new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertTrue(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));
    }

    @Test
    public void test_buyerDoesNotContainCharacteristics_returnFalse() {
        //No matching characteristics
        FilterBuyerContainingAllCharacteristicsPredicate predicate =
                new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertFalse(predicate.test(new PersonBuilder().withDesiredCharacteristics("Condo").build()));

        // Only match one characteristics
        predicate = new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PersonBuilder().withDesiredCharacteristics("HDB").build()));

        // No characteristics
        predicate = new FilterBuyerContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PersonBuilder().withNoDesiredCharacteristics().build()));
    }
}
