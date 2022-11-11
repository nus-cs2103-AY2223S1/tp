package longtimenosee.model.policy;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CoveragesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Coverage(null));
    }

    @Test
    public void constructor_invalidCoverageName_throwsIllegalArgumentException() {
        String invalidCoverageName = "";
        assertThrows(IllegalArgumentException.class, () -> new Coverage(invalidCoverageName));
    }

    @Test
    public void isValidCoverageName() {
        // EP: invalid Coverage name
        assertFalse(Coverage.isValidCoverageName("")); //Empty string
        assertFalse(Coverage.isValidCoverageName("abc")); //Non-existent coverage type

        //Valid coverage name
        assertTrue(Coverage.isValidCoverageName("LIFE"));
        assertTrue(Coverage.isValidCoverageName("MOTOR"));
        assertTrue(Coverage.isValidCoverageName("HEALTH"));
        assertTrue(Coverage.isValidCoverageName("TRAVEL"));
        assertTrue(Coverage.isValidCoverageName("PROPERTY"));
        assertTrue(Coverage.isValidCoverageName("MOBILE"));
        assertTrue(Coverage.isValidCoverageName("BITE"));
        assertTrue(Coverage.isValidCoverageName("INVESTMENT"));
    }

}
