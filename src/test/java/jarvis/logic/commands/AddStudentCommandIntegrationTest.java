package jarvis.logic.commands;

import static jarvis.logic.commands.CommandTestUtil.assertCommandFailure;
import static jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jarvis.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.model.Model;
import jarvis.model.ModelManager;
import jarvis.model.Student;
import jarvis.model.UserPrefs;
import jarvis.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddStudentCommand}.
 */
public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalStudentBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddStudentCommand(validStudent), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getStudentBook().getStudentList().get(0);
        assertCommandFailure(new AddStudentCommand(studentInList), model, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
