package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.characteristics.Characteristics;
import seedu.address.testutil.PropertyBuilder;


public class FilterPropsContainingAllCharacteristicsPredicateTest {

    @Test
    public void equals() {
        Characteristics oneCharacteristic = new Characteristics("HDB");
        Characteristics twoCharacteristic = new Characteristics("HDB;5-room");

        FilterPropsContainingAllCharacteristicsPredicate firstPredicate =
                new FilterPropsContainingAllCharacteristicsPredicate(oneCharacteristic);
        FilterPropsContainingAllCharacteristicsPredicate secondPredicate =
                new FilterPropsContainingAllCharacteristicsPredicate((twoCharacteristic));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterPropsContainingAllCharacteristicsPredicate firstPredicateCopy =
                new FilterPropsContainingAllCharacteristicsPredicate(oneCharacteristic);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> returns false
        assertFalse(firstPredicate.equals("HDB"));

        //null -> returns false
        assertFalse(firstPredicate.equals(null));

        //different characteristics -> false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_propertyContainsCharacteristics_returnTrue() {
        //One characteristics
        FilterPropsContainingAllCharacteristicsPredicate predicate =
                new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("Bishan"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("Bishan").build()));

        // Contains one characteristics
        predicate = new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));

        // Different Space
        predicate = new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB;5-room"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));

        // Different Case
        predicate = new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("hdb").build()));

        // Multiple characteristics
        predicate = new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));
    }

    @Test
    public void test_propertyDoesNotContainCharacteristics_returnFalse() {
        // No matching characteristics
        FilterPropsContainingAllCharacteristicsPredicate predicate =
                new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB"));
        assertFalse(predicate.test(new PropertyBuilder().withCharacteristics("Condo").build()));

        // Only match one characteristics
        predicate = new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PropertyBuilder().withCharacteristics("HDB").build()));


        // No characteristics
        predicate = new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PropertyBuilder().withNoCharacteristics().build()));

    }
}
