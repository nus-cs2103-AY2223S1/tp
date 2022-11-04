//package seedu.address.logic.commands;
//
//import org.junit.jupiter.api.Test;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;
//
//import java.util.Arrays;
//import java.util.Collections;
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;


public class FindModulesCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void equals() {
        ModuleCodeContainsKeywordsPredicate firstPredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleCodeContainsKeywordsPredicate secondPredicate =
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList("second"));

        FindModulesCommand findModulesCommand = new FindModulesCommand(firstPredicate);
        FindModulesCommand findModulesSecondCommand = new FindModulesCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findModulesCommand.equals(findModulesCommand));

        // same values -> returns true
        FindModulesCommand findModulesFirstCommandCopy = new FindModulesCommand(firstPredicate);
        assertTrue(findModulesCommand.equals(findModulesFirstCommandCopy));

        // different types -> returns false
        assertFalse(findModulesCommand.equals(1));

        // null -> returns false
        assertFalse(findModulesCommand.equals(null));

        assertFalse(findModulesCommand.equals(findModulesSecondCommand));
    }

    @Test
    public void executeFullWord_moduleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 1);

        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("CS2030S");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executePartialStartingWord_modulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 4);
        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("CS20");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executePartialMiddleWord_modulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("210 ");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeModuleWithKeywordMixCase_modulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("cS203");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeModulesWithAllUpperCase_moduleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 1);
        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("ma1521");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void executeModulesWithAllUpperCaseWithKeywordMixCase_moduleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 1);
        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("Ma1521");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeNoMatchingModule_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        ModuleCodeContainsKeywordsPredicate predicate = preparePredicate("ZX2022");
        FindModulesCommand command = new FindModulesCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    /**
     * Parses {@code userInput} into a {@code ModuleCodeContainsKeywordsPredicate}.
     */
    private ModuleCodeContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ModuleCodeContainsKeywordsPredicate(Arrays.asList(userInput.trim().toLowerCase()));
    }
}
