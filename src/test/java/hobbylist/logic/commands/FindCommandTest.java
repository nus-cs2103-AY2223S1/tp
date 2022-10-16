package hobbylist.logic.commands;

import static hobbylist.commons.core.Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.testutil.TypicalActivities;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void equals() {
        NameOrDescContainsKeywordsPredicate firstPredicate =
                new NameOrDescContainsKeywordsPredicate(Collections.singletonList("first"));
        NameOrDescContainsKeywordsPredicate secondPredicate =
                new NameOrDescContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different activity -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noActivityFound() {
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, 0);
        NameOrDescContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredActivityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredActivityList());
    }

    @Test
    public void execute_multipleKeywords_multipleActivitiesFound() {
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, 3);
        NameOrDescContainsKeywordsPredicate predicate = preparePredicate("Charlotte Exercise Chicken");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredActivityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalActivities.ACTIVITY_C, TypicalActivities.ACTIVITY_E,
                TypicalActivities.ACTIVITY_F), model.getFilteredActivityList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameOrDescContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameOrDescContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
