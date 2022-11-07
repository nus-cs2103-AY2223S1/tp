package gim.logic.commands;

import static gim.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import gim.commons.core.GuiSettings;
import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.ReadOnlyUserPrefs;
import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import gim.model.exercise.Name;
import gim.testutil.ExerciseBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_exerciseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExerciseAdded modelStub = new ModelStubAcceptingExerciseAdded();
        Exercise validExercise = new ExerciseBuilder().build();

        CommandResult commandResult = new AddCommand(validExercise).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validExercise.getName().toString(), validExercise),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExercise), modelStub.exercisesAdded);
    }

    @Test
    public void equals() {
        Exercise alice = new ExerciseBuilder().withName("Alice").build();
        Exercise benchPress = new ExerciseBuilder().withName("Bench Press").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBenchPressCommand = new AddCommand(benchPress);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different exercise -> returns false
        assertFalse(addAliceCommand.equals(addBenchPressCommand));
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
     * A Model stub that contains a single exercise.
     */
    private class ModelStubWithExercise extends ModelStub {
        private final Exercise exercise;

        ModelStubWithExercise(Exercise exercise) {
            requireNonNull(exercise);
            this.exercise = exercise;
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            requireNonNull(exercise);
            return this.exercise.isSameExercise(exercise);
        }
    }

    /**
     * A Model stub that always accept the exercise being added.
     */
    private class ModelStubAcceptingExerciseAdded extends ModelStub {
        final ArrayList<Exercise> exercisesAdded = new ArrayList<>();

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
    }

}
