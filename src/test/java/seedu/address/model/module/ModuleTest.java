package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.module.Module.MESSAGE_NO_TASKS_FOR_MODULE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.ModuleBuilder;

/**
 * Integration testing of Module class with some unit testing.
 */
public class ModuleTest {

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_nullModuleCredit_throwsNullPointerException() {
        ModuleCode moduleCode = new ModuleCode("CS2100");
        ModuleName moduleName = new ModuleName("Computer Organisation");
        assertThrows(NullPointerException.class, () -> new Module(moduleCode, moduleName, null));
    }

    @Test
    public void constructor_nullModuleName_throwsNullPointerException() {
        ModuleCode moduleCode = new ModuleCode("CS2100");
        ModuleCredit moduleCredit = new ModuleCredit(4);
        assertThrows(NullPointerException.class, () -> new Module(moduleCode,
                null, moduleCredit));
    }

    @Test
    public void testIsSameModule() {
        Module module = new ModuleBuilder().withModuleCode("CS2100").build();
        Module sameModule = new ModuleBuilder(module).build();
        Module differentModule = new ModuleBuilder().withModuleCode("CS2102").build();

        //Checks if it is same module as itself
        assertTrue(module.isSameModule(module));

        //Checks if it is same module as another module with same module code
        assertTrue(module.isSameModule(sameModule));

        //Checks if the module is different from another module with different module code
        assertFalse(module.isSameModule(differentModule));

        //Checks if the module is different from null
        assertFalse(module.isSameModule(null));
    }

    @Test
    public void hasAllSameFields_identicalFields_success() {
        ModuleCode firstModuleCode = new ModuleCode("CS2100");
        ModuleCode firstModuleCodeCopy = new ModuleCode("CS2100");
        ModuleName firstModuleName = new ModuleName("Computer Organisation");
        ModuleName firstModuleNameCopy = new ModuleName("Computer Organisation");
        ModuleCredit firstModuleCredit = new ModuleCredit(4);
        ModuleCredit firstModuleCreditCopy = new ModuleCredit(4);

        //All fields are identical
        Module firstModule = new Module(firstModuleCode, firstModuleName, firstModuleCredit);
        Module firstModuleCopy = new Module(firstModuleCodeCopy, firstModuleNameCopy, firstModuleCreditCopy);

        //Checks if it is same as itself
        firstModule.hasAllSameFields(firstModule);

        //Checks if it is same as another module with a same fields
        firstModule.hasAllSameFields(firstModuleCopy);
    }

    @Test
    public void hasAllSameFields_differentFields_failure() {
        ModuleCode firstModuleCode = new ModuleCode("CS2100");
        ModuleName firstModuleName = new ModuleName("Computer Organisation");
        ModuleCredit firstModuleCredit = new ModuleCredit(4);
        ModuleCode secondModuleCode = new ModuleCode("CS2102");
        ModuleName secondModuleName = new ModuleName("Database");
        ModuleCredit secondModuleCredit = new ModuleCredit(5);

        //Missing fields
        Module firstModule = new Module(firstModuleCode, firstModuleName, firstModuleCredit);
        Module secondModule = new Module(secondModuleCode);

        //Compare with module with only module code
        assertFalse(secondModule.hasAllSameFields(firstModule));

        //Different Fields
        Module thirdModule = new Module(secondModuleCode, firstModuleName, firstModuleCredit);
        Module fourthModule = new Module(firstModuleCode, secondModuleName, firstModuleCredit);
        Module fifthModule = new Module(firstModuleCode, firstModuleName, secondModuleCredit);

        //Different module code
        assertFalse(firstModule.hasAllSameFields(thirdModule));

        //Different module name
        assertFalse(firstModule.hasAllSameFields(fourthModule));

        //Different module credit
        assertFalse(firstModule.hasAllSameFields(fifthModule));
    }

    @Test
    public void edit_nullEditModuleDescriptor_throwsNullPointerException() {
        Module module = new ModuleBuilder().withModuleCode("CS2100").build();
        assertThrows(NullPointerException.class, () -> module.edit(null));
    }

    @Test
    public void testGenerateProgressMessage() {
        ModuleCode moduleCode = new ModuleCode("CS2100");
        ModuleName moduleName = new ModuleName("Computer Organisation");
        ModuleCredit moduleCredit = new ModuleCredit(4);
        Module module = new Module(moduleCode, moduleName, moduleCredit);
        module = module.setTotalNumOfTasks(0);

        //Checks if progress message shows no task if there are no tasks
        assertEquals(MESSAGE_NO_TASKS_FOR_MODULE, module.generateProgressMessage());

        //Checks if progress message shows correct number of tasks completed
        module = module.setTotalNumOfTasks(5);
        module = module.setNumOfCompletedTasks(2);

        assertEquals("2 / 5 task(s) completed", module.generateProgressMessage());
    }

    @Test
    public void testGetPercentageCompleted() {
        ModuleCode moduleCode = new ModuleCode("CS2105");
        ModuleName moduleName = new ModuleName("Networking");
        ModuleCredit moduleCredit = new ModuleCredit(4);
        Module module = new Module(moduleCode, moduleName, moduleCredit);
        module = module.setTotalNumOfTasks(5).setNumOfCompletedTasks(2);

        //Checks if percentage completed is correct
        assertEquals(0.4, module.getPercentageCompleted());
    }

    @Test
    public void testHasTasks() {
        ModuleCode moduleCode = new ModuleCode("CS2103");
        ModuleName moduleName = new ModuleName("Software engineering");
        ModuleCredit moduleCredit = new ModuleCredit(6);

        Module module = new Module(moduleCode, moduleName, moduleCredit);
        module = module.setTotalNumOfTasks(0);

        //Module has no tasks
        assertFalse(module.hasTasks());

        //Module has tasks
        module = module.setTotalNumOfTasks(5);
        assertTrue(module.hasTasks());
    }

    @Test
    public void testToString() {
        Module module = new ModuleBuilder().withModuleCode("CS2103T").build();
        assertEquals("CS2103T", module.toString());
    }

}
