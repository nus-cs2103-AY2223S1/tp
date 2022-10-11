package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_LISTED;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;
import static seedu.address.testutil.TypicalModules.getTypicalAddressBook;

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
    private static final String MODULECODESTR_CS2106 = CS2106.getModuleCodeAsUpperCaseString();
    private static final String MODULECODESTR_MA2001 = MA2001.getModuleCodeAsUpperCaseString();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeMatchesKeywordPredicate firstPredicate =
                new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_CS2106);
        ModuleCodeMatchesKeywordPredicate secondPredicate =
                new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_MA2001);

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
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = MESSAGE_NO_SUCH_MODULE;
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(" ");
        GoToCommand command = new GoToCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_validKeywords_moduleFound() {
        String expectedMessage = MESSAGE_MODULE_LISTED;
        ModuleCodeMatchesKeywordPredicate predicate = new ModuleCodeMatchesKeywordPredicate(MODULECODESTR_CS2106);
        GoToCommand command = new GoToCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2106), model.getFilteredModuleList());
    }
}
