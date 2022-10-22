package gim.logic.commands;

import static gim.testutil.TypicalExercises.getSortedDifferentExercisesDifferentDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getSortedDifferentExercisesSameDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getSortedSameExercisesDifferentDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getUnsortedDifferentExercisesDifferentDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getUnsortedDifferentExercisesSameDatesExerciseTracker;
import static gim.testutil.TypicalExercises.getUnsortedSameExercisesDifferentDatesExerciseTracker;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;

public class SortCommandTest {
    private Model model;
    private Model expectedModel;

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

}

