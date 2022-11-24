package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_NON_EXISTENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;
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



class MarkCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markUnmarkedAppointment_success() {
        Appointment appointment = new Appointment("Sore Throat", "2019-12-10 16:30", "", true);
        Person person = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        appointment.setPatient(person);

        MarkCommand markCommand = new MarkCommand(INDEX_SECOND_APPOINTMENT);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                INDEX_SECOND_APPOINTMENT.getOneBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(expectedModel.getFilteredAppointmentList().get(1), appointment);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_recurringBooking_success() {
        Appointment appointment = new Appointment("Sore Throat", "2019-12-10 16:30", "1Y", true);
        Person person = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        appointment.setPatient(person);
        Appointment recurringAppointment = new Appointment(appointment);

        MarkCommand markCommand = new MarkCommand(INDEX_THIRD_APPOINTMENT);
        String markSuccessMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                INDEX_THIRD_APPOINTMENT.getOneBased());
        String recurringMessage = "\nA recurring appointment has been automatically added";
        String expectedMessage = markSuccessMessage + recurringMessage;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(expectedModel.getFilteredAppointmentList().get(2), appointment);
        expectedModel.addAppointment(recurringAppointment);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }



    @Test
    public void execute_markAlreadyMarkedAppointment_throwsCommandException() {
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        MarkCommand markCommand = new MarkCommand(targetAppointmentIndex);

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_ALREADY_MARKED);
    }

    @Test
    public void execute_markNonExistentAppointment_throwsCommandException() {
        Index targetAppointmentIndex = INDEX_NON_EXISTENT;

        MarkCommand markCommand = new MarkCommand(targetAppointmentIndex);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}
