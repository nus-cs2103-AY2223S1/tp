package seedu.taassist.model.moduleclass;

import static seedu.taassist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleClass(null));
    }

    @Test
    public void constructor_invalidModuleClassName_throwsIllegalArgumentException() {
        String invalidModuleClassName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleClass(invalidModuleClassName));
    }

    @Test
    public void isValidModuleClassName() {
        // null module class name
        assertThrows(NullPointerException.class, () -> ModuleClass.isValidModuleClassName(null));
    }

}
