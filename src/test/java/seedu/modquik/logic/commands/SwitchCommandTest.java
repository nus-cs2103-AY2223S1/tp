package seedu.modquik.logic.commands;

import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.model.ModelType.CONSULTATION;
import static seedu.modquik.model.ModelType.GRADE_CHART;
import static seedu.modquik.model.ModelType.STUDENT;
import static seedu.modquik.model.ModelType.TUTORIAL;

import org.junit.jupiter.api.Test;

import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ModelType;


public class SwitchCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_switchToStudent_showStudentList() {
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SwitchCommand(STUDENT), model, SwitchCommand.MESSAGE_SUCCESS_STUDENT,
                ModelType.STUDENT, expectedModel);
    }

    @Test
    public void execute_switchToTutorial_showTutorialList() {
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SwitchCommand(TUTORIAL), model, SwitchCommand.MESSAGE_SUCCESS_TUTORIAL,
                ModelType.TUTORIAL, expectedModel);
    }

    @Test
    public void execute_switchToConsultation_showConsultationList() {
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SwitchCommand(CONSULTATION), model, SwitchCommand.MESSAGE_SUCCESS_CONSULTATION,
                ModelType.CONSULTATION, expectedModel);
    }

    @Test
    public void execute_switchToGrade_showGradeChart() {
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SwitchCommand(GRADE_CHART), model, SwitchCommand.MESSAGE_SUCCESS_GRADE,
                ModelType.GRADE_CHART, expectedModel);
    }
}
