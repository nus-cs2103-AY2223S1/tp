package seedu.taassist.logic.commands;

import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taassist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.taassist.model.Model;
import seedu.taassist.model.ModelManager;
import seedu.taassist.model.UserPrefs;
import seedu.taassist.model.student.Student;
import seedu.taassist.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaAssist(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getTaAssist(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent.getName()), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getTaAssist().getStudentList().get(0);
        String expectedMessage = String.format(AddCommand.MESSAGE_DUPLICATE_STUDENT, studentInList.getName());
        assertCommandFailure(new AddCommand(studentInList), model, expectedMessage);
    }

}
