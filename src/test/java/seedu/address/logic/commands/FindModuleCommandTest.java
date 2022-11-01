package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2106;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCodeStartsWithKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindModuleCommand}.
 */
public class FindModuleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeStartsWithKeywordPredicate firstPredicate =
                new ModuleCodeStartsWithKeywordPredicate("first");
        ModuleCodeStartsWithKeywordPredicate secondPredicate =
                new ModuleCodeStartsWithKeywordPredicate("second");

        FindModuleCommand findModuleFirstCommand = new FindModuleCommand(firstPredicate);
        FindModuleCommand findModuleSecondCommand = new FindModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findModuleFirstCommand.equals(findModuleFirstCommand));

        // same keyword -> returns true
        FindModuleCommand findModuleFirstCommandCopy = new FindModuleCommand(firstPredicate);
        assertTrue(findModuleFirstCommand.equals(findModuleFirstCommandCopy));

        // different types -> returns false
        assertFalse(findModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findModuleFirstCommand.equals(null));

        // different keyword -> returns false
        assertFalse(findModuleFirstCommand.equals(findModuleSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 0);
        ModuleCodeStartsWithKeywordPredicate predicate = preparePredicate(" ");
        FindModuleCommand command = new FindModuleCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_validKeywords_modulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED_OVERVIEW, 2);
        ModuleCodeStartsWithKeywordPredicate predicate = preparePredicate("CS");
        FindModuleCommand command = new FindModuleCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2103T, CS2106), model.getFilteredModuleList());
    }

    @Test
    public void execute_notAtHomePage_throwsCommandException() {
        ModuleCodeStartsWithKeywordPredicate predicate = preparePredicate("CS");
        FindModuleCommand command = new FindModuleCommand(predicate);
        model.setHomeStatus(false);
        assertCommandFailure(command, model, MESSAGE_NOT_AT_HOMEPAGE);
    }

    /**
     * Parses {@code testInput} into a {@code ModuleCodeStartsWithKeywordPredicate}.
     */
    private ModuleCodeStartsWithKeywordPredicate preparePredicate(String testInput) {
        return new ModuleCodeStartsWithKeywordPredicate(testInput);
    }
}
