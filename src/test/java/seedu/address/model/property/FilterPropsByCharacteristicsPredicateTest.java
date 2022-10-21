package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.characteristics.Characteristics;
import seedu.address.testutil.PropertyBuilder;


public class FilterPropsByCharacteristicsPredicateTest {

    @Test
    public void equals() {
        Characteristics oneCharacteristic = new Characteristics("HDB");
        Characteristics twoCharacteristic = new Characteristics("HDB;5-room");

        FilterPropsByCharacteristicsPredicate firstPredicate =
                new FilterPropsByCharacteristicsPredicate(oneCharacteristic);
        FilterPropsByCharacteristicsPredicate secondPredicate =
                new FilterPropsByCharacteristicsPredicate((twoCharacteristic));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterPropsByCharacteristicsPredicate firstPredicateCopy =
                new FilterPropsByCharacteristicsPredicate(oneCharacteristic);
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
        FilterPropsByCharacteristicsPredicate predicate =
                new FilterPropsByCharacteristicsPredicate(new Characteristics("Bishan"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("Bishan").build()));

        // Contains one characteristics
        predicate = new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));

        // Only match one characteristics
        predicate = new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB").build()));

        // Different Space
        predicate = new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB;5-room"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));

        // Different Case
        predicate = new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("hdb").build()));

        // Multiple characteristics
        predicate = new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));
    }

    @Test
    public void test_propertyDoesNotContainCharacteristics_returnFalse() {
        //No matching characteristics
        FilterPropsByCharacteristicsPredicate predicate =
                new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB"));
        assertFalse(predicate.test(new PropertyBuilder().withCharacteristics("Condo").build()));

        // No characteristics
        predicate = new FilterPropsByCharacteristicsPredicate(new Characteristics("HDB ; 5-room"));
        assertFalse(predicate.test(new PropertyBuilder().withNoCharacteristics().build()));

    }
}
