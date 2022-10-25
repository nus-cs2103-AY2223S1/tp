package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.Command.MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ClearAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {
        Person personToClearAppointment = model.getFilteredPersonList().get(FIRST_INDEX.getZeroBased());
        ClearAppointmentCommand clearAppointmentCommand = new ClearAppointmentCommand(FIRST_INDEX);

        String expectedMessage = ClearAppointmentCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPersonToAddAppointment =
                new PersonBuilder(personToClearAppointment)
                        .withAppointment(Appointment.NO_APPOINTMENT_SCHEDULED).build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPersonToAddAppointment);

        assertCommandSuccess(clearAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, FIRST_INDEX);

        Person personToClearAppointment = model.getFilteredPersonList().get(FIRST_INDEX.getZeroBased());
        ClearAppointmentCommand clearAppointmentCommand = new ClearAppointmentCommand(FIRST_INDEX);

        String expectedMessage = ClearAppointmentCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person expectedPersonToAddAppointment =
                new PersonBuilder(personToClearAppointment)
                        .withAppointment(Appointment.NO_APPOINTMENT_SCHEDULED).build();
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), expectedPersonToAddAppointment);

        assertCommandSuccess(clearAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClearAppointmentCommand clearAppointmentCommand = new ClearAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(clearAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_clearAppointmentInRecordList_failure() {
        ClearAppointmentCommand clearAppointmentCommand = new ClearAppointmentCommand(FIRST_INDEX);

        model.setFilteredRecordList(BENSON);
        model.setRecordListDisplayed(true);

        assertCommandFailure(clearAppointmentCommand, model, MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE);
    }

    @Test
    void equals() {
        ClearAppointmentCommand testFirstClearAppointmentCommand = new ClearAppointmentCommand(FIRST_INDEX);
        ClearAppointmentCommand testSecondClearAppointmentCommand = new ClearAppointmentCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(testFirstClearAppointmentCommand.equals(testFirstClearAppointmentCommand));

        // same values -> returns true
        ClearAppointmentCommand testFirstClearAppointmentCommandCopy = new ClearAppointmentCommand(FIRST_INDEX);
        assertTrue(testFirstClearAppointmentCommand.equals(testFirstClearAppointmentCommand));

        // different types -> returns false
        assertFalse(testFirstClearAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(testFirstClearAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(testFirstClearAppointmentCommand.equals(testSecondClearAppointmentCommand));
    }
}
