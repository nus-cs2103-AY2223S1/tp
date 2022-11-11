package longtimenosee.model.policy;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PremiumTest {

    //EP: null premium
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Premium(null));
    }

    @Test
    public void constructor_invalidPremium_throwsIllegalArgumentException() {
        String invalidPremium = "";
        assertThrows(IllegalArgumentException.class, () -> new Premium(invalidPremium));
    }

    @Test
    public void premium_isValidPremium() {
        // invalid Premium
        assertFalse(Premium.isValidPremium("")); // empty string
        assertFalse(Premium.isValidPremium(" ")); // spaces only
        assertFalse(Premium.isValidPremium("IEarnOneMillionBucks")); // non-numeric
        assertFalse(Premium.isValidPremium("0neHundredD0llars")); // alphabets within digits
        assertFalse(Premium.isValidPremium("600 000")); // Premium values seperated with whitespace
        assertFalse(Premium.isValidPremium("100.0.000")); // not an appropriate decimal value

        // valid Premium
        assertTrue(Premium.isValidPremium("100")); // small yearly Premium
        assertTrue(Premium.isValidPremium("100.00")); //same yearly Premium with decimal
        assertTrue(Premium.isValidPremium("100000000.00")); // longer Premium value
    }

    @Test
    public void premium_isValidValue() {
        //valid values
        assertTrue(Premium.isValidPremium("0")); //0 is a valid Premium, possibly for unemployed clients

        //EP: negative values
        //invalid values
        assertFalse(Premium.isValidPremium("-0.0001")); //Small negative values
        assertFalse(Premium.isValidPremium("-110191910290909900.00")); //Large negative Values

        //EP: values too big
        assertFalse(Premium.isValidPremium("100000000000"));
    }
}

