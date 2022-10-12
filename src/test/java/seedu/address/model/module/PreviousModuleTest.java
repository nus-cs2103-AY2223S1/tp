package seedu.address.model.module;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PreviousModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PreviousModule(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidPreviousModuleName = "";
        assertThrows(IllegalArgumentException.class, () -> new PreviousModule(invalidPreviousModuleName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> CurrentModule.isValidModuleName(null));
    }

}
