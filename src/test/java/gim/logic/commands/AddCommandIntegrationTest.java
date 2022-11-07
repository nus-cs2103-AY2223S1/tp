package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.exercise.Exercise;
import gim.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
    }

    @Test
    public void execute_newExercise_success() {
        Exercise validExercise = new ExerciseBuilder().build();

        Model expectedModel = new ModelManager(model.getExerciseTracker(), new UserPrefs());
        Exercise added = expectedModel.addExercise(validExercise);

        assertCommandSuccess(new AddCommand(validExercise), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validExercise.getName().toString(), added), expectedModel);
    }

}
