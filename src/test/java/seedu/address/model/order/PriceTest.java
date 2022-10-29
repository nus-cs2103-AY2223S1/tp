package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceTest {
    @Test
    public void getNotApplicablePrice() {
        Price notApplicablePrice = Price.getNotApplicablePrice();
        assertEquals(notApplicablePrice, new Price(-1));
    }

    @Test
    public void isNotApplicablePrice() {
        Price validPrice = new Price(10);
        Price invalidPrice = new Price(-1);
        assertFalse(validPrice.isNotApplicablePrice());
        assertFalse(Price.isNotApplicablePrice(validPrice));
        assertTrue(invalidPrice.isNotApplicablePrice());
        assertTrue(Price.isNotApplicablePrice(invalidPrice));
    }

    @Test
    public void getDouble() {
        Price price = new Price(109.28794);
        assertEquals(price.getPrice(), 109.28794);
        assertNotEquals(price.getPrice(), 109.28795);
    }

    @Test
    public void setPrice() {
        Price price = new Price(109.28794);
        price.setPrice(109.28795);
        assertEquals(price.getPrice(), 109.28795);
        assertNotEquals(price.getPrice(), 109.28794);
    }

    @Test
    public void equals() {
        Price firstPrice = new Price(109.28794);
        Price secondPrice = new Price(109.28795);

        assertEquals(firstPrice, firstPrice);
        assertNotEquals(firstPrice, 1);
        assertNotEquals(firstPrice, null);

        Price firstPriceCopy = new Price(109.28794);
        assertEquals(firstPrice, firstPriceCopy);

        assertNotEquals(firstPrice, secondPrice);
    }

    @Test
    public void toString_unsettledPrice_returnsTrue() {
        String expectedString = "Price settled: " + "The price is not settled yet";
        String result = new Price(-1).toString();
        assertEquals(result, expectedString);
    }

    @Test
    public void toString_settledPrice_returnsTrue() {
        double settledPrice = 100.508;
        String expectedString = "Price settled: " + settledPrice;
        String result = new Price(settledPrice).toString();
        assertEquals(result, expectedString);
    }

    @Test
    public void hashcode_sameObject_returnsTrue() {
        Price firstPrice = new Price(109.28794);
        assertEquals(firstPrice.hashCode(), firstPrice.hashCode());
    }

    @Test
    public void hashcode_differentObject_returnsFalse() {
        Price firstPrice = new Price(109.28794);
        Price secondPrice = new Price(109.28795);
        assertNotEquals(firstPrice.hashCode(), secondPrice.hashCode());
    }

    @Test
    public void compareTo() {
        Price firstPrice = new Price(109.28794);
        Price secondPrice = new Price(109.28795);
        Price thirdPrice = new Price(109.28793);
        Price firstPriceCopy = new Price(109.28794);

        assertEquals(firstPrice.compareTo(firstPrice), 0);
        assertEquals(firstPrice.compareTo(firstPriceCopy), 0);
        assertTrue(firstPrice.compareTo(secondPrice) < 0);
        assertTrue(firstPrice.compareTo(thirdPrice) > 0);
    }
}
