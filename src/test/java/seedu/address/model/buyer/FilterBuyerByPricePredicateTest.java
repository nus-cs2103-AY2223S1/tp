package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.Price;
import seedu.address.testutil.BuyerBuilder;

public class FilterBuyerByPricePredicateTest {

    @Test
    public void equals() {
        Price price1 = new Price("1234");
        Price price2 = new Price("77777");

        FilterBuyerByPricePredicate firstPredicate =
                new FilterBuyerByPricePredicate(price1);
        FilterBuyerByPricePredicate secondPredicate =
                new FilterBuyerByPricePredicate((price2));

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        //same value -> returns true
        FilterBuyerByPricePredicate firstPredicateCopy =
                new FilterBuyerByPricePredicate(price1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> returns false
        assertFalse(firstPredicate.equals("1234"));

        //null -> returns false
        assertFalse(firstPredicate.equals(null));

        //different characteristics -> false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_priceWithinBuyerPriceRange_returnTrue() {

        Price targetPrice = new Price("9999");
        //Within price range
        FilterBuyerByPricePredicate predicate =
                new FilterBuyerByPricePredicate(targetPrice);
        assertTrue(predicate.test(new BuyerBuilder().withPriceRange("1 - 10000").build()));

        //On price range upperbound
        predicate = new FilterBuyerByPricePredicate(targetPrice);
        assertTrue(predicate.test(new BuyerBuilder().withPriceRange("1 - 9999").build()));

        //On price range lowerbound
        predicate = new FilterBuyerByPricePredicate(targetPrice);
        assertTrue(predicate.test(new BuyerBuilder().withPriceRange("9999 - 10000").build()));
    }

    @Test
    public void test_priceNotInPriceRange_returnFalse() {
        //No matching characteristics
        FilterBuyerByPricePredicate predicate =
                new FilterBuyerByPricePredicate(new Price("0"));
        assertFalse(predicate.test(new BuyerBuilder().withPriceRange("1000-2000").build()));

    }
}
