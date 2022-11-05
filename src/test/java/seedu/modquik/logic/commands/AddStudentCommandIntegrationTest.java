package seedu.modquik.logic.commands;

import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.testutil.TypicalPersons.getTypicalModQuik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modquik.logic.commands.student.AddStudentCommand;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.UserPrefs;
import seedu.modquik.model.student.Student;
import seedu.modquik.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModQuik(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validStudent = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getModQuik(), new UserPrefs());
        expectedModel.addPerson(validStudent);

        assertCommandSuccess(new AddStudentCommand(validStudent), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), ModelType.STUDENT, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getModQuik().getPersonList().get(0);
        assertCommandFailure(new AddStudentCommand(studentInList), model, AddStudentCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
