package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import tracko.model.item.Description;
import tracko.model.item.InventoryItem;
import tracko.model.item.ItemName;
import tracko.model.item.Price;
import tracko.model.item.Quantity;

public class ItemQuantityPairTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemQuantityPair(null, null));
        Integer validQuantity = 3;
        assertThrows(NullPointerException.class, () -> new ItemQuantityPair(null, new Quantity(validQuantity)));
        InventoryItem validInventoryItem = new InventoryItem(
            new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(10.00), new Price(20.00));
        assertThrows(NullPointerException.class, () -> new ItemQuantityPair(validInventoryItem, null));
    }

    @Test
    public void getItem() {
        InventoryItem validInventoryItem = new InventoryItem(
            new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(10.00), new Price(20.00));
        Quantity validQuantity = new Quantity(1);
        ItemQuantityPair validPair = new ItemQuantityPair(validInventoryItem, validQuantity);

        assertSame(validInventoryItem, validPair.getItem());
    }

    @Test
    public void getQuantity() {
        InventoryItem validInventoryItem = new InventoryItem(
            new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(9.99), new Price(19.99));
        Quantity validQuantity = new Quantity(1);
        ItemQuantityPair validPair = new ItemQuantityPair(validInventoryItem, validQuantity);

        assertSame(validQuantity, validPair.getQuantity());
    }

    @Test
    public void toStringTest() {
        InventoryItem validInventoryItem = new InventoryItem(
            new ItemName("Valid Item Name"), new Description("Valid Item Description"),
                new Quantity(10), new HashSet<>(), new Price(9.9), new Price(19.9));
        Quantity validQuantity = new Quantity(1);
        ItemQuantityPair validPair = new ItemQuantityPair(validInventoryItem, validQuantity);

        assertEquals(validPair.toString(), validQuantity + " * " + validInventoryItem.getItemName());
    }

    @Test
    public void test_calculatePrice_success() {
        InventoryItem validItem = new InventoryItem(new ItemName("Valid Item Name"),
            new Description("Valid Item Description"), new Quantity(10), new HashSet<>(),
                new Price(523.98), new Price(300.67));
        Quantity validQuantity = new Quantity(50);
        ItemQuantityPair validPair = new ItemQuantityPair(validItem, validQuantity);
        Double validPairPrice = validPair.calculatePrice();

        assertEquals(validPairPrice, 26199.00);
        assertNotEquals(validPairPrice, 500);
    }

    @Test
    public void equals() {
        InventoryItem validInventoryItemOne = new InventoryItem(
            new ItemName("Valid Item Name One"), new Description("Valid Item Description One"),
                new Quantity(10), new HashSet<>(), new Price(10.00), new Price(20.00));
        Quantity validQuantityOne = new Quantity(1);
        InventoryItem validInventoryItemTwo = new InventoryItem(
            new ItemName("Valid Item Name Two"), new Description("Valid Item Description Two"),
                new Quantity(10), new HashSet<>(), new Price(20.00), new Price(39.99));
        Quantity validQuantityTwo = new Quantity(2);
        ItemQuantityPair validPairOne = new ItemQuantityPair(validInventoryItemOne, validQuantityOne);
        ItemQuantityPair validPairTwo = new ItemQuantityPair(validInventoryItemTwo, validQuantityTwo);
        ItemQuantityPair validPairThree = new ItemQuantityPair(validInventoryItemOne, validQuantityOne);

        assertTrue(validPairOne.equals(validPairOne));
        assertTrue(validPairOne.equals(validPairThree));
        assertFalse(validPairOne.equals(validPairTwo));
    }
}
