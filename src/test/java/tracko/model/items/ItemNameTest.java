package tracko.model.items;

import org.junit.jupiter.api.Test;
import tracko.model.items.ItemName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

public class ItemNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));
    }

    @Test
    public void constructor_invalidItemName_throwsIllegalArgumentException() {
        String invalidItemName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemName(invalidItemName));
    }

    @Test
    public void isValidItemName() {
        // null
        assertThrows(NullPointerException.class, () -> ItemName.isValidItemName(null));

        // invalid ItemName
        assertFalse(ItemName.isValidItemName("")); // empty string
        assertFalse(ItemName.isValidItemName(" ")); // spaces only

        // valid ItemName
        assertTrue(ItemName.isValidItemName("Golden Spoon")); // alphabets only
        assertTrue(ItemName.isValidItemName("345")); // numbers only
        assertTrue(ItemName.isValidItemName("3 Golden Spoons")); // alphanumeric characters
        assertTrue(ItemName.isValidItemName("Queen-Sized Bed")); // with special characters
        assertTrue(ItemName.isValidItemName("Microsoft Windows Vista Ultimate UPGRADE Limited "
                + "Numbered Signature Edition")); // long item name
    }
}
