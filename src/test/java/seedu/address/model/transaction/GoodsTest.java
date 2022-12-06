package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GoodsTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Goods(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Goods(invalidName));

        String invalidName2 = "$2";
        assertThrows(IllegalArgumentException.class, () -> new Goods(invalidName2));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Goods.isValidName(null));

        // invalid name
        assertFalse(Goods.isValidName("")); // empty string
        assertFalse(Goods.isValidName(" ")); // spaces only
        assertFalse(Goods.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Goods.isValidName("fruits*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Goods.isValidName("frozen beef")); // alphabets only
        assertTrue(Goods.isValidName("12345")); // numbers only
        assertTrue(Goods.isValidName("2nd grade beef")); // alphanumeric characters
        assertTrue(Goods.isValidName("Buttoned Shirt")); // with capital letters
        assertTrue(Goods.isValidName("Buttoned Shirt Long Sleeves")); // long names
    }

    @Test
    public void toString_returnsValueInName() {
        String value = "Buttoned Shirt";
        Goods goodsName = new Goods(value);
        assertEquals(goodsName.toString(), value);
    }

    @Test
    public void equals() {
        String value = "Buttoned Shirt";
        Goods goodsName = new Goods(value);
        Goods secondGoods = new Goods(value);
        assertEquals(goodsName, secondGoods);
    }
}
