package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_LISTED;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCodeMatchesKeywordPredicate;
import seedu.address.model.person.PersonIsInModulePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GoToCommand}.
 */
public class GoToCommandTest {
    private static final String MODULE_CODE_STR_CS2106 = CS2106.getModuleCodeAsUpperCaseString();
    private static final String MODULE_CODE_STR_MA2001 = MA2001.getModuleCodeAsUpperCaseString();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeMatchesKeywordPredicate firstPredicate =
                new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_CS2106);
        ModuleCodeMatchesKeywordPredicate secondPredicate =
                new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_MA2001);
        ModuleCode firstModuleCode = new ModuleCode(MODULE_CODE_STR_CS2106);
        ModuleCode secondModuleCode = new ModuleCode(MODULE_CODE_STR_CS2106);

        GoToCommand gotoFirstCommand = new GoToCommand(firstPredicate, firstModuleCode);
        GoToCommand gotoSecondCommand = new GoToCommand(secondPredicate, secondModuleCode);

        // same object -> returns true
        assertTrue(gotoFirstCommand.equals(gotoFirstCommand));

        // same values -> returns true
        GoToCommand gotoFirstCommandCopy = new GoToCommand(firstPredicate, firstModuleCode);
        assertTrue(gotoFirstCommand.equals(gotoFirstCommandCopy));

        // different types -> returns false
        assertFalse(gotoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(gotoFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(gotoFirstCommand.equals(gotoSecondCommand));
    }

    @Test
    public void execute_nonMatchingKeywords_noModuleFound() {
        String expectedMessage = MESSAGE_NO_SUCH_MODULE;
        ModuleCodeMatchesKeywordPredicate predicate =
                new ModuleCodeMatchesKeywordPredicate(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK);
        ModuleCode moduleCode = new ModuleCode(VALID_CS9999_MODULE_CODE_NOT_IN_TYPICAL_ADDRESS_BOOK);
        GoToCommand command = new GoToCommand(predicate, moduleCode);

        assertThrows(CommandException.class,
                expectedMessage, ()
                        -> command.execute(model));
    }

    @Test
    public void execute_matchingKeywords_moduleFound() {
        String expectedMessage = MESSAGE_MODULE_LISTED;

        // Matching keyword test
        ModuleCodeMatchesKeywordPredicate predicateToFilterModule =
                new ModuleCodeMatchesKeywordPredicate(MODULE_CODE_STR_CS2106);
        PersonIsInModulePredicate predicateToFilterPerson = new PersonIsInModulePredicate(CS2106);
        ModuleCode moduleCode = new ModuleCode(MODULE_CODE_STR_CS2106);
        GoToCommand command = new GoToCommand(predicateToFilterModule, moduleCode);

        expectedModel.updateFilteredModuleList(predicateToFilterModule);
        expectedModel.updateFilteredPersonList(predicateToFilterPerson);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2106), model.getFilteredModuleList());

        // To be updated when moduleBuilder has in-built list of persons to
        // assertEquals(Arrays.asList(...), model.getFilteredPersonList())
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_caseInsensitiveKeywords_moduleFound() {
        String expectedMessage = MESSAGE_MODULE_LISTED;

        // Case-insensitive keyword test
        ModuleCodeMatchesKeywordPredicate predicateToFilterModule = new ModuleCodeMatchesKeywordPredicate("cS2106");
        PersonIsInModulePredicate predicateToFilterPerson = new PersonIsInModulePredicate(CS2106);
        ModuleCode moduleCode = new ModuleCode(MODULE_CODE_STR_CS2106);
        GoToCommand command = new GoToCommand(predicateToFilterModule, moduleCode);

        expectedModel.updateFilteredModuleList(predicateToFilterModule);
        expectedModel.updateFilteredPersonList(predicateToFilterPerson);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2106), model.getFilteredModuleList());
        // To be updated when moduleBuilder has in-built list of persons to
        // assertEquals(Arrays.asList(...), model.getFilteredPersonList())
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
