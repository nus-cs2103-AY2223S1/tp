package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FIRST_VALID_APPOINTMENT_OBJECT;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_VALID_APPOINTMENT_OBJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteAppointmentCommand.MESSAGE_NO_APPOINTMENT_TO_DELETE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.MUSAB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DeleteAppointmentCommandTest {

    @Test
    public void execute_validIndex_throwsCommandException() {
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(new PersonBuilder(MUSAB).withAppointment(
                FIRST_VALID_APPOINTMENT_OBJECT).buildWithAppointments());
        expectedModel.addPerson(new PersonBuilder(MUSAB).buildWithAppointments());

        Person editedPerson = expectedModel.getAddressBook().getPersonList().get(0);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder(MUSAB)
                .withAppointment(FIRST_VALID_APPOINTMENT_OBJECT)
                .withAppointment(SECOND_VALID_APPOINTMENT_OBJECT)
                .buildWithAppointments());
        Index outOfBoundIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noAppointmentToDelete_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder(MUSAB).buildWithAppointments());

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(deleteAppointmentCommand, testModel, MESSAGE_NO_APPOINTMENT_TO_DELETE);
    }

    @Test
    public void equals() {
        DeleteAppointmentCommand deleteAllApptFromFirstCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);
        DeleteAppointmentCommand deleteAllApptFromSecondCommand = new DeleteAppointmentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteAllApptFromFirstCommand.equals(deleteAllApptFromFirstCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstCommandCopy = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteAllApptFromFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteAllApptFromFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAllApptFromFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteAllApptFromFirstCommand.equals(deleteAllApptFromSecondCommand));
    }
}
