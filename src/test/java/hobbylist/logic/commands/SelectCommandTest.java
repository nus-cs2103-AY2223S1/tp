package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandFailure;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.testutil.TypicalActivities;

public class SelectCommandTest {
    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void getCommandWord_success() {
        assertEquals(SelectCommand.getCommandWord(), "select");
    }

    @Test
    public void setCommandWord_success() {
        SelectCommand.setCommandWord("test");
        assertEquals(SelectCommand.getCommandWord(), "test");
        SelectCommand.setCommandWord("select");
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredActivityList().size() + 1);
        SelectCommand selectCommand = new SelectCommand(outOfBoundIndex);

        assertCommandFailure(selectCommand, model, Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_success() {
        SelectCommand command = new SelectCommand(Index.fromOneBased(1));
        String expectedMessage = String.format(SelectCommand.MESSAGE_SELECT_ACTIVITY_SUCCESS,
                model.getFilteredActivityList().get(0));
        expectedModel.selectActivity(model.getFilteredActivityList().get(0));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getSelectedActivity(), expectedModel.getSelectedActivity());
    }

    @Test
    public void equals() {
        SelectCommand firstCommand = new SelectCommand(Index.fromOneBased(1));
        SelectCommand secondCommand = new SelectCommand(Index.fromOneBased(2));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // wrong type -> returns false
        assertFalse(firstCommand.equals("test"));

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> return true
        SelectCommand copyFirstCommand = new SelectCommand(Index.fromOneBased(1));
        assertTrue(firstCommand.equals(copyFirstCommand));

        // different index -> return false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
