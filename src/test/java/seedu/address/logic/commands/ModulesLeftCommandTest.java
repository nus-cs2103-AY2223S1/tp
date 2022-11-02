package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ModulesLeftCommand}.
 */
public class ModulesLeftCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_coreModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS1101S", "CS1231S", "IS1103", "MA1521", "MA2001", "GEA1000", "ES2660", "ST2334",
                                   "CS2030S", "CS2100", "CS2101", "CS2106", "CS2109S", "CS3230"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(1);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_algoModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS3230", "CS3231", "CS3236", "CS4231", "CS4234"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(2);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_aiModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS2109S", "CS3243", "CS3244", "CS3263", "CS3264", "CS4243", "CS4244", "CS4246",
                                   "CS4248"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(3);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_gamesModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS3241", "CS3242", "CS3247", "CS4247", "CS4350"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(4);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_securityModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS2107", "CS3235", "CS4230", "CS4236", "CS4238", "CS4239"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(5);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dataModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS2102", "CS3223", "CS4221", "CS4224", "CS4225"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(6);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_infoModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS2108", "CS3245", "CS4242", "CS4248", "CS4347"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(7);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_networkModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS2105", "CS3103", "CS4222", "CS4226", "CS4231"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(8);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_parallelModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS3210", "CS3211", "CS4223", "CS4231"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(9);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_languagesModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS2104", "CS3211", "CS4212", "CS4215"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(10);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sweModules_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS3213", "CS3219", "CS4211", "CS4218", "CS4239"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        Index index = Index.fromOneBased(11);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        String expectedMessage = String.format(ModulesLeftCommand.MESSAGE_SUCCESS, expectedSet);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(modulesLeftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(12);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(outOfBoundIndex);

        String expectedMessage = ModulesLeftCommand.MESSAGE_INVALID_INDEX;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandFailure(modulesLeftCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptyUser_failure() {
        Model modelWithoutUser = model;
        modelWithoutUser.deleteUser();

        Index index = Index.fromOneBased(1);
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(index);

        assertCommandFailure(modulesLeftCommand, modelWithoutUser, ModulesLeftCommand.MESSAGE_NO_USER);
    }

    @Test
    public void equals() {
        Index indexOne = Index.fromOneBased(1);
        ModulesLeftCommand coreModsLeftCommand = new ModulesLeftCommand(indexOne);
        Index indexTwo = Index.fromOneBased(2);
        ModulesLeftCommand algoModsLeftCommand = new ModulesLeftCommand(indexTwo);

        // same object -> returns true
        assertTrue(coreModsLeftCommand.equals(coreModsLeftCommand));

        // same values -> returns true
        ModulesLeftCommand coreModsLeftCommandCopy = new ModulesLeftCommand(indexOne);
        assertTrue(coreModsLeftCommand.equals(coreModsLeftCommandCopy));

        // different types -> returns false
        assertFalse(coreModsLeftCommand.equals(1));

        // null -> returns false
        assertFalse(coreModsLeftCommand.equals(null));

        // different index -> returns false
        assertFalse(coreModsLeftCommand.equals(algoModsLeftCommand));
    }
}
