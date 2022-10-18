package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_NON_EXISTENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class CancelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexCancel_success() {
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());

        Appointment unmarkedAppointment =
                new Appointment("Cough", "2010-01-01 00:00", "", false);
        Person personWithAppointment = new PersonBuilder().withAppointment(unmarkedAppointment).build();
        unmarkedAppointment.setPatient(personWithAppointment);

        testModel.addPerson(personWithAppointment);
        testModel.addAppointment(unmarkedAppointment);

        CancelCommand cancelCommand = new CancelCommand(targetAppointmentIndex);
        String expectedCancelMessage =
                CancelCommand.MESSAGE_CANCEL_APPOINTMENT_SUCCESS + personWithAppointment.getName();

        ModelManager expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        Person expectedPerson = new PersonBuilder().build();
        expectedModel.addPerson(expectedPerson);

        assertCommandSuccess(cancelCommand, testModel, expectedCancelMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentCancelling_throwsCommandException() {
        CancelCommand cancelCommand = new CancelCommand(INDEX_NON_EXISTENT);

        assertCommandFailure(cancelCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }


}
