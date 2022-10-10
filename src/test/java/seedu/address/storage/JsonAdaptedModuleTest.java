package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TITLE;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.task.TaskList;

public class JsonAdaptedModuleTest {
    private static final List<JsonAdaptedTask> VALID_LIST_OF_TASKS = getTypicalTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());
    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2103T);
        assertEquals(CS2103T, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_CODE, VALID_MODULE_TITLE,
                        VALID_LIST_OF_TASKS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null,
                VALID_MODULE_TITLE, VALID_LIST_OF_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE,
                null, VALID_LIST_OF_TASKS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ModuleTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleTasks_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE,
                VALID_MODULE_TITLE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TaskList.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

}
