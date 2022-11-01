package longtimenosee.model.person;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IncomeTest {

    //EP: null income
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Income(null));
    }

    @Test
    public void constructor_invalidIncome_throwsIllegalArgumentException() {
        String invalidIncome = "";
        assertThrows(IllegalArgumentException.class, () -> new Income(invalidIncome));
    }

    @Test
    public void income_isValidFormat() {
        // invalid income
        assertFalse(Income.isValidFormat("")); // empty string
        assertFalse(Income.isValidFormat(" ")); // spaces only
        assertFalse(Income.isValidFormat("IEarnOneMillionBucks")); // non-numeric
        assertFalse(Income.isValidFormat("0neHundredD0llars")); // alphabets within digits
        assertFalse(Income.isValidFormat("600 000")); // income values seperated with whitespace
        assertFalse(Income.isValidFormat("100.0.000")); // not an appropriate decimal value

        // valid income
        assertTrue(Income.isValidFormat("100")); // small yearly income
        assertTrue(Income.isValidFormat("100.00")); //same yearly income with decimal
        assertTrue(Income.isValidFormat("10000000.00")); // longer income value
    }

    //EP: negative values
    @Test
    public void income_isValidValue() {
        //valid values
        assertTrue(Income.isPositiveIncome("0")); //0 is a valid income, possibly for unemployed clients

        //invalid values
        assertFalse(Income.isPositiveIncome("-0.0001")); //Small negative values
        assertFalse(Income.isPositiveIncome("-110191910290909900.00")); //Large negative Values

    }
}

