package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Money(null));
    }

    @Test
    public void constructor_invalidMoney_throwsIllegalArgumentException() {
        String invalidMoney = "";
        assertThrows(IllegalArgumentException.class, () -> new Money(invalidMoney));
    }

    @Test
    public void constructor_success() {
        Money validMoneyWithDollarSign = new Money("$2.5");
        Money validMoneyNoDollarSign = new Money("2.5");

        assertEquals(validMoneyNoDollarSign.getValue(), validMoneyWithDollarSign.getValue());
    }

    @Test
    public void isValidMoney() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Money.isValidMoney(null));

        // invalid phone numbers
        assertFalse(Money.isValidMoney("")); // empty string
        assertFalse(Money.isValidMoney(" ")); // spaces only
        assertFalse(Money.isValidMoney("       ")); // many white spaces
        assertFalse(Money.isValidMoney("$")); // dollar sign only
        assertFalse(Money.isValidMoney("-1.23")); // with negative sign in front
        assertFalse(Money.isValidMoney("0.")); // no numbers after decimal point
        assertFalse(Money.isValidMoney("0.12345")); // more than 2 numbers after decimal point
        assertFalse(Money.isValidMoney("phone")); // non-numeric
        assertFalse(Money.isValidMoney("20,1")); // decimal point is wrong character
        assertFalse(Money.isValidMoney("1$20c")); // characters within digits
        assertFalse(Money.isValidMoney("1234 5678")); // spaces within digits
        assertFalse(Money.isValidMoney("$$2.50")); // multiple leading dollar signs

        // valid phone numbers
        assertTrue(Money.isValidMoney("911")); // no decimal point
        assertTrue(Money.isValidMoney("0.1")); // 1 number after decimal point
        assertTrue(Money.isValidMoney("0.12")); // 2 numbers after decimal point
        assertTrue(Money.isValidMoney("$2.50")); // with leading dollar sign
        assertTrue(Money.isValidMoney("124293842033123")); // long numbers
    }
}
