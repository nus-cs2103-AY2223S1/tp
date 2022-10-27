package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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
    public void execute_validIndex_success() {
        Set<Module> expectedSet = new HashSet<>();
        String[] modStringArray = {"CS1101S", "CS1231S", "IS1103", "MA1521", "MA2001", "GEA1000", "ES2660", "ST2334",
                                   "CS2030S", "CS2100", "CS2101", "CS2106", "CS2109S", "CS3230"};
        for (String modString : modStringArray) {
            expectedSet.add(new Module(modString));
        }
        ModulesLeftCommand modulesLeftCommand = new ModulesLeftCommand(INDEX_FIRST_PERSON);

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
    public void equals() {
        ModulesLeftCommand coreModsLeftCommand = new ModulesLeftCommand(INDEX_FIRST_PERSON);
        ModulesLeftCommand algoModsLeftCommand = new ModulesLeftCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(coreModsLeftCommand.equals(coreModsLeftCommand));

        // same values -> returns true
        ModulesLeftCommand coreModsLeftCommandCopy = new ModulesLeftCommand(INDEX_FIRST_PERSON);
        assertTrue(coreModsLeftCommand.equals(coreModsLeftCommandCopy));

        // different types -> returns false
        assertFalse(coreModsLeftCommand.equals(1));

        // null -> returns false
        assertFalse(coreModsLeftCommand.equals(null));

        // different person -> returns false
        assertFalse(coreModsLeftCommand.equals(algoModsLeftCommand));
    }
}
