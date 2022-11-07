package gim.logic.commands;

import static gim.commons.core.Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW;
import static gim.commons.core.Messages.MESSAGE_FIND_EMPTY_EXERCISES_LIST;
import static gim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static gim.testutil.TypicalExercises.CALF_RAISES;
import static gim.testutil.TypicalExercises.ELEVATED_SQUATS;
import static gim.testutil.TypicalExercises.FRONT_SQUATS;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.exercise.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different exercise -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExerciseFound() {
        String expectedMessage = String.format(MESSAGE_FIND_EMPTY_EXERCISES_LIST, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.filterFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExerciseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExercisesFound() {
        String expectedMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("CALF ELEVATED FRONT");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.filterFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CALF_RAISES, ELEVATED_SQUATS, FRONT_SQUATS), model.getFilteredExerciseList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
