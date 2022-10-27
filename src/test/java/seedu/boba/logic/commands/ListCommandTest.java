package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.boba.model.Model;
import seedu.boba.model.ModelManager;
import seedu.boba.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBobaBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getBobaBot(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
