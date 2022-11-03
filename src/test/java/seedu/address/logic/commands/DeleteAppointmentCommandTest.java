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
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class DeleteAppointmentCommandTest {
    @Test
    public void execute_validIndexUnfilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        actualModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build())
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                        .withLocation(VALID_LOCATION_JURONGPOINT).build())
                .build());

        // Create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        expectedModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                        .withLocation(VALID_LOCATION_JURONGPOINT).build())
                .build());
        Appointment deletedAppointment = new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build();

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);

        String expectedMessage =
                String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, deletedAppointment);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
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
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        expectedModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                                .withAppointment(new AppointmentBuilder()
                                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                                        .withLocation(VALID_LOCATION_JURONGPOINT).build())
                                .build());
        expectedModel.addPerson(ALICE);
        Appointment deletedAppointment = new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build();

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_APPOINTMENT);

        String expectedMessage =
                String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, deletedAppointment);

        assertCommandSuccess(deleteAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build())
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build())
                .build());

        Index outOfBoundPersonIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(outOfBoundPersonIndex, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    @Test
    public void execute_invalidPersonIndexFilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
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

        Index outOfBoundPersonIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(outOfBoundPersonIndex, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT)
                            .withAppointment(new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_21_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build())
                            .withAppointment(new AppointmentBuilder()
                                    .withDateTime(VALID_DATETIME_22_JAN_2023)
                                    .withLocation(VALID_LOCATION_NUS).build())
                            .build());

        Index outOfBoundAppointmentIndex =
                Index.fromOneBased(testModel.getFilteredPersonList().get(0)
                                                .getAppointments().size() + 1);

        DeleteAppointmentCommand deleteAppointmentCommand =
                new DeleteAppointmentCommand(outOfBoundAppointmentIndex, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(deleteAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexFilteredList_throwsCommandException() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs(), new CommandHistory());
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

        Index outOfBoundAppointmentIndex =
                Index.fromOneBased(testModel.getFilteredPersonList().get(0)
                                            .getAppointments().size() + 1);

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
