package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_JURONGPOINT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteAppointmentCommand.MESSAGE_NO_APPOINTMENT_TO_DELETE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.MUSAB_WITH_NO_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class DeleteAppointmentCommandTest {

    @Test
    public void execute_validPersonIndexUnfilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                                .withAppointment(new AppointmentBuilder()
                                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                                        .withLocation(VALID_LOCATION_NUS).build())
                                .withAppointment(new AppointmentBuilder()
                                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                                        .withLocation(VALID_LOCATION_JURONGPOINT).build())
                                .build());

        // Create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                                  .build());
        Person editedPerson = expectedModel.getFilteredPersonList().get(0);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                            .withAppointment(new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_21_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build())
                            .withAppointment(new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_22_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build())
                            .build());

        Index outOfBoundIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void execute_noAppointmentToDeleteUnfilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(deleteAppointmentCommand, testModel, MESSAGE_NO_APPOINTMENT_TO_DELETE);
    }

    @Test
    public void execute_validPersonIndexFilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                                .withAppointment(new AppointmentBuilder()
                                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                                        .withLocation(VALID_LOCATION_NUS).build())
                                .withAppointment(new AppointmentBuilder()
                                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                                        .withLocation(VALID_LOCATION_JURONGPOINT).build())
                                .build());
        actualModel.addPerson(ALICE);
        // Filter list
        showPersonAtIndex(actualModel, INDEX_FIRST_PERSON);

        // Create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                                  .build());
        expectedModel.addPerson(ALICE);
        Person editedPerson = expectedModel.getFilteredPersonList().get(0);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_PERSON_SUCCESS, editedPerson);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }
    @Test
    public void execute_invalidPersonIndexFilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                            .withAppointment(new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_21_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build())
                            .withAppointment(new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_22_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build())
                            .build());
        testModel.addPerson(ALICE);
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noAppointmentToDeleteFilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());
        testModel.addPerson(new PersonBuilder(ALICE).build());
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);

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
