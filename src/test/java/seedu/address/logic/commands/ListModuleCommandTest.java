package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyModules;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListModuleCommand.
 */
public class ListModuleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithOnlyModules(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_moduleListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListModuleCommand(), model, ListModuleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_moduleListIsFiltered_showsEverything() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new ListModuleCommand(), model, ListModuleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_notAtHomePage_throwsCommandException() {
        model.setHomeStatus(false);
        assertCommandFailure(new ListModuleCommand(), model, MESSAGE_NOT_AT_HOMEPAGE);
    }
}
