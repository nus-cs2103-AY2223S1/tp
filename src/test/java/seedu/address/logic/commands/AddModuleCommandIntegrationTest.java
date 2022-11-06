package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;


public class AddModuleCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void initialize() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_addUniqueModule_success() {
        Module module = new ModuleBuilder().withModuleCode("CS9999").build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(module);
        String expectedMessage = AddModuleCommand.MODULE_ADDED_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addModule(module);
        assertCommandSuccess(addModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addSameModule_throwsCommandException() {
        model.addModule(new ModuleBuilder().withModuleCode("CS2111").build());
        Module module = new ModuleBuilder().withModuleCode("CS2111").build();
        //Checks that model has module
        assertTrue(model.hasModule(module));
        AddModuleCommand addModuleCommand = new AddModuleCommand(module);
        String expectedMessage = AddModuleCommand.DUPLICATE_MODULE_DETECTED;

        assertCommandFailure(addModuleCommand, model, expectedMessage);
    }

}
