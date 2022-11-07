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
        assertTrue(PaymentStatus.isValidPaymentStatus("paid"));
        assertTrue(PaymentStatus.isValidPaymentStatus("PAID"));
        assertTrue(PaymentStatus.isValidPaymentStatus("PaiD"));
        assertTrue(PaymentStatus.isValidPaymentStatus("unpaid"));
        assertTrue(PaymentStatus.isValidPaymentStatus("UNPAID"));
    }

    @Test
    public void equals() {
        PaymentStatus p1 = new PaymentStatus("paid");
        PaymentStatus p2 = new PaymentStatus("unpaid");
        PaymentStatus p3 = new PaymentStatus("PAID");
        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(p3));
        assertTrue(p3.equals(p1));
        assertFalse(p2.equals(p1));
        assertFalse(p3.equals(null));
        assertFalse(p3.equals("PAID"));
    }

    @Test
    public void hashCodeTest() {
        PaymentStatus p1 = new PaymentStatus("paid");
        PaymentStatus p2 = new PaymentStatus("unpaid");
        PaymentStatus p3 = new PaymentStatus("PAID");
        assertTrue(p1.hashCode() == p1.hashCode());
        assertFalse(p1.hashCode() == p2.hashCode());
        assertTrue(p1.hashCode() == p3.hashCode());
    }
}
