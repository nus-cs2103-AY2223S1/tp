package hobbylist.logic.commands;

import static hobbylist.commons.core.Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.StatusMatchesGivenStatus;
import hobbylist.testutil.TypicalActivities;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindStatusCommandTest {
    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void equals() {
        StatusMatchesGivenStatus firstPredicate =
                new StatusMatchesGivenStatus("ONGOING");
        StatusMatchesGivenStatus secondPredicate =
                new StatusMatchesGivenStatus("COMPLETED");

        FindStatusCommand findStatusFirstCommand = new FindStatusCommand(firstPredicate);
        FindStatusCommand findStatusSecondCommand = new FindStatusCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findStatusFirstCommand.equals(findStatusFirstCommand));

        // same values -> returns true
        FindStatusCommand findStatusFirstCommandCopy = new FindStatusCommand(firstPredicate);
        assertTrue(findStatusFirstCommand.equals(findStatusFirstCommandCopy));

        // different types -> returns false
        assertFalse(findStatusFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findStatusFirstCommand.equals(null));

        // different activity -> returns false
        assertFalse(findStatusFirstCommand.equals(findStatusSecondCommand));
    }

    @Test
    public void execute_success_noActivityFound() {
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, 0);
        StatusMatchesGivenStatus predicate = new StatusMatchesGivenStatus("UPCOMING");
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredActivityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredActivityList());
    }

    @Test
    public void setCommandWord_validWord_success() {
        FindCommand.setCommandWord("test");
        assertEquals(FindCommand.getCommandWord(), "test");
        FindCommand.setCommandWord("find");
    }
}
