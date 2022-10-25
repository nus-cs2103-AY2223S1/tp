package tracko.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(ItemName.isValidItemName("!@#$%^&*")); // non-alphanumeric characters

        // valid ItemName
        assertTrue(ItemName.isValidItemName("Golden Spoon")); // alphabets only
        assertTrue(ItemName.isValidItemName("345")); // numbers only
        assertTrue(ItemName.isValidItemName("3 Golden Spoons")); // alphanumeric characters
        assertTrue(ItemName.isValidItemName("Queen-Sized Bed")); // with special characters
        assertTrue(ItemName.isValidItemName("Microsoft Windows Vista Ultimate UPGRADE Limited "
                + "Numbered Signature Edition")); // long item name
    }

    @Test
    public void equals() {
        String firstItemNameString = "Leather Sofa";
        String secondItemNameString = "Electronic Guitar";

        ItemName firstItemName = new ItemName(firstItemNameString);
        ItemName secondItemName = new ItemName(secondItemNameString);

        // same object -> returns true
        assertTrue(firstItemName.equals(firstItemName));

        ItemName firstItemNameCopy = new ItemName(firstItemNameString);
        // same values -> returns true
        assertTrue(firstItemName.equals(firstItemNameCopy));

        // different types -> returns false
        assertFalse(firstItemName.equals(3));
        assertFalse(firstItemName.equals(29.5));

        // null -> returns false
        assertFalse(firstItemName.equals(null));

        // different item name -> returns false
        assertFalse(firstItemName.equals(secondItemName));
    }
}
