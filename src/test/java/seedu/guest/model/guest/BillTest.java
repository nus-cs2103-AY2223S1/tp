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
        assertFalse(Bill.isValidBill("abc")); // non-numerical input
        assertFalse(Bill.isValidBill("-1")); // negative number
        assertFalse(Bill.isValidBill(".99")); // start with decimal point
        assertFalse(Bill.isValidBill("1.")); // end in decimal point
        assertFalse(Bill.isValidBill("1.5")); // only 1 decimal place
        assertFalse(Bill.isValidBill("1.500")); // more than 2 decimal places
        assertFalse(Bill.isValidBill("1,000")); // comma


        // valid bill
        assertTrue(Bill.isValidBill("0")); // 0
        assertTrue(Bill.isValidBill("0.00")); // decimal places
        assertTrue(Bill.isValidBill("10")); // integer
        assertTrue(Bill.isValidBill("999999999999999.99")); // large numbers
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

