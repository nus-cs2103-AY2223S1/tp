package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.MUSAB_WITH_NO_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.util.exceptions.SortedListException;
import seedu.address.testutil.ModelBuilder;
import seedu.address.testutil.PersonBuilder;

public class DeleteAppointmentCommandTest {
    @Test
    public void execute_validIndexUnfilteredList_success() throws SortedListException {
        // Create actualModel
        Model actualModel = new ModelBuilder().build();

        // Create expectedModel
        Model expectedModel = new ModelBuilder().build();
        Person personWithAppointmentToDelete = expectedModel.getFilteredPersonList().get(0);
        Appointment deletedAppointment = personWithAppointmentToDelete.getAppointments().get(0);
        personWithAppointmentToDelete.getAppointments().remove(0);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);

        String expectedMessage =
                String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, deletedAppointment);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws SortedListException {
        // Create actualModel
        Model actualModel = new ModelBuilder().build();

        // Filter list
        showPersonAtIndex(actualModel, INDEX_FIRST_PERSON);

        // Create expectedModel
        Model expectedModel = new ModelBuilder().build();
        Person personWithAppointmentToDelete = expectedModel.getFilteredPersonList().get(0);
        Appointment deletedAppointment = personWithAppointmentToDelete.getAppointments().get(0);
        personWithAppointmentToDelete.getAppointments().remove(0);
        expectedModel.updateFilteredPersonList(person -> person.isSamePerson(personWithAppointmentToDelete));

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);

        String expectedMessage =
                String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, deletedAppointment);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Model testModel = new ModelBuilder().build();
        Index outOfBoundPersonIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(outOfBoundPersonIndex, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    @Test
    public void execute_invalidPersonIndexFilteredList_throwsCommandException() {
        Model testModel = new ModelBuilder().build();
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);

        Index outOfBoundPersonIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundPersonIndex.getZeroBased() < testModel.getAddressBook().getPersonList().size());

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(outOfBoundPersonIndex, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_throwsCommandException() {
        Model testModel = new ModelBuilder().build();

        Index outOfBoundAppointmentIndex =
                Index.fromOneBased(testModel.getFilteredPersonList().get(0)
                                                .getAppointments().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, outOfBoundAppointmentIndex);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexFilteredList_throwsCommandException() {
        Model testModel = new ModelBuilder().build();
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);

        Index outOfBoundAppointmentIndex =
                Index.fromOneBased(testModel.getFilteredPersonList().get(0)
                                            .getAppointments().size() + 1);

        // ensures that outOfBoundAppointmentIndex is in bounds of appointment list which can hold 3 appointments max
        assertTrue(outOfBoundAppointmentIndex.getZeroBased() < 3);
        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(outOfBoundAppointmentIndex, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    @Test
    public void execute_noAppointmentToDeleteUnfilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }




    @Test
    public void execute_noAppointmentToDeleteFilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());
        testModel.addPerson(new PersonBuilder(ALICE).build());
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteAppointmentCommand deleteFirstApptFromFirstCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);
        DeleteAppointmentCommand deleteSecondApptFromSecondCommand =
                new DeleteAppointmentCommand(INDEX_SECOND_PERSON, INDEX_SECOND_APPOINTMENT);

        // same object -> returns true
        assertTrue(deleteFirstApptFromFirstCommand.equals(deleteFirstApptFromFirstCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstCommandCopy =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);
        assertTrue(deleteFirstApptFromFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstApptFromFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstApptFromFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstApptFromFirstCommand.equals(deleteSecondApptFromSecondCommand));
    }
}
