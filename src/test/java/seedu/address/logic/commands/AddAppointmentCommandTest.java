package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_210_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.person.Appointment.EMPTY_APPOINTMENTS;
import static seedu.address.model.person.Person.MAXIMUM_APPOINTMENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.logic.util.MaximumSortedList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class AddAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @AfterAll
    public static void setAlice() {
        ALICE.setAppointments(EMPTY_APPOINTMENTS);
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, null));
    }

    @Test
    public void execute_addOneAppointment_success() {
        Person firstPerson = model.getAddressBook().getPersonList().get(0);
        MaximumSortedList<Appointment> appointmentList = new MaximumSortedList<>(MAXIMUM_APPOINTMENTS);
        appointmentList.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_21_JAN_2023))));
        firstPerson.setAppointments(appointmentList);
        EditPersonDescriptor descriptor = DESC_APPOINTMENT;
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, firstPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), firstPerson);

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultipleAppointment_success() {
        Person firstPerson = model.getAddressBook().getPersonList().get(0);
        MaximumSortedList<Appointment> appointmentList = new MaximumSortedList<>(MAXIMUM_APPOINTMENTS);
        appointmentList.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_21_JAN_2023))));
        appointmentList.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_22_JAN_2023))));
        firstPerson.setAppointments(appointmentList);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023, VALID_APPOINTMENT_22_JAN_2023).build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, firstPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), firstPerson);

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addAppointmentWithNameFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithAddressFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAddress(VALID_ADDRESS_BOB).withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithPhoneFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithEmailFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withEmail(VALID_EMAIL_BOB).withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithTagsFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023).build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_22_JAN_2023, VALID_APPOINTMENT_22_JAN_2023).build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddAppointmentCommand standardCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, DESC_APPOINTMENT);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_APPOINTMENT);
        AddAppointmentCommand commandWithSameValues = new AddAppointmentCommand(INDEX_FIRST_PERSON, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_SECOND_PERSON, copyDescriptor)));

        EditPersonDescriptor diffDescriptor = new EditPersonDescriptorBuilder()
                .withAppointments(VALID_APPOINTMENT_21_JAN_2023, VALID_APPOINTMENT_22_JAN_2023).build();
        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_FIRST_PERSON, diffDescriptor)));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_failure() {
        try {
            EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                    .withAppointments(INVALID_APPOINTMENT_210_JAN_2023, VALID_APPOINTMENT_21_JAN_2023).build();
            fail();
        } catch (Exception e) {
            assertEquals("Text '210-Jan-2023 01:00 AM' could not be parsed: "
                    + "Invalid value for DayOfMonth (valid values 1 - 28/31): 210", e.getMessage());
        }
    }

    @Test
    public void parse_validValueFollowedByInvalidValue_failure() {
        try {
            EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                    .withAppointments(VALID_APPOINTMENT_21_JAN_2023, INVALID_APPOINTMENT_210_JAN_2023).build();
            fail();
        } catch (Exception e) {
            assertEquals("Text '210-Jan-2023 01:00 AM' could not be parsed: "
                    + "Invalid value for DayOfMonth (valid values 1 - 28/31): 210", e.getMessage());
        }
    }
}
