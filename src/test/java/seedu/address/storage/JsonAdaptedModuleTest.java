package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TITLE;
import static seedu.address.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.getTypicalLinks;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.GE3238;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.TaskList;
import seedu.address.model.person.Person;

public class JsonAdaptedModuleTest {
    private static final List<JsonAdaptedTask> VALID_LIST_OF_TASKS = getTypicalTasks().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLink> VALID_LIST_OF_LINKS = getTypicalLinks().stream()
            .map(JsonAdaptedLink::new)
            .collect(Collectors.toList());
    private static final Set<JsonAdaptedPerson> VALID_SET_OF_PERSONS = getTypicalPersons().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toSet());
    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2103T);
        assertEquals(CS2103T, module.toModelType());
    }

    @Test
    public void toModelType_validModuleLinks_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(GE3238);
        assertEquals(GE3238, module.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_CODE, VALID_MODULE_TITLE,
                        VALID_LIST_OF_TASKS, VALID_LIST_OF_LINKS, VALID_SET_OF_PERSONS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_MODULE_TITLE,
                VALID_LIST_OF_TASKS, VALID_LIST_OF_LINKS, VALID_SET_OF_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE, null,
                VALID_LIST_OF_TASKS, VALID_LIST_OF_LINKS, VALID_SET_OF_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ModuleTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleTasks_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE,
                VALID_MODULE_TITLE, null, VALID_LIST_OF_LINKS, VALID_SET_OF_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TaskList.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleLinks_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE,
                VALID_MODULE_TITLE, VALID_LIST_OF_TASKS, null,
                VALID_SET_OF_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Link.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModulePersons_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_MODULE_CODE,
                VALID_MODULE_TITLE, VALID_LIST_OF_TASKS, VALID_LIST_OF_LINKS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Person.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}
