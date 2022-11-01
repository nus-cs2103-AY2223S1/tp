package longtimenosee.model.person;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RiskAppetiteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RiskAppetite(null));
    }


    @Test
    public void isValidRiskAppetite() {
        // EP: null
        assertThrows(NullPointerException.class, () -> RiskAppetite.isValidFormat(null));

        //invalid risk appetites
        assertFalse(RiskAppetite.isValidFormat("")); //Empty string
        assertFalse(RiskAppetite.isValidFormat("  ")); //Spaces
        assertFalse(RiskAppetite.isValidFormat("0")); //Integer values
        assertFalse(RiskAppetite.isValidFormat("0.00")); //Decimal values
        assertFalse(RiskAppetite.isValidFormat("A")); //Uppercase invalid characters
        assertFalse(RiskAppetite.isValidFormat("l")); //Lowercase valid characters
        assertFalse(RiskAppetite.isValidFormat("High")); //Valid first character, invalid padding

        //Valid risk appetites
        assertTrue(RiskAppetite.isValidFormat("H")); //High
        assertTrue(RiskAppetite.isValidFormat("M")); //Medium
        assertTrue(RiskAppetite.isValidFormat("L")); //Low

    }
}

