package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class QuantityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidNum_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {

        assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));


        // invalid quantity numbers
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("test")); // non-numeric
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity("93 14")); // spaces within digits
        assertFalse(Quantity.isValidQuantity("+45")); // has "+"



        // valid quantity numbers
        assertTrue(Quantity.isValidQuantity("1"));
        assertTrue(Quantity.isValidQuantity("91"));
        assertTrue(Quantity.isValidQuantity("000009353"));
        assertTrue(Quantity.isValidQuantity("122237"));
    }

    @Test
    public void parseQualityArguments() {

        assertThrows(NullPointerException.class, () -> Quantity.parseQuantityArguments(null));

        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments(" "));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("."));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("-1"));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("0"));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("-0.5"));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("1000000000000"));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("2dasd0"));
        assertThrows(ParseException.class, () -> Quantity.parseQuantityArguments("adsadad asdasdsad"));

    }


    @Test
    public void testToString() {
        String value = "93124";
        Quantity quantity = new Quantity(value);
        assertEquals(quantity.toString(), value);
    }

    @Test
    public void value() {
        String value = "93124";
        Quantity quantity = new Quantity(value);
        assertEquals(quantity.value(), 93124);
    }

    @Test
    public void testEquals() {
        String value = "931214";
        Quantity quantity = new Quantity(value);
        Quantity test = new Quantity(value);
        assertEquals(quantity, test);
    }


}
