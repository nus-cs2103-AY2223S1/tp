package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.characteristics.Characteristics;
import seedu.address.testutil.BuyerBuilder;

public class FilterBuyerContainingAllCharacteristicsPredicateTest {

    @Test
    public void equals() {
        Characteristics oneCharacteristic = new Characteristics("HDB");
        Characteristics twoCharacteristic = new Characteristics("HDB;5-room");

        FilterBuyersContainingAllCharacteristicsPredicate firstPredicate =
                new FilterBuyersContainingAllCharacteristicsPredicate(oneCharacteristic);
        FilterBuyersContainingAllCharacteristicsPredicate secondPredicate =
                new FilterBuyersContainingAllCharacteristicsPredicate((twoCharacteristic));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterBuyersContainingAllCharacteristicsPredicate firstPredicateCopy =
                new FilterBuyersContainingAllCharacteristicsPredicate(oneCharacteristic);
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
        FilterBuyersContainingAllCharacteristicsPredicate predicate =
                new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("Bishan"));
        assertTrue(predicate.test(new BuyerBuilder().withDesiredCharacteristics("Bishan").build()));

        // Contains one characteristics
        predicate = new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new BuyerBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));

        // Different Space
        predicate = new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB;5-room"));
        assertTrue(predicate.test(new BuyerBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));

        // Different Case
        predicate = new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new BuyerBuilder().withDesiredCharacteristics("hdb").build()));

        // Multiple characteristics
        predicate = new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertTrue(predicate.test(new BuyerBuilder().withDesiredCharacteristics("HDB ; 5-room").build()));
    }

    @Test
    public void test_buyerDoesNotContainCharacteristics_returnFalse() {
        //No matching characteristics
        FilterBuyersContainingAllCharacteristicsPredicate predicate =
                new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertFalse(predicate.test(new BuyerBuilder().withDesiredCharacteristics("Condo").build()));

        // Only match one characteristics
        predicate = new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new BuyerBuilder().withDesiredCharacteristics("HDB").build()));

        // No characteristics
        predicate = new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new BuyerBuilder().withNoDesiredCharacteristics().build()));
    }
}
