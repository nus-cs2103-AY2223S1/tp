package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.task.DistinctTaskList;
import seedu.address.model.task.Task;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TaskBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2040S;
import static seedu.address.testutil.TypicalModules.CS2100;

/**
 * Integration testing of DistinctModuleList with Module class and DistinctTaskList.
 */
public class DistinctModuleListTest {
    private final DistinctModuleList moduleList = new DistinctModuleList();

    @Test
    public void containsModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.containsModule(null));
    }

    @Test
    public void containsModule_moduleNotInList_returnsFalse() {
        assertFalse(moduleList.containsModule(CS2030));
    }

    @Test
    public void containsModule_moduleInList_returnsTrue() {
        moduleList.addModule(CS2030);
        assertTrue(moduleList.containsModule(CS2030));
    }

    @Test
    public void containsModule_moduleWithSameModuleCode_returnsTrue() {
        Module module = new ModuleBuilder().
                withModuleCode("CS2030").build();
        moduleList.addModule(module);
        assertTrue(moduleList.containsModule(CS2030));
    }

    @Test
    public void addModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.addModule(null));
    }

    @Test
    public void addModule_duplicateModuleAdded_throwsDuplicateModuleException() {
        moduleList.addModule(CS2030);
        assertThrows(DuplicateModuleException.class, () -> moduleList.addModule(CS2030));
    }

    @Test
    public void setModules_nullModules_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.setModules(null));
    }

    @Test
    public void setModules_newListIsSet_listInDistinctModuleListIsChanged() {
        moduleList.addModule(CS2030);
        List<Module> modules = List.of(CS2100, CS2040S);
        moduleList.setModules(modules);
        DistinctModuleList distinctModuleList = new DistinctModuleList();
        distinctModuleList.addModule(CS2100);
        distinctModuleList.addModule(CS2040S);
        assertEquals(moduleList, distinctModuleList);
    }

    @Test
    public void updateTotalNumberOfTasks_nullModule_throwsNullPointerException() {
        DistinctTaskList distinctTaskList = new DistinctTaskList();
        assertThrows(NullPointerException.class, () -> moduleList
                .updateTotalNumOfTasks(null, distinctTaskList));
    }

    @Test
    public void updateTotalNumberOfTasks_nullDistinctTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList
                .updateTotalNumOfTasks(CS2030, null));
    }

    @Test
    public void updateTotalNumberOfTasks_moduleNotInTask_throwsModuleNotFoundException() {
        DistinctTaskList distinctTaskList = new DistinctTaskList();
        Task addedTask = new TaskBuilder().withModule("CS2040S").build();
        distinctTaskList.addTask(addedTask);
        assertThrows(ModuleNotFoundException.class, () -> moduleList
                .updateTotalNumOfTasks(CS2030, distinctTaskList));
    }

    @Test
    public void updateNumOfCompletedTasks_nullModule_throwsNullPointerException() {
        DistinctTaskList distinctTaskList = new DistinctTaskList();
        assertThrows(NullPointerException.class, () -> moduleList
                .updateNumOfCompletedTasks(null, distinctTaskList));
    }

    @Test
    public void updateNumOfCompletedTasks_nullDistinctTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList
                .updateNumOfCompletedTasks(CS2030, null));
    }

    @Test
    public void updateNumOfCompletedTasks_moduleNotInTask_throwsModuleNotFoundException() {
        DistinctTaskList distinctTaskList = new DistinctTaskList();
        Task addedTask = new TaskBuilder().withModule("CS2040S").build();
        distinctTaskList.addTask(addedTask);
        assertThrows(ModuleNotFoundException.class, () -> moduleList
                .updateNumOfCompletedTasks(CS2030, distinctTaskList));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.remove(null));
    }

    @Test
    public void remove_moduleNotPresent_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> moduleList.remove(CS2040));
    }

    @Test
    public void remove_moduleRemovedFromSet_moduleNoLongerInSet() {
        moduleList.addModule(CS2040);
        DistinctModuleList distinctModuleList = new DistinctModuleList();
        moduleList.remove(CS2040);
        assertEquals(distinctModuleList, moduleList);
    }

    @Test
    public void replaceModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.replaceModule(null, CS2030));
    }

    @Test
    public void replaceModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.replaceModule(CS2030, null));
    }

    @Test
    public void replaceModule_moduleNotPresent_throwsModuleNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> moduleList.replaceModule(CS2030, CS2040));
    }

    @Test
    public void replaceModule_duplicateModule_throwsDuplicateModuleException() {
        moduleList.addModule(CS2030);
        moduleList.addModule(CS2040);
        assertThrows(DuplicateModuleException.class, () -> moduleList.replaceModule(CS2030, CS2040));
    }

    @Test
    public void testEquals() {
        moduleList.addModule(CS2040);
        moduleList.addModule(CS2030);
        DistinctModuleList distinctModuleList = new DistinctModuleList();
        distinctModuleList.addModule(CS2040);
        distinctModuleList.addModule(CS2030);

        //Same DistinctModuleList
        assertTrue(moduleList.equals(moduleList));

        //Different DistinctModuleList but with same modules
        assertTrue(moduleList.equals(distinctModuleList));

        //Different DistinctModuleList with different modules
        distinctModuleList.setModules(List.of(CS2100));
        assertFalse(moduleList.equals(distinctModuleList));

        //Different Object type
        assertFalse(moduleList.equals(12012));

        //Null
        assertFalse(moduleList.equals(null));
    }

}
