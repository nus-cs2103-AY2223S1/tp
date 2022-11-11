package longtimenosee.model.policy;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommissionTest {

    //EP: null Commission
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Commission(null));
    }

    @Test
    public void constructor_invalidCommission_throwsIllegalArgumentException() {
        String invalidCommission = "";
        assertThrows(IllegalArgumentException.class, () -> new Commission(invalidCommission));
    }

    @Test
    public void commission_isValidCommission() {
        // invalid Commission
        assertFalse(Commission.isValidCommission("")); // empty string
        assertFalse(Commission.isValidCommission(" ")); // spaces only
        assertFalse(Commission.isValidCommission("one% two% 3%")); // non-numeric
        assertFalse(Commission.isValidCommission("12a% 2% 3%")); // alphabets within digits
        assertFalse(Commission.isValidCommission("1 2% 3% 4%")); // Commission values separated with whitespace
        assertFalse(Commission.isValidCommission("1.2.3% 3% 4%")); // not an appropriate decimal value
        assertFalse(Commission.isValidCommission("1.233343% 3% 4%")); // not 5 or fewer decimals

        // valid Commission
        assertTrue(Commission.isValidCommission("10% 4% 1%")); // small yearly Commission
        assertTrue(Commission.isValidCommission("10.0% 4.0% 1.0%")); //same yearly Commission with decimal
    }

    //EP: negative values
    @Test
    public void commission_isValidValue() {
        //valid values
        assertTrue(Commission.isValidCommission("100% 0% 0%")); //0% and 100% is a valid Commission for fee-based

        //invalid values
        assertFalse(Commission.isValidCommission("-0.0001% 0% 0%")); //Small negative values
        assertFalse(Commission.isValidCommission("-110191910290909900.0% 0% 0%")); //Large negative Values

    }
}

