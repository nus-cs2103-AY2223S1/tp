package seedu.realtime.logic.commands;

import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.realtime.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.realtime.testutil.TypicalClients.getTypicalRealTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListClientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRealTime(), new UserPrefs());
        expectedModel = new ModelManager(model.getRealTime(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
