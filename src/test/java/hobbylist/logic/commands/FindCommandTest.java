package hobbylist.logic.commands;

import static hobbylist.commons.core.Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.DateMatchesGivenDatePredicate;
import hobbylist.model.activity.NameOrDescContainsKeywordsPredicate;
import hobbylist.model.activity.RatingMatchesGivenValuePredicate;
import hobbylist.testutil.TypicalActivities;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());
    private final Model expectedModel = new ModelManager(TypicalActivities.getTypicalHobbyList(), new UserPrefs());

    @Test
    public void equals() {
        NameOrDescContainsKeywordsPredicate firstKeywordPredicate =
                new NameOrDescContainsKeywordsPredicate(Collections.singletonList("first"));
        DateMatchesGivenDatePredicate firstDatePredicate = new DateMatchesGivenDatePredicate("2022-01-01");
        RatingMatchesGivenValuePredicate firstRatingPredicate = new RatingMatchesGivenValuePredicate(3);
        NameOrDescContainsKeywordsPredicate secondKeywordPredicate =
                new NameOrDescContainsKeywordsPredicate(Collections.singletonList("second"));
        DateMatchesGivenDatePredicate secondDatePredicate = new DateMatchesGivenDatePredicate("2021-02-02");
        RatingMatchesGivenValuePredicate secondRatingPredicate = new RatingMatchesGivenValuePredicate(2);

        FindCommand findFirstCommand = new FindCommand(firstKeywordPredicate,
                                                        firstDatePredicate,
                                                        firstRatingPredicate);
        FindCommand findSecondCommand = new FindCommand(secondKeywordPredicate,
                                                        secondDatePredicate,
                                                        secondRatingPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstKeywordPredicate,
                                                            firstDatePredicate,
                                                            firstRatingPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different activity -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noActivityFound() {
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, 0);
        NameOrDescContainsKeywordsPredicate keywordPredicate = preparePredicate(" ");
        DateMatchesGivenDatePredicate datePredicate = new DateMatchesGivenDatePredicate("");
        RatingMatchesGivenValuePredicate ratingPredicate = new RatingMatchesGivenValuePredicate(-1);
        FindCommand command = new FindCommand(keywordPredicate, datePredicate, ratingPredicate);
        expectedModel.updateFilteredActivityList(keywordPredicate.or(datePredicate).or(ratingPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredActivityList());
    }

    @Test
    public void execute_multipleKeywords_multipleActivitiesFound() {
        String expectedMessage = String.format(MESSAGE_ACTIVITIES_LISTED_OVERVIEW, 3);
        NameOrDescContainsKeywordsPredicate keywordPredicate = preparePredicate("Charlotte Exercise Chicken");
        DateMatchesGivenDatePredicate datePredicate = new DateMatchesGivenDatePredicate("2022");
        RatingMatchesGivenValuePredicate ratingPredicate = new RatingMatchesGivenValuePredicate(1);
        FindCommand command = new FindCommand(keywordPredicate, datePredicate, ratingPredicate);
        expectedModel.updateFilteredActivityList(keywordPredicate.or(datePredicate).or(ratingPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalActivities.ACTIVITY_C, TypicalActivities.ACTIVITY_E,
                TypicalActivities.ACTIVITY_F), model.getFilteredActivityList());
    }

    @Test
    public void setCommandWord_validWord_success() {
        FindCommand.setCommandWord("test");
        assertEquals(FindCommand.getCommandWord(), "test");
        FindCommand.setCommandWord("find");
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameOrDescContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameOrDescContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
