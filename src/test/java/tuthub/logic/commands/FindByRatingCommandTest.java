package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.BENSON;
import static tuthub.testutil.TypicalTutors.CARL;
import static tuthub.testutil.TypicalTutors.DANIEL;
import static tuthub.testutil.TypicalTutors.FIONA;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.RatingContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByRatingCommand}.
 */
public class FindByRatingCommandTest {
    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void equals() {
        RatingContainsKeywordsPredicate firstPredicate =
                new RatingContainsKeywordsPredicate(Collections.singletonList("first"));
        RatingContainsKeywordsPredicate secondPredicate =
                new RatingContainsKeywordsPredicate(Collections.singletonList("second"));

        FindByRatingCommand findFirstCommand = new FindByRatingCommand(firstPredicate);
        FindByRatingCommand findSecondCommand = new FindByRatingCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByRatingCommand findFirstCommandCopy = new FindByRatingCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTutorFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 0);
        RatingContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindByRatingCommand command = new FindByRatingCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        RatingContainsKeywordsPredicate predicate = preparePredicate("4.5 4.9");
        FindByRatingCommand command = new FindByRatingCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL), model.getFilteredTutorList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 3);
        RatingContainsKeywordsPredicate predicate = preparePredicate("2. 1.");
        FindByRatingCommand command = new FindByRatingCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, FIONA), model.getFilteredTutorList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private RatingContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RatingContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
