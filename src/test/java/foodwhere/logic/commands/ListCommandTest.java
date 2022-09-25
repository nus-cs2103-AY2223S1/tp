package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static foodwhere.logic.commands.CommandTestUtil.showPersonAtIndex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
