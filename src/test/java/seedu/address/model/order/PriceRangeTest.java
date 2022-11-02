package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceRangeTest {
    @Test
    public void constructor_lowerBoundHigherThanUpperBound_swap() {
        Price lowerBound = new Price(253.0);
        Price upperBound = new Price(156.76);
        PriceRange expected = new PriceRange(upperBound, lowerBound);
        PriceRange result = new PriceRange(lowerBound, upperBound);
        assertEquals(result, expected);
    }

    @Test
    public void getUpperBound() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        assertEquals(priceRange.getUpperBound(), upperBound);
    }

    @Test
    public void getLowerBound() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        assertEquals(priceRange.getLowerBound(), lowerBound);
    }

    @Test
    public void setUpperBound() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Price newUpperBound = new Price(65.77);
        priceRange.setUpperBound(newUpperBound);
        assertEquals(priceRange.getUpperBound(), newUpperBound);
        assertNotEquals(priceRange.getUpperBound(), upperBound);
    }

    @Test
    public void setLowerBound() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);
        Price newLowerBound = new Price(65.77);
        priceRange.setLowerBound(newLowerBound);
        assertEquals(priceRange.getLowerBound(), newLowerBound);
        assertNotEquals(priceRange.getLowerBound(), lowerBound);
    }

    @Test
    public void updatePriceRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        Price newUpperBound = new Price(89.4);
        Price newLowerBound = new Price(70.14);
        priceRange.updatePriceRange(newLowerBound, newUpperBound);

        assertEquals(priceRange.getLowerBound(), newLowerBound);
        assertNotEquals(priceRange.getLowerBound(), lowerBound);
        assertEquals(priceRange.getUpperBound(), newUpperBound);
        assertNotEquals(priceRange.getUpperBound(), upperBound);
    }

    @Test
    public void toString_samePriceRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        String expectedString = "Price range: " + lowerBound.getPrice() + " - " + upperBound.getPrice();
        assertEquals(priceRange.toString(), expectedString);
    }

    @Test
    public void toString_differentPriceRange() {
        Price firstUpperBound = new Price(79.5);
        Price firstLowerBound = new Price(19.5);
        PriceRange firstPriceRange = new PriceRange(firstLowerBound, firstUpperBound);

        Price secondUpperBound = new Price(667.0);
        Price secondLowerBound = new Price(11113.900);
        PriceRange secondPriceRange = new PriceRange(secondLowerBound, secondUpperBound);

        assertNotEquals(firstPriceRange.toString(), secondPriceRange.toString());
    }

    @Test
    public void hashCode_samePriceRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.hashCode(), priceRange.hashCode());
    }

    @Test
    public void hashCode_differentPriceRange() {
        Price firstUpperBound = new Price(79.5);
        Price firstLowerBound = new Price(19.5);
        PriceRange firstPriceRange = new PriceRange(firstLowerBound, firstUpperBound);

        Price secondUpperBound = new Price(667.0);
        Price secondLowerBound = new Price(11113.900);
        PriceRange secondPriceRange = new PriceRange(secondLowerBound, secondUpperBound);

        assertNotEquals(firstPriceRange.hashCode(), secondPriceRange.hashCode());
    }

    @Test
    public void equals() {
        Price firstUpperBound = new Price(79.5);
        Price firstLowerBound = new Price(19.5);
        PriceRange firstPriceRange = new PriceRange(firstLowerBound, firstUpperBound);

        Price secondUpperBound = new Price(667.0);
        Price secondLowerBound = new Price(11113.900);
        PriceRange secondPriceRange = new PriceRange(secondLowerBound, secondUpperBound);

        assertEquals(firstPriceRange, firstPriceRange);
        assertNotEquals(firstPriceRange, null);
        assertNotEquals(firstPriceRange, 1);

        PriceRange firstPriceRangeCopy = new PriceRange(firstLowerBound, firstUpperBound);
        assertEquals(firstPriceRange, firstPriceRangeCopy);

        assertNotEquals(firstPriceRange, secondPriceRange);
    }

    @Test
    public void comparePrice_closedRangeAndMiddlePrice_withinRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        Price price = new Price(50);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_closedRangeAndNotApplicablePrice_withinRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        Price price = Price.getNotApplicablePrice();
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice1_withinRange() {
        Price upperBound = Price.getNotApplicablePrice();
        Price lowerBound = new Price(19.5);
        Price price = Price.getNotApplicablePrice();
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice2_withinRange() {
        Price upperBound = Price.getNotApplicablePrice();
        Price lowerBound = Price.getNotApplicablePrice();
        Price price = Price.getNotApplicablePrice();
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice3_withinRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = Price.getNotApplicablePrice();
        Price price = Price.getNotApplicablePrice();
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice4_withinRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = Price.getNotApplicablePrice();
        Price price = new Price(79.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice5_withinRange() {
        Price upperBound = new Price(79.5);
        Price lowerBound = Price.getNotApplicablePrice();
        Price price = new Price(79.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice6_withinRange() {
        Price upperBound = Price.getNotApplicablePrice();
        Price lowerBound = new Price(79.5);
        Price price = new Price(79.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndNotApplicablePrice7_withinRange() {
        Price upperBound = Price.getNotApplicablePrice();
        Price lowerBound = Price.getNotApplicablePrice();
        Price price = new Price(79.5);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.WITHIN_RANGE);
    }

    @Test
    public void comparePrice_closedRangeAndMiddlePrice_lower() {
        Price upperBound = new Price(79.5);
        Price lowerBound = new Price(19.5);
        Price price = new Price(19.4);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.LOWER_THAN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndMiddlePrice_lower() {
        Price upperBound = Price.getNotApplicablePrice();
        Price lowerBound = new Price(19.5);
        Price price = new Price(19.4);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.LOWER_THAN_RANGE);
    }

    @Test
    public void comparePrice_closedRangeAndMiddlePrice_higher() {
        Price upperBound = new Price(50);
        Price lowerBound = new Price(19.5);
        Price price = new Price(51);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.HIGHER_THAN_RANGE);
    }

    @Test
    public void comparePrice_openRangeAndMiddlePrice_higher() {
        Price upperBound = new Price(50);
        Price lowerBound = Price.getNotApplicablePrice();
        Price price = new Price(51);
        PriceRange priceRange = new PriceRange(lowerBound, upperBound);

        assertEquals(priceRange.comparePrice(price), PriceRange.HIGHER_THAN_RANGE);
    }
}
