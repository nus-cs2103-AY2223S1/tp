package seedu.address.model.bill;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PaymentStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PaymentStatus(null));
    }

    @Test
    public void constructor_invalidPaid_throwsIllegalArgumentException() {
        String invalidPaid = "";
        assertThrows(IllegalArgumentException.class, () -> new PaymentStatus(invalidPaid));
    }

    @Test
    public void isValidPaid() {
        // null paid
        assertThrows(NullPointerException.class, () -> PaymentStatus.isValidPaymentStatus(null));

        // invalid paid
        assertFalse(PaymentStatus.isValidPaymentStatus("")); // empty string
        assertFalse(PaymentStatus.isValidPaymentStatus(" ")); // spaces only
        assertFalse(PaymentStatus.isValidPaymentStatus("-"));
        assertFalse(PaymentStatus.isValidPaymentStatus("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
        assertFalse(PaymentStatus.isValidPaymentStatus("10"));
        assertFalse(PaymentStatus.isValidPaymentStatus("$"));

        // valid paid
        assertTrue(PaymentStatus.isValidPaymentStatus("true"));
        assertTrue(PaymentStatus.isValidPaymentStatus("false"));
    }

    @Test
    public void equals() {
        PaymentStatus d1 = new PaymentStatus("true");
        PaymentStatus d2 = new PaymentStatus("false");
        PaymentStatus d3 = new PaymentStatus("true");
        assertTrue(d1.equals(d1));
        assertTrue(d1.equals(d3));
        assertTrue(d3.equals(d1));
        assertFalse(d2.equals(d1));
        assertFalse(d3.equals(null));
        assertFalse(d3.equals("true"));
    }

    @Test
    public void hashCodeTest() {
        PaymentStatus d1 = new PaymentStatus("true");
        PaymentStatus d2 = new PaymentStatus("false");
        PaymentStatus d3 = new PaymentStatus("true");
        assertTrue(d1.hashCode() == d1.hashCode());
        assertFalse(d1.hashCode() == d2.hashCode());
        assertTrue(d1.hashCode() == d3.hashCode());
    }
}
