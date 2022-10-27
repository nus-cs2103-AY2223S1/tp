package seedu.classify.logic.commands;

import static seedu.classify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddStudCommand}.
 */
public class AddStudCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddStudCommand(validStudent), model,
                String.format(AddStudCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student personInList = model.getStudentRecord().getStudentList().get(0);
        assertCommandFailure(new AddStudCommand(personInList), model, AddStudCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
