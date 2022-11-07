package taskbook.logic.commands;

import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static taskbook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static taskbook.testutil.TypicalTaskBook.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskbook.logic.commands.contacts.ContactListCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ContactListCommand.
 */
public class ContactListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ContactListCommand(), model, ContactListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ContactListCommand(), model, ContactListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
