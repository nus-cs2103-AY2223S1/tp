package seedu.modquik.logic.commands;

import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.testutil.TypicalStudents.getTypicalModQuik;

import org.junit.jupiter.api.Test;

import seedu.modquik.model.ModQuik;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyModQuikAll_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("all"), model, ClearCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_nonEmptyModQuikAll_success() {
        Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModQuik(), new UserPrefs());
        expectedModel.setModQuik(new ModQuik());

        assertCommandSuccess(new ClearCommand("all"), model, ClearCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_emptyModQuikStudent_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("student"), model,
                ClearCommand.MESSAGE_SUCCESS_STUDENT, ModelType.STUDENT, expectedModel);
    }

    @Test
    public void execute_nonEmptyModQuikStudent_success() {
        Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModQuik(), new UserPrefs());
        expectedModel.resetStudents();

        assertCommandSuccess(new ClearCommand("student"), model,
                ClearCommand.MESSAGE_SUCCESS_STUDENT, ModelType.STUDENT, expectedModel);
    }

    @Test
    public void execute_emptyModQuikConsultation_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("consultation"), model,
                ClearCommand.MESSAGE_SUCCESS_CONSULTATION, ModelType.CONSULTATION, expectedModel);
    }

    @Test
    public void execute_nonEmptyModQuikConsultation_success() {
        Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModQuik(), new UserPrefs());
        expectedModel.resetConsultations();

        assertCommandSuccess(new ClearCommand("consultation"), model,
                ClearCommand.MESSAGE_SUCCESS_CONSULTATION, ModelType.CONSULTATION, expectedModel);
    }

    @Test
    public void execute_emptyModQuikTutorial_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("tutorial"), model,
                ClearCommand.MESSAGE_SUCCESS_TUTORIAL, ModelType.TUTORIAL, expectedModel);
    }

    @Test
    public void execute_nonEmptyModQuikTutorial_success() {
        Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModQuik(), new UserPrefs());
        expectedModel.resetTutorials();

        assertCommandSuccess(new ClearCommand("tutorial"), model,
                ClearCommand.MESSAGE_SUCCESS_TUTORIAL, ModelType.TUTORIAL, expectedModel);
    }

    @Test
    public void execute_emptyModQuikReminder_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand("reminder"), model,
                ClearCommand.MESSAGE_SUCCESS_REMINDER, expectedModel);
    }

    @Test
    public void execute_nonEmptyModQuikReminder_success() {
        Model model = new ModelManager(getTypicalModQuik(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModQuik(), new UserPrefs());
        expectedModel.resetReminders();

        assertCommandSuccess(new ClearCommand("reminder"), model,
                ClearCommand.MESSAGE_SUCCESS_REMINDER, expectedModel);
    }
}
