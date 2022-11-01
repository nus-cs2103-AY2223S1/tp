package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.RatePredicate;
import hobbylist.testutil.TypicalActivities;

/**
 * Contains integration tests (interaction with the Model) for {@code RateAboveCommand}.
 */
public class RateAboveCommandTest {
    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void equals() {

        RateAboveCommand rateAboveFirstCommand = new RateAboveCommand(2);
        RateAboveCommand rateAboveSecondCommand = new RateAboveCommand(4);

        // same object -> returns true
        assertEquals(rateAboveFirstCommand, rateAboveFirstCommand);

        // same values -> returns true
        RateAboveCommand rateAboveFirstCommandCopy = new RateAboveCommand(2);
        assertEquals(rateAboveFirstCommand, rateAboveFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, rateAboveFirstCommand);

        // null -> returns false
        assertNotEquals(null, rateAboveFirstCommand);

        // different activity -> returns false
        assertNotEquals(rateAboveFirstCommand, rateAboveSecondCommand);
    }

    @Test
    public void execute_zero_allActivityFound() {
        String expectedMessage = RateAboveCommand.RESPOND_MESSAGE + "2" + ".";
        RatePredicate predicate = new RatePredicate(2);
        RateAboveCommand command = new RateAboveCommand(2);
        expectedModel.updateFilteredActivityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredActivityList());
    }

    @Test
    public void setCommandWord_validWord_success() {
        RateAboveCommand.setCommandWord("test");
        assertEquals(RateAboveCommand.getCommandWord(), "test");
        RateAboveCommand.setCommandWord("r/above");
    }
}
