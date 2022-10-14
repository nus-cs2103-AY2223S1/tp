package coydir.logic.commands;

import static coydir.logic.commands.CommandTestUtil.assertCommandSuccess;
import static coydir.logic.commands.CommandTestUtil.showPersonAtIndex;
import static coydir.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static coydir.testutil.TypicalPersons.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
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
