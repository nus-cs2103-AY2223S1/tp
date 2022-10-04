package gim.logic.commands;

import static gim.logic.commands.CommandTestUtil.assertCommandFailure;
import static gim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static gim.testutil.TypicalExercises.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gim.model.Model;
import gim.model.ModelManager;
import gim.model.UserPrefs;
import gim.model.person.Exercise;
import gim.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newExercise_success() {
        Exercise validExercise = new ExerciseBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addExercise(validExercise);

        assertCommandSuccess(new AddCommand(validExercise), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validExercise), expectedModel);
    }

    @Test
    public void execute_duplicateExercise_throwsCommandException() {
        Exercise personInList = model.getAddressBook().getExerciseList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
