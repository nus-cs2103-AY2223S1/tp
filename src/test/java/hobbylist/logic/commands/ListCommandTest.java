package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.testutil.TypicalActivities;
import hobbylist.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
        expectedModel = new ModelManager(model.getHobbyList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showActivityAtIndex(model, TypicalIndexes.INDEX_FIRST_ACTIVITY);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void setCommandWord_validWord_success() {
        ListCommand.setCommandWord("test");
        assertEquals(ListCommand.getCommandWord(), "test");
        ListCommand.setCommandWord("list");
    }
}
