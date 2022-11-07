package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_WESTMALL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ModelBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddAppointmentCommandTest {


    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, null));
    }

    @Test
    public void execute_validAppointmentUnfilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelBuilder().build();

        // Create expectedModel
        Model expectedModel = new ModelBuilder().build();
        Person expectedPerson = expectedModel.getFilteredPersonList().get(0);
        Appointment appointmentToAdd = new AppointmentBuilder().build();
        expectedPerson.getAppointments().add(appointmentToAdd);

        // Create addAppointmentCommand
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, appointmentToAdd);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, appointmentToAdd);

        assertCommandSuccess(addAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAppointmentFilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelBuilder().build();
        showPersonAtIndex(actualModel, INDEX_FIRST_PERSON);

        // Create expectedModel
        Model expectedModel = new ModelBuilder().build();
        Person expectedPerson = expectedModel.getFilteredPersonList().get(0);
        Appointment appointmentToAdd = new AppointmentBuilder().build();
        expectedPerson.getAppointments().add(appointmentToAdd);
        expectedModel.updateFilteredPersonList(person -> person.isSamePerson(expectedPerson));

        // Create addAppointmentCommand
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, appointmentToAdd);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, appointmentToAdd);

        assertCommandSuccess(addAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addDuplicateAppointmentWithSameDateTimeUnfilteredList_failure() {
        // Create testModel
        Model testModel = new ModelBuilder().build();
        testModel.addPerson(new PersonBuilder().build());
        Person testPerson = testModel.getFilteredPersonList().get(0);
        testPerson.getAppointments().add(new AppointmentBuilder().build());

        // Create addAppointmentCommand
        Appointment duplicateAppointmentToAdd = new AppointmentBuilder()
                .withLocation("differentValidLocation")
                .build();
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, duplicateAppointmentToAdd);

        assertCommandFailure(addAppointmentCommand, testModel, MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME);
    }

    @Test
    public void execute_addDuplicateAppointmentWithSameDateTimeFilteredList_failure() {
        // Create testModel
        Model testModel = new ModelBuilder().build();
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);
        Person testPerson = testModel.getFilteredPersonList().get(0);
        testPerson.getAppointments().add(new AppointmentBuilder().build());

        // Create addAppointmentCommand
        Appointment duplicateAppointmentToAdd = new AppointmentBuilder()
                .withLocation("differentValidLocation")
                .build();
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, duplicateAppointmentToAdd);

        assertCommandFailure(addAppointmentCommand, testModel, MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Model testModel = new ModelBuilder().build();
        Index outOfBoundIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);

        Appointment appointmentToAdd = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(outOfBoundIndex, appointmentToAdd);

        assertCommandFailure(addAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        Model testModel = new ModelBuilder().build();
        showPersonAtIndex(testModel, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < testModel.getAddressBook().getPersonList().size());

        Appointment appointmentToAdd = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand =
                new AddAppointmentCommand(outOfBoundIndex, appointmentToAdd);

        assertCommandFailure(addAppointmentCommand, testModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Appointment appointmentToAdd = new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build();
        final AddAppointmentCommand standardCommand =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, appointmentToAdd);

        // same values -> returns true
        Appointment appointmentToAddCopy = new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build();
        AddAppointmentCommand commandWithSameValues =
                new AddAppointmentCommand(INDEX_FIRST_PERSON, appointmentToAddCopy);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new AddAppointmentCommand(INDEX_SECOND_PERSON, appointmentToAdd)));

        // different appointment -> returns false
        Appointment differentAppointmentToAdd = new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_22_JAN_2023)
                .withLocation(VALID_LOCATION_WESTMALL).build();
        assertFalse(standardCommand.equals(
                new AddAppointmentCommand(INDEX_FIRST_PERSON, differentAppointmentToAdd)));
    }
}
