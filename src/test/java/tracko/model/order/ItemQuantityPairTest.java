package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import java.math.BigDecimal;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import tracko.model.item.Description;
import tracko.model.item.Item;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;

public class ItemQuantityPairTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemQuantityPair(null, null));
        Integer validQuantity = 3;
        assertThrows(NullPointerException.class, () -> new ItemQuantityPair(null, new Quantity(validQuantity)));
        Item validItem = new Item(new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(new BigDecimal(10)), new Price(new BigDecimal(20)));
        assertThrows(NullPointerException.class, () -> new ItemQuantityPair(validItem, null));
    }

    @Test
    public void getItem() {
        Item validItem = new Item(new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(new BigDecimal(10)), new Price(new BigDecimal(20)));
        Quantity validQuantity = new Quantity(1);
        ItemQuantityPair validPair = new ItemQuantityPair(validItem, validQuantity);

        assertSame(validItem, validPair.getItem());
    }

    @Test
    public void getQuantity() {
        Item validItem = new Item(new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(new BigDecimal(10)), new Price(new BigDecimal(20)));
        Quantity validQuantity = new Quantity(1);
        ItemQuantityPair validPair = new ItemQuantityPair(validItem, validQuantity);

        assertSame(validQuantity, validPair.getQuantity());
    }

    @Test
    public void toStringTest() {
        Item validItem = new Item(new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(new BigDecimal(10)), new Price(new BigDecimal(20)));
        Quantity validQuantity = new Quantity(1);
        ItemQuantityPair validPair = new ItemQuantityPair(validItem, validQuantity);

        assertEquals(validPair.toString(), validQuantity + " * " + validItem.getItemName());
    }

    @Test
    public void equals() {
        Item validItemOne = new Item(new ItemName("Valid Item Name One"), new Description("Valid Item Description One"),
                new Quantity(10), new HashSet<>(), new Price(new BigDecimal(10)), new Price(new BigDecimal(20)));
        Quantity validQuantityOne = new Quantity(1);
        Item validItemTwo = new Item(new ItemName("Valid Item Name Two"), new Description("Valid Item Description Two"),
                new Quantity(10), new HashSet<>(), new Price(new BigDecimal(20)), new Price(new BigDecimal(40)));
        Quantity validQuantityTwo = new Quantity(2);
        ItemQuantityPair validPairOne = new ItemQuantityPair(validItemOne, validQuantityOne);
        ItemQuantityPair validPairTwo = new ItemQuantityPair(validItemTwo, validQuantityTwo);
        ItemQuantityPair validPairThree = new ItemQuantityPair(validItemOne, validQuantityOne);

        assertTrue(validPairOne.equals(validPairOne));
        assertTrue(validPairOne.equals(validPairThree));
        assertFalse(validPairOne.equals(validPairTwo));
    }
}
