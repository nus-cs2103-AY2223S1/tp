package seedu.uninurse.logic.commands;

import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.uninurse.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.ModelManager;
import seedu.uninurse.model.UserPrefs;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for AddPatientCommand.
 */
public class AddCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUninurseBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Patient validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getUninurseBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddPatientCommand(validPerson), model,
                String.format(AddPatientCommand.MESSAGE_SUCCESS, validPerson),
                        AddPatientCommand.COMMAND_TYPE, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient personInList = model.getPatient(model.getUninurseBook().getPersonList().get(0));
        assertCommandFailure(new AddPatientCommand(personInList), model, Messages.MESSAGE_DUPLICATE_PATIENT);
    }
}
