package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2040;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_CODE = "cs1";
    private static final String INVALID_MODULE_NAME = "";
    private static final String INVALID_MODULE_CREDIT = "-1";

    private static final String VALID_MODULE_CODE = CS2040.getModuleCode().toString();
    private static final String VALID_MODULE_NAME = CS2040.getModuleName().toString();
    private static final String VALID_MODULE_CREDIT = CS2040.getModuleCredit().toString();

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws IllegalValueException {
        JsonAdaptedModule mod = new JsonAdaptedModule(CS2040);
        assertEquals(CS2040, mod.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule mod = new JsonAdaptedModule(INVALID_MODULE_CODE,
                VALID_MODULE_NAME, VALID_MODULE_CREDIT);
        String expectedMessage = ModuleCode.MODULE_CODE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, mod::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule mod = new JsonAdaptedModule(null,
                VALID_MODULE_NAME, VALID_MODULE_CREDIT);
        String expectedMessage = JsonAdaptedModule.MISSING_MODULE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, mod::toModelType);
    }

    @Test
    public void toModelType_invalidModuleDescription_throwsIllegalValueException() {
        JsonAdaptedModule mod = new JsonAdaptedModule(VALID_MODULE_CODE,
                INVALID_MODULE_NAME, VALID_MODULE_CREDIT);
        String expectedMessage = ModuleName.MODULE_NAME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, mod::toModelType);
    }

    @Test
    public void toModelType_nullModuleDescription_throwsIllegalValueException() {
        JsonAdaptedModule mod = new JsonAdaptedModule(VALID_MODULE_CODE,
                null, VALID_MODULE_CREDIT);
        String expectedMessage = JsonAdaptedModule.MISSING_MODULE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, mod::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCredit_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE,
                VALID_MODULE_NAME, INVALID_MODULE_CREDIT);
        String expectedMessage = ModuleCredit.MODULE_CREDIT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleCredit_throwsIllegalValueException() {
        JsonAdaptedModule mod = new JsonAdaptedModule(VALID_MODULE_CODE,
                VALID_MODULE_NAME, null);
        String expectedMessage = JsonAdaptedModule.MISSING_MODULE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, mod::toModelType);
    }

}
