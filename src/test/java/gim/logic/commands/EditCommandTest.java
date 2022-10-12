package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_DATE;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.VALID_WEIGHT_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.assertCommandFailure;
import static gim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static gim.logic.commands.CommandTestUtil.showExerciseAtIndex;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;
import static gim.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static gim.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import gim.commons.core.Messages;
import gim.commons.core.index.Index;
import gim.logic.commands.EditCommand.EditExerciseDescriptor;
import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.exercise.Exercise;
import gim.testutil.EditExerciseDescriptorBuilder;
import gim.testutil.ExerciseBuilder;




/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Exercise editedExercise = new ExerciseBuilder().build();
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(editedExercise).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXERCISE, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new ExerciseTracker(model.getExerciseTracker()), new UserPrefs());
        expectedModel.setExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExercise = Index.fromOneBased(model.getFilteredExerciseList().size());
        Exercise lastExercise = model.getFilteredExerciseList().get(indexLastExercise.getZeroBased());

        ExerciseBuilder exerciseInList = new ExerciseBuilder(lastExercise);

        Exercise editedExercise = exerciseInList.withName(VALID_NAME_BENCH_PRESS).withWeight(VALID_WEIGHT_BENCH_PRESS)
                .withDate(VALID_DATE).build();

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withName(VALID_NAME_BENCH_PRESS)
                .withWeight(VALID_WEIGHT_BENCH_PRESS).withDates(VALID_DATE).build();
        EditCommand editCommand = new EditCommand(indexLastExercise, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new ExerciseTracker(model.getExerciseTracker()), new UserPrefs());
        expectedModel.setExercise(lastExercise, editedExercise);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXERCISE, new EditExerciseDescriptor());
        Exercise editedExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new ExerciseTracker(model.getExerciseTracker()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        Exercise exerciseInFilteredList = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        Exercise editedExercise = new ExerciseBuilder(exerciseInFilteredList).withName(VALID_NAME_BENCH_PRESS).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXERCISE,
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_BENCH_PRESS).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new ExerciseTracker(model.getExerciseTracker()), new UserPrefs());
        expectedModel.setExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    //    @Test
    //    public void execute_duplicateExerciseUnfilteredList_failure() {
    //        Exercise firstExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
    //        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(firstExercise).build();
    //        EditCommand editCommand = new EditCommand(INDEX_SECOND_EXERCISE, descriptor);
    //
    //        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EXERCISE);
    //    }

    //    @Test
    //    public void execute_duplicateExerciseFilteredList_failure() {
    //        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);
    //
    //        // edit exercise in filtered list into a duplicate in exercise tracker
    //        Exercise exerciseInList = model.getExerciseTracker().getExerciseList().get(
    //                INDEX_SECOND_EXERCISE.getZeroBased());
    //        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXERCISE,
    //                new EditExerciseDescriptorBuilder(exerciseInList).build());
    //
    //        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EXERCISE);
    //    }

    @Test
    public void execute_invalidExerciseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExerciseList().size() + 1);
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder()
                .withName(VALID_NAME_BENCH_PRESS).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of exercise tracker
     */
    @Test
    public void execute_invalidExerciseIndexFilteredList_failure() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);
        Index outOfBoundIndex = INDEX_SECOND_EXERCISE;
        // ensures that outOfBoundIndex is still in bounds of exercise tracker list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExerciseTracker().getExerciseList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_BENCH_PRESS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_EXERCISE, DESC_ARM_CURLS);

        // same values -> returns true
        EditExerciseDescriptor copyDescriptor = new EditExerciseDescriptor(DESC_ARM_CURLS);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_EXERCISE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_EXERCISE, DESC_ARM_CURLS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_EXERCISE, DESC_BENCH_PRESS)));
    }

}
