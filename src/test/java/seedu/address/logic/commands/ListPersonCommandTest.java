package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookWithOnlyPersons;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPersonCommand.
 */
public class ListPersonCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithOnlyPersons(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_personListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPersonCommand(), model, ListPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_personListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListPersonCommand(), model, ListPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_notAtHomePage_throwsCommandException() {
        model.setHomeStatus(false);
        assertCommandFailure(new ListPersonCommand(), model, MESSAGE_NOT_AT_HOMEPAGE);
    }
}
