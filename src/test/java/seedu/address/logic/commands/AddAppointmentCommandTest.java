package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_JURONGPOINT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.MUSAB_WITH_NO_APPT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.logic.util.MaximumSortedList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, null));
    }

    @Test
    public void execute_validAppointmentUnfilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(new PersonBuilder().build());

<<<<<<< HEAD
        // Create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(new PersonBuilder().build());
        Person expectedPerson = expectedModel.getFilteredPersonList().get(0);
        expectedPerson.getAppointments().add(new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build());
=======
        MaximumSortedList<Appointment> appointments = new MaximumSortedList<>(MAXIMUM_NUM_OF_APPOINTMENTS);
        appointments.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_21_JAN_2023))));
        Person expectedPerson = expectedModel.getAddressBook().getPersonList().get(0);
        expectedPerson.setAppointments(appointments);
>>>>>>> musab_max_appointmnet

        // Create addAppointmentCommand
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAppointment(new AppointmentBuilder()
                                            .withDateTime(VALID_DATETIME_21_JAN_2023)
                                            .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, expectedPerson);

        assertCommandSuccess(addAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAppointmentFilteredList_success() {
        // Create actualModel
        Model actualModel = new ModelManager(new AddressBook(), new UserPrefs());
        actualModel.addPerson(new PersonBuilder().build());
        actualModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

<<<<<<< HEAD
        // Create expectedModel
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPerson(new PersonBuilder().build());
        expectedModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());
        Person expectedPerson = expectedModel.getFilteredPersonList().get(0);
        expectedPerson.getAppointments().add(new AppointmentBuilder()
                .withDateTime(VALID_DATETIME_21_JAN_2023)
                .withLocation(VALID_LOCATION_NUS).build());
=======
        MaximumSortedList<Appointment> appointments = new MaximumSortedList<>(MAXIMUM_NUM_OF_APPOINTMENTS);
        appointments.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_21_JAN_2023))));
        appointments.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_22_JAN_2023))));
        Person expectedPerson = expectedModel.getAddressBook().getPersonList().get(0);
        expectedPerson.setAppointments(appointments);
>>>>>>> musab_max_appointmnet

        // Create addAppointmentCommand
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAppointment(new AppointmentBuilder()
                                            .withDateTime(VALID_DATETIME_21_JAN_2023)
                                            .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, expectedPerson);

        assertCommandSuccess(addAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addDuplicateAppointmentUnfilteredList_failure() {
        // Create testModel
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder().build());
<<<<<<< HEAD
        Set<Appointment> appointments = new HashSet<>();
        appointments.add(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build());
        Person testPerson = testModel.getFilteredPersonList().get(0);
=======

        MaximumSortedList<Appointment> appointments = new MaximumSortedList<>(MAXIMUM_NUM_OF_APPOINTMENTS);
        appointments.add(new Appointment(new DateTime(DateTimeParser.parseLocalDateTimeFromString(
                VALID_APPOINTMENT_21_JAN_2023))));
        Person testPerson = testModel.getAddressBook().getPersonList().get(0);
>>>>>>> musab_max_appointmnet
        testPerson.setAppointments(appointments);

        // Create addAppointmentCommand
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        assertCommandFailure(addAppointmentCommand, testModel, MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_addDuplicateAppointmentFilteredList_failure() {
        // Create testModel
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        testModel.addPerson(new PersonBuilder().build());
        testModel.addPerson(new PersonBuilder(MUSAB_WITH_NO_APPT).build());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Set<Appointment> appointments = new HashSet<>();
        appointments.add(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build());
        Person testPerson = testModel.getFilteredPersonList().get(0);
        testPerson.setAppointments(appointments);

        // Create addAppointmentCommand
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        assertCommandFailure(addAppointmentCommand, testModel, MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_addAppointmentWithNameFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withName(VALID_NAME_BOB)
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithAddressFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAddress(VALID_ADDRESS_BOB)
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithPhoneFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withPhone(VALID_PHONE_BOB)
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithEmailFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withEmail(VALID_EMAIL_BOB)
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_addAppointmentWithTagsFieldEdit_failure() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON, descriptor));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                                        .withAppointment(new AppointmentBuilder()
                                                .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                .withLocation(VALID_LOCATION_NUS).build())
                                        .build();
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
                                            .withAppointment(new AppointmentBuilder()
                                                    .withDateTime(VALID_DATETIME_21_JAN_2023)
                                                    .withLocation(VALID_LOCATION_NUS).build())
                                            .withAppointment(new AppointmentBuilder()
                                                    .withDateTime(VALID_DATETIME_22_JAN_2023)
                                                    .withLocation(VALID_LOCATION_JURONGPOINT).build())
                                            .build();
        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddAppointmentCommand(INDEX_FIRST_PERSON, diffDescriptor)));
    }
}
