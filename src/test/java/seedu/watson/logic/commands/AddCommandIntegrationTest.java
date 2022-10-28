package seedu.watson.logic.commands;

import static seedu.watson.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.watson.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.UserPrefs;
import seedu.watson.model.student.Student;
import seedu.watson.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.addPerson(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                             String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getDatabase().getPersonList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
