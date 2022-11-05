package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.price.PriceRange;
import seedu.address.testutil.PropertyBuilder;


public class FilterPropsByPricePredicateTest {

    @Test
    public void equals() {
        PriceRange priceRange1 = new PriceRange("200 - 500");
        PriceRange priceRange2 = new PriceRange("100 - 70000");

        FilterPropsByPricePredicate firstPredicate =
                new FilterPropsByPricePredicate(priceRange1);
        FilterPropsByPricePredicate secondPredicate =
                new FilterPropsByPricePredicate((priceRange2));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterPropsByPricePredicate firstPredicateCopy =
                new FilterPropsByPricePredicate(priceRange1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> returns false
        assertFalse(firstPredicate.equals("200 - 500"));

        //null -> returns false
        assertFalse(firstPredicate.equals(null));

        //different characteristics -> false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_propertyPriceWithinPriceRange_returnTrue() {

        PriceRange targetPriceRange = new PriceRange("1000 - 2000");
        //Within price range
        FilterPropsByPricePredicate predicate =
                new FilterPropsByPricePredicate(targetPriceRange);
        assertTrue(predicate.test(new PropertyBuilder().withPrice("1500").build()));

        //On price range upperbound
        predicate = new FilterPropsByPricePredicate(targetPriceRange);
        assertTrue(predicate.test(new PropertyBuilder().withPrice("2000").build()));

        //On price range lowerbound
        predicate = new FilterPropsByPricePredicate(targetPriceRange);
        assertTrue(predicate.test(new PropertyBuilder().withPrice("1000").build()));
    }

    @Test
    public void test_propertyPriceNotInPriceRange_returnFalse() {
        //No matching characteristics
        FilterPropsByPricePredicate predicate =
                new FilterPropsByPricePredicate(new PriceRange("0 - 2000"));
        assertFalse(predicate.test(new PropertyBuilder().withPrice("99999").build()));

    }
}
