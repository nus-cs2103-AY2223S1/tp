package gim.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import gim.commons.core.GuiSettings;
import gim.commons.core.Messages;
import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.ReadOnlyUserPrefs;
import gim.model.date.Date;
import gim.model.exercise.DateWithinRangePredicate;
import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import gim.model.exercise.Name;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

class RangeCommandTest {

    @Test
    public void execute_exerciseAcceptedByModel_rangeVariationOneSuccessful() throws Exception {
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        DateWithinRangePredicate validPredicate = new DateWithinRangePredicate(new Date("10/10/2022"),
                new Date("12/10/2022"));

        CommandResult commandResult = new RangeCommand(validPredicate).execute(modelStub);

        assertEquals(String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW,
                        modelStub.getFilteredExerciseList().size()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_exerciseAcceptedByModel_rangeVariationTwoRangeZeroSuccessful() throws Exception {
        // When rangeInDays is 0 (refer to RangeCommand::execute)
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        DateWithinRangePredicate validPredicate = new DateWithinRangePredicate(new Date("10/10/2022"),
                new Date("10/10/2022"));

        CommandResult commandResult = new RangeCommand(validPredicate, true).execute(modelStub);

        assertEquals(Messages.MESSAGE_RANGE_COMMAND_TWO_TODAY,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_exerciseAcceptedByModel_rangeVariationTwoRangeOneSuccessful() throws Exception {
        // When rangeInDays is 1 (refer to RangeCommand::execute)
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        DateWithinRangePredicate validPredicate = new DateWithinRangePredicate(new Date("10/10/2022"),
                new Date("11/10/2022"));

        CommandResult commandResult = new RangeCommand(validPredicate, true).execute(modelStub);

        assertEquals(Messages.MESSAGE_RANGE_COMMAND_TWO_YESTERDAY,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_exerciseAcceptedByModel_rangeVariationTwoRangeSevenSuccessful() throws Exception {
        // When rangeInDays is 7 (refer to RangeCommand::execute)
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        DateWithinRangePredicate validPredicate = new DateWithinRangePredicate(new Date("10/10/2022"),
                new Date("17/10/2022"));

        CommandResult commandResult = new RangeCommand(validPredicate, true).execute(modelStub);

        assertEquals(Messages.MESSAGE_RANGE_COMMAND_TWO_WEEK,
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_exerciseAcceptedByModel_rangeVariationTwoRangeOthersSuccessful() throws Exception {
        // When rangeInDays is greater than 1, e.g. 2 (refer to RangeCommand::execute)
        int rangeInDays = 2;
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        DateWithinRangePredicate validPredicate = new DateWithinRangePredicate(new Date("10/10/2022"),
                new Date("12/10/2022"));

        CommandResult commandResult = new RangeCommand(validPredicate, true).execute(modelStub);

        assertEquals(String.format(Messages.MESSAGE_RANGE_COMMAND_TWO, rangeInDays),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        String startDateStringOne = "10/10/2022";
        String endDateStringOne = "12/10/2022";
        String startDateStringTwo = "21/10/2022";
        String endDateStringTwo = "28/10/2022";

        DateWithinRangePredicate predicateOne =
                new DateWithinRangePredicate(new Date(startDateStringOne), new Date(endDateStringOne));
        DateWithinRangePredicate predicateTwo =
                new DateWithinRangePredicate(new Date(startDateStringTwo), new Date(endDateStringTwo));

        RangeCommand rangeCommandOne = new RangeCommand(predicateOne);
        RangeCommand rangeCommandTwo = new RangeCommand(predicateTwo);

        // same object -> returns true
        assertEquals(rangeCommandOne, rangeCommandOne);

        // same values -> returns true
        RangeCommand rangeCommandOneCopy = new RangeCommand(predicateOne);
        assertEquals(rangeCommandOne, rangeCommandOneCopy);

        // different types -> returns false
        assertNotEquals("STRING", rangeCommandOne);

        // null returns false
        assertNotEquals(rangeCommandOne, null);

        // different exercise returns false
        assertNotEquals(rangeCommandOne, rangeCommandTwo);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getExerciseTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExerciseTrackerFilePath(Path exerciseTrackerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Exercise getExercisePR(Name exerciseName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Exercise> getAllExercisePRs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Exercise addExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExerciseTracker(ReadOnlyExerciseTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExerciseTracker getExerciseTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExercise(Exercise target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExercise(Exercise target, Exercise editedExercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetDisplayedList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ExerciseHashMap getExerciseHashMap() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the exercise being added.
     */
    private class ModelStubAcceptingExerciseAdded extends ModelStub {
        final ArrayList<Exercise> exercisesAdded = new ArrayList<>();
        final ExerciseTracker exerciseTracker = new ExerciseTracker();
        final FilteredList<Exercise> filteredExercises = new FilteredList<>(this.exerciseTracker.getExerciseList());

        @Override
        public boolean hasExercise(Exercise exercise) {
            requireNonNull(exercise);
            return exercisesAdded.stream().anyMatch(exercise::isSameExercise);
        }

        @Override
        public Exercise addExercise(Exercise exercise) {
            requireNonNull(exercise);
            exercisesAdded.add(exercise);
            return exercise;
        }

        @Override
        public ReadOnlyExerciseTracker getExerciseTracker() {
            return new ExerciseTracker();
        }

        @Override
        public void sortFilteredExerciseList(Predicate<Exercise> predicate) {
            requireNonNull(predicate);
            exerciseTracker.sortDisplayedList();
            FilteredList<Exercise> filteredList = new FilteredList<>(this.exerciseTracker.getDuplicatedDisplayedList());
            filteredList.setPredicate(predicate);
            exerciseTracker.filterDisplayedList(filteredList);
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            return filteredExercises;
        }

    }
}
