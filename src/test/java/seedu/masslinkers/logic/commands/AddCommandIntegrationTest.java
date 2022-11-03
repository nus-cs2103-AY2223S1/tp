package seedu.masslinkers.logic.commands;

import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.masslinkers.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.masslinkers.testutil.TypicalStudents.getTypicalMassLinkers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.ModelManager;
import seedu.masslinkers.model.UserPrefs;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.StudentBuilder;

//@@author
/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMassLinkers(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getMassLinkers(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel,
                false, false, true, true);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getMassLinkers().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
