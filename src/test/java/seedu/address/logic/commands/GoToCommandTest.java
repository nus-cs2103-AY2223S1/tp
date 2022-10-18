package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_LISTED;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalModules.CS2106;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GoToCommand}.
 */
public class GoToCommandTest {
    private static final String MODULE_CODE_MIX_CASE = "cS2106";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeMatchesKeywordPredicate firstPredicate =
                new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE);
        ModuleCodeMatchesKeywordPredicate secondPredicate =
                new ModuleCodeMatchesKeywordPredicate(VALID_MA2001_MODULE_CODE);

        GoToCommand gotoFirstCommand = new GoToCommand(firstPredicate);
        GoToCommand gotoSecondCommand = new GoToCommand(secondPredicate);

        // same object -> returns true
        assertTrue(gotoFirstCommand.equals(gotoFirstCommand));

        // same values -> returns true
        GoToCommand gotoFirstCommandCopy = new GoToCommand(firstPredicate);
        assertTrue(gotoFirstCommand.equals(gotoFirstCommandCopy));

        // different types -> returns false
        assertFalse(gotoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(gotoFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(gotoFirstCommand.equals(gotoSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = MESSAGE_NO_SUCH_MODULE;
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(" ");
        GoToCommand command = new GoToCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        expectedModel.setHomeStatus(false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_matchingKeywords_moduleFound() {
        String expectedMessage = MESSAGE_MODULE_LISTED;

        // Matching keyword test
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(VALID_CS2106_MODULE_CODE);
        GoToCommand command = new GoToCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        expectedModel.setHomeStatus(false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2106), model.getFilteredModuleList());
    }

    @Test
    public void execute_caseInsensitiveKeywords_moduleFound() {
        String expectedMessage = MESSAGE_MODULE_LISTED;

        // Case-insensitive keyword test
        ModuleCodeMatchesKeywordPredicate predicate =
                new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_MIX_CASE);
        GoToCommand command = new GoToCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        expectedModel.setHomeStatus(false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2106), model.getFilteredModuleList());
    }
}
