package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import seedu.address.model.characteristics.Characteristics;
import seedu.address.testutil.PropertyBuilder;

public class FilterPropsContainingAnyCharacteristicPredicateTest {


    @Test
    public void constructor_nullCharacteristics_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FilterPropsContainingAnyCharacteristicPredicate(
                null));
    }

    @Test
    public void testEquals() {
        Characteristics oneCharacteristic = new Characteristics("HDB");
        Characteristics twoCharacteristic = new Characteristics("HDB; 5-room");

        FilterPropsContainingAnyCharacteristicPredicate firstPredicate =
                new FilterPropsContainingAnyCharacteristicPredicate(oneCharacteristic);
        FilterPropsContainingAnyCharacteristicPredicate secondPredicate =
                new FilterPropsContainingAnyCharacteristicPredicate((twoCharacteristic));

        assertEquals(firstPredicate, firstPredicate); // same object

        FilterPropsContainingAnyCharacteristicPredicate firstPredicateCopy =
                new FilterPropsContainingAnyCharacteristicPredicate(oneCharacteristic);
        assertEquals(firstPredicate, firstPredicateCopy); // same value

        assertNotEquals(null, firstPredicate); // null check

        assertNotEquals(firstPredicate, secondPredicate); // different values
    }

    @Test
    public void test_propertyContainsCharacteristic_returnTrue() {
        // Single characteristic
        FilterPropsContainingAnyCharacteristicPredicate predicate =
                new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("bright"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("bright").build()));

        // Multiple characteristics
        predicate = new FilterPropsContainingAnyCharacteristicPredicate(
                new Characteristics("HDB; 5-room; bright; sunny"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB; 5-room; bright; sunny").build()));

        // Different space
        predicate = new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("HDB;5-room"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB ; 5-room").build()));

        // Different case
        predicate = new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("HDB"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("hdb").build()));

        // Singular matching predicate
        predicate = new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("HDB; bright; sunny"));
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB").build()));

        // Matching and non-matching predicates
        assertTrue(predicate.test(new PropertyBuilder().withCharacteristics("HDB; dim; gloomy").build()));
    }

    @Test
    public void test_propertyDoesNotContainCharacteristics_returnFalse() {

        // Single non-matching characteristic
        FilterPropsContainingAnyCharacteristicPredicate predicate =
                new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("HDB; 5-room; bright; sunny"));
        assertFalse(predicate.test(new PropertyBuilder().withCharacteristics("Landed").build()));

        // Property with no characteristics
        predicate = new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("HDB"));
        assertFalse(predicate.test(new PropertyBuilder().build()));

        // Multiple non-matching predicates
        predicate = new FilterPropsContainingAnyCharacteristicPredicate(
                new Characteristics("HDB; bright; sunny; windy"));
        assertFalse(predicate.test(new PropertyBuilder().withCharacteristics("landed; 5-room; dim; gloomy").build()));

    }

}
