package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandFailure;
import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;
import static friday.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.student.Student;
import friday.testutil.StudentBuilder;

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
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getFriday().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
