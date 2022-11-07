package tuthub.logic.commands;

import static tuthub.logic.commands.CommandTestUtil.assertCommandFailure;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;
import tuthub.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTuthub(), new UserPrefs());
    }

    @Test
    public void execute_newTutor_success() {
        Tutor validTutor = new TutorBuilder().build();

        Model expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());
        expectedModel.addTutor(validTutor);

        assertCommandSuccess(new AddCommand(validTutor), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTutor), expectedModel);
    }

    @Test
    public void execute_duplicateTutor_throwsCommandException() {
        Tutor tutorInList = model.getTuthub().getTutorList().get(0);
        assertCommandFailure(new AddCommand(tutorInList), model, AddCommand.MESSAGE_DUPLICATE_TUTOR);
    }

}
