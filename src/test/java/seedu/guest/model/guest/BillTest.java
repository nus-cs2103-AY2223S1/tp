package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BillTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Bill(null));
    }

    @Test
    public void constructor_invalidBill_throwsIllegalArgumentException() {
        String invalidBill = "";
        assertThrows(IllegalArgumentException.class, () -> new Bill(invalidBill));
    }

    @Test
    public void isValidBill() {
        // null bill
        assertThrows(NullPointerException.class, () -> Bill.isValidBill(null));

        // invalid bill
        assertFalse(Bill.isValidBill("")); // empty string
        assertFalse(Bill.isValidBill(" ")); // spaces only
        assertFalse(Bill.isValidBill(".")); // decimal point only
        assertFalse(Bill.isValidBill("abc")); // non-numerical input
        assertFalse(Bill.isValidBill("1..00")); // 2 decimal points
        assertFalse(Bill.isValidBill("1.500")); // more than 2 decimal places
        assertFalse(Bill.isValidBill("1,000")); // comma separator
        assertFalse(Bill.isValidBill("1 000")); // space separator
        assertFalse(Bill.isValidBill("100-")); // wrong location of hyphen
        assertFalse(Bill.isValidBill("100+")); // wrong location of plus sign
        assertFalse(Bill.isValidBill("--100")); // more than 1 hyphen
        assertFalse(Bill.isValidBill("++100")); // more than 1 plus sign
        assertFalse(Bill.isValidBill("+-100")); // both hyphen and plus sign
        assertFalse(Bill.isValidBill("-+100")); // both hyphen and plus sign
        assertFalse(Bill.isValidBill("-1000000000000.00")); // out of bounds
        assertFalse(Bill.isValidBill("1000000000000.00")); // out of bounds


        // valid bill
        assertTrue(Bill.isValidBill("0")); // 0
        assertTrue(Bill.isValidBill("+1")); // positive number
        assertTrue(Bill.isValidBill("-1")); // negative number
        assertTrue(Bill.isValidBill("-0")); // signed zero
        assertTrue(Bill.isValidBill("-1.99")); // signed number with decimals
        assertTrue(Bill.isValidBill(".99")); // starts with decimal point
        assertTrue(Bill.isValidBill("+.99")); // signed and starts with decimal point
        assertTrue(Bill.isValidBill("1.")); // no decimal place
        assertTrue(Bill.isValidBill("1.5")); // 1 decimal place
        assertTrue(Bill.isValidBill("1.99")); // 2 decimal places
        assertTrue(Bill.isValidBill("10")); // integer
        assertTrue(Bill.isValidBill("-999999999999.99")); // min value
        assertTrue(Bill.isValidBill("999999999999.99")); // max value
    }

    @Test
    public void hashcode() {
        Bill tempBill = new Bill("9.99");

        // same values -> return true
        assertEquals(tempBill, new Bill("9.99"));

        // different values -> return false
        assertNotEquals(tempBill, new Bill("10"));
    }
}

