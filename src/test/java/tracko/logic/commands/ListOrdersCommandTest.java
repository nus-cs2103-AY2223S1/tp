package tracko.logic.commands;

import static tracko.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tracko.logic.commands.CommandTestUtil.showPersonAtIndex;
import static tracko.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static tracko.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.ListOrdersCommand;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListOrdersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListOrdersCommand(), model, ListOrdersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListOrdersCommand(), model, ListOrdersCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
