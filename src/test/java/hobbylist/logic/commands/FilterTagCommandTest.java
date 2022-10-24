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
import hobbylist.model.activity.TagMatchesKeywordPredicate;
import hobbylist.testutil.TypicalActivities;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagCommand}.
 */
public class FilterTagCommandTest {
    private Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void equals() {
        TagMatchesKeywordPredicate firstPredicate =
                new TagMatchesKeywordPredicate(Collections.singletonList("first"));
        TagMatchesKeywordPredicate secondPredicate =
                new TagMatchesKeywordPredicate(Collections.singletonList("second"));

        FilterTagCommand findTagFirstCommand = new FilterTagCommand(firstPredicate);
        FilterTagCommand findTagSecondCommand = new FilterTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findTagFirstCommand.equals(findTagFirstCommand));

        // same values -> returns true
        FilterTagCommand findFirstCommandCopy = new FilterTagCommand(firstPredicate);
        assertTrue(findTagFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findTagFirstCommand.equals(null));

        // different activity -> returns false
        assertFalse(findTagFirstCommand.equals(findTagSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noActivityFound() {
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, 0);
        TagMatchesKeywordPredicate predicate = preparePredicate(" ");
        FilterTagCommand command = new FilterTagCommand(predicate);
        expectedModel.updateFilteredActivityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredActivityList());
    }

    /**
     * Parses {@code userInput} into a {@code TagMatchesKeywordPredicate}.
     */
    private TagMatchesKeywordPredicate preparePredicate(String userInput) {
        return new TagMatchesKeywordPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
