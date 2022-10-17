package seedu.address.model.bill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PaidTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Paid(null));
    }

    @Test
    public void constructor_invalidPaid_throwsIllegalArgumentException() {
        String invalidPaid = "";
        assertThrows(IllegalArgumentException.class, () -> new Paid(invalidPaid));
    }

    @Test
    public void isValidPaid() {
        // null paid
        assertThrows(NullPointerException.class, () -> Paid.isValidIsPaid(null));

        // invalid paid
        assertFalse(Paid.isValidIsPaid("")); // empty string
        assertFalse(Paid.isValidIsPaid(" ")); // spaces only
        assertFalse(Paid.isValidIsPaid("-"));
        assertFalse(Paid.isValidIsPaid("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
        assertFalse(Paid.isValidIsPaid("10"));
        assertFalse(Paid.isValidIsPaid("$"));

        // valid paid
        assertTrue(Paid.isValidIsPaid("true"));
        assertTrue(Paid.isValidIsPaid("false"));
    }

    @Test
    public void equals() {
        Paid d1 = new Paid("true");
        Paid d2 = new Paid("false");
        Paid d3 = new Paid("true");
        assertTrue(d1.equals(d1));
        assertTrue(d1.equals(d3));
        assertTrue(d3.equals(d1));
        assertFalse(d2.equals(d1));
        assertFalse(d3.equals(null));
        assertFalse(d3.equals("true"));
    }

    @Test
    public void hashCodeTest() {
        Paid d1 = new Paid("true");
        Paid d2 = new Paid("false");
        Paid d3 = new Paid("true");
        assertTrue(d1.hashCode() == d1.hashCode());
        assertFalse(d1.hashCode() == d2.hashCode());
        assertTrue(d1.hashCode() == d3.hashCode());
    }
}
