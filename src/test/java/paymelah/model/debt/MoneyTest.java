package paymelah.model.debt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    public void isValidMoney() {
        // null money amount
        assertThrows(NullPointerException.class, () -> Money.isValidMoney(null));

        // invalid money amounts
        assertFalse(Money.isValidMoney("")); // empty string
        assertFalse(Money.isValidMoney(" ")); // spaces only
        assertFalse(Money.isValidMoney("       ")); // many white spaces
        assertFalse(Money.isValidMoney("$")); // dollar sign only
        assertFalse(Money.isValidMoney("+")); // plus sign only
        assertFalse(Money.isValidMoney("-1.23")); // with negative sign in front
        assertFalse(Money.isValidMoney("0.")); // no numbers after decimal point
        assertFalse(Money.isValidMoney("0.12345")); // more than 2 numbers after decimal point
        assertFalse(Money.isValidMoney("phone")); // non-numeric
        assertFalse(Money.isValidMoney("20,1")); // decimal point is wrong character
        assertFalse(Money.isValidMoney("1$20c")); // characters within digits
        assertFalse(Money.isValidMoney("1234 5678")); // spaces within digits
        assertFalse(Money.isValidMoney("$$2.50")); // multiple leading dollar signs
        assertFalse(Money.isValidMoney("1+++")); // with >2 '+'

        // valid money amounts
        assertTrue(Money.isValidMoney("911")); // no decimal point
        assertTrue(Money.isValidMoney("0.1")); // 1 number after decimal point
        assertTrue(Money.isValidMoney("0.12")); // 2 numbers after decimal point
        assertTrue(Money.isValidMoney("$2.50")); // with leading dollar sign
        assertTrue(Money.isValidMoney("124293842033123")); // long numbers
        assertTrue(Money.isValidMoney("1+")); // with 1 '+'
        assertTrue(Money.isValidMoney("$1++")); // with 2 '+'
        assertTrue(Money.isValidMoney("1 ++")); // with space between number and plus sign
        assertTrue(Money.isValidMoney("1               ++")); // with many spaces between number and plus sign
    }

    @Test
    public void equals() {
        Money noDecimal = new Money("2");
        Money oneDecimal = new Money("2.0");
        Money twoDecimal = new Money("2.00");
        Money dollarSign = new Money("$2");
        Money differentValue = new Money("3");

        // same object -> returns true
        assertEquals(twoDecimal, twoDecimal);

        // same values -> returns true
        assertEquals(noDecimal, oneDecimal);
        assertEquals(oneDecimal, twoDecimal);
        assertEquals(noDecimal, dollarSign);

        // different values -> returns false
        assertNotEquals(noDecimal, differentValue);

        // null -> returns false
        assertNotEquals(null, noDecimal);

        Money withOnePlus = new Money("2.5+");
        Money withTwoPlus = new Money("2.5++");
        Money withSpaces = new Money("2.5        ++");
        Money expectedAfterGst = new Money("2.68");
        Money expectedAfterGstServiceCharge = new Money("2.95");

        // spaces are inconsequential
        assertEquals(withTwoPlus, withSpaces);

        // with 1 '+'
        assertEquals(expectedAfterGst, withOnePlus);

        //with 2 '+'
        assertEquals(expectedAfterGstServiceCharge, withTwoPlus);
    }
}
