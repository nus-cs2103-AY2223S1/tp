package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Item(null));
    }

    @Test
    public void constructor_invalidItem_throwsIllegalArgumentException() {
        String invalidItem = "";
        assertThrows(IllegalArgumentException.class, () -> new Item(invalidItem));
    }

    @Test
    public void isValidItem() {
        // null item
        assertThrows(NullPointerException.class, () -> Item.isValidItem(null));

        // invalid items
        assertFalse(Item.isValidItem("")); // empty string
        assertFalse(Item.isValidItem(" ")); // spaces only
        assertFalse(Item.isValidItem("11")); // numbers only

        // valid items
        assertTrue(Item.isValidItem("Broccoli"));
        assertTrue(Item.isValidItem("Celery"));
        assertTrue(Item.isValidItem("Luncheon Meat")); // two words
        assertTrue(Item.isValidItem("Chicken 65 masala")); // items with numbers in them
    }
}
