package friday.model.student;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MasteryCheckTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MasteryCheck(null));
    }

    @Test
    public void isValidMasteryCheck() {

        // valid dates
        assertTrue(MasteryCheck.isValidMasteryCheck("2019-06-07"));
    }
}
