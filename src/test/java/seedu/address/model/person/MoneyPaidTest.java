package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class MoneyPaidTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MoneyPaid((null)));
    }

    @Test
    public void constructor_invalidMoneyOwed_throwsIllegalArgumentException() {
        Integer invalidMoneyPaid = -1;
        assertThrows(IllegalArgumentException.class, () -> new MoneyPaid(invalidMoneyPaid));
    }

    @Test
    public void isValidAddress() {
        // null amount of money paid
        assertThrows(NullPointerException.class, () -> MoneyPaid.isValidMoneyPaid(null));

        // invalid amount of money paid
        assertFalse(MoneyPaid.isValidMoneyPaid(-1));

        // valid amount of money paid
        assertTrue(MoneyPaid.isValidMoneyPaid(1)); // single digit positive number
        assertTrue(MoneyPaid.isValidMoneyPaid(1234)); // multiple digits positive number
        assertTrue(MoneyPaid.isValidMoneyPaid(0)); // zero
    }
}
