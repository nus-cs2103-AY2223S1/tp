package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddStuCommand}.
 */
public class AddStuCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddStuCommand(validStudent), model,
                String.format(AddStuCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getAddressBook().getStudentList().get(0);
        assertCommandFailure(new AddStuCommand(studentInList), model, AddStuCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
