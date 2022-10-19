package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_NON_EXISTENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
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

class UnmarkCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_unmarkMarkedAppointment_success() {
        Appointment appointment = new Appointment("Cough", "2010-12-31 23:45", "", false);
        Person person = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        appointment.setPatient(person);

        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_APPOINTMENT);
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_PERSON_SUCCESS,
                INDEX_FIRST_APPOINTMENT.getOneBased(), person.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(expectedModel.getFilteredAppointmentList().get(0), appointment);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_unmarkAlreadyUnmarkedAppointment_throwsCommandException() {
        Index targetAppointmentIndex = INDEX_SECOND_APPOINTMENT;

        UnmarkCommand unmarkCommand = new UnmarkCommand(targetAppointmentIndex);

        assertCommandFailure(unmarkCommand, model, UnmarkCommand.MESSAGE_ALREADY_UNMARKED);
    }

    @Test
    public void execute_unmarkNonExistentAppointment_throwsCommandException() {
        Index targetAppointmentIndex = INDEX_NON_EXISTENT;

        UnmarkCommand unmarkCommand = new UnmarkCommand(targetAppointmentIndex);

        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}
