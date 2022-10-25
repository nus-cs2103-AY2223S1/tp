package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PriceRangeTest {
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
}
