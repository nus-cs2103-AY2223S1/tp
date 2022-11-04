package gim.logic.commands;

import static gim.testutil.TypicalExercises.getSampleUnsortedExercisesExerciseTracker;
import static gim.testutil.TypicalExercises.getSortedDifferentExercisesDifferentDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getSortedDifferentExercisesSameDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getSortedSameExercisesDifferentDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static gim.testutil.TypicalExercises.getUnsortedDifferentExercisesDifferentDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getUnsortedDifferentExercisesSameDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getUnsortedSameExercisesDifferentDatesExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.exercise.NameContainsKeywordsPredicate;


public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @Test
    public void execute_sortedList_showsSameList() {
        model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
        CommandResult result = new SortCommand().execute(model);
        assertEquals(new CommandResult(SortCommand.MESSAGE_SUCCESS), result);
        assertEquals(model.getFilteredExerciseList(), expectedModel.getFilteredExerciseList());
    }

    @Test
    public void execute_sameExercisesDifferentDates_showsSameList() {
        model = new ModelManager(getUnsortedSameExercisesDifferentDatesExerciseTracker(), new UserPrefs());
        expectedModel = new ModelManager(getSortedSameExercisesDifferentDatesExerciseTracker(), new UserPrefs());
        CommandResult result = new SortCommand().execute(model);
        assertEquals(new CommandResult(SortCommand.MESSAGE_SUCCESS), result);
        assertEquals(model.getFilteredExerciseList(), expectedModel.getFilteredExerciseList());
    }

    @Test
    public void execute_differentExercisesSameDates_showsSameList() {
        model = new ModelManager(getUnsortedDifferentExercisesSameDatesExerciseTracker(), new UserPrefs());
        expectedModel = new ModelManager(getSortedDifferentExercisesSameDatesExerciseTracker(), new UserPrefs());
        CommandResult result = new SortCommand().execute(model);
        assertEquals(new CommandResult(SortCommand.MESSAGE_SUCCESS), result);
        assertEquals(model.getFilteredExerciseList(), expectedModel.getFilteredExerciseList());
    }

    @Test
    public void execute_differentExercisesDifferentDates_showsSameList() {
        model = new ModelManager(getUnsortedDifferentExercisesDifferentDatesExerciseTracker(), new UserPrefs());
        expectedModel = new ModelManager(getSortedDifferentExercisesDifferentDatesExerciseTracker(), new UserPrefs());
        CommandResult result = new SortCommand().execute(model);
        assertEquals(new CommandResult(SortCommand.MESSAGE_SUCCESS), result);
        assertEquals(model.getFilteredExerciseList(), expectedModel.getFilteredExerciseList());
    }

    @Test
    public void execute_sortExercisesAfterFilterCommand_showsSameList() {
        model = new ModelManager(getSampleUnsortedExercisesExerciseTracker(), new UserPrefs());
        expectedModel = new ModelManager(getSortedSameExercisesDifferentDatesExerciseTracker(), new UserPrefs());
        NameContainsKeywordsPredicate predicate = preparePredicate("Arm Curls");
        model.filterFilteredExerciseList(predicate);
        CommandResult result = new SortCommand().execute(model);
        assertEquals(new CommandResult(SortCommand.MESSAGE_SUCCESS), result);
        assertEquals(model.getFilteredExerciseList(), expectedModel.getFilteredExerciseList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

