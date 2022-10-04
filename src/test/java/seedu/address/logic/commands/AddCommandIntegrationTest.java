package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.TeachingAssistant;
import seedu.address.testutil.ProfessorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeachingAssistantBuilder;

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
        Student validPerson = new StudentBuilder().build();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new StudentCommand(validPerson), model,
            String.format(StudentCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_newProfessor_success() {
        Person validPerson = new ProfessorBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new ProfCommand(validPerson), model,
            String.format(ProfCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_newTeachingAssistant_success() {
        TeachingAssistant validPerson = new TeachingAssistantBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new TaCommand(validPerson), model,
            String.format(TaCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new StudentCommand((Student) personInList), model,
            StudentCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
