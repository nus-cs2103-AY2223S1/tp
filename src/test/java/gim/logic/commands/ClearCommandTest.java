package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static gim.testutil.TypicalExercises.getTypicalExerciseTracker;

import org.junit.jupiter.api.Test;

import gim.model.ExerciseTracker;
import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyExerciseTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExerciseTracker_success() {
        Model model = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalExerciseTracker(), new UserPrefs());
        expectedModel.setExerciseTracker(new ExerciseTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
