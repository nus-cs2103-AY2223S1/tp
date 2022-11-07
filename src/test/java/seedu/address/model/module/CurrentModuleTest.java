package seedu.address.model.module;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrentModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CurrentModule(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidCurrentModuleName = "";
        assertThrows(IllegalArgumentException.class, () -> new CurrentModule(invalidCurrentModuleName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> CurrentModule.isValidModuleName(null));
    }

}
