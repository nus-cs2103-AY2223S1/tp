package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_23_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_JURONGPOINT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_WESTMALL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditAppointmentCommand.MESSAGE_NO_APPOINTMENT_TO_EDIT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.MUSAB_WITH_NO_APPT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditAppointmentCommandTest {
    @Test
    public void execute_overwriteAppointmentsWithOneAppointment_success() {
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
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_23_JAN_2023)
                        .withLocation(VALID_LOCATION_WESTMALL).build())
                .build());
        Person expectedPerson = expectedModel.getFilteredPersonList().get(0);

        // Create editAppointmentCommand
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_23_JAN_2023)
                        .withLocation(VALID_LOCATION_WESTMALL).build())
                .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, expectedPerson);

        assertCommandSuccess(editAppointmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_overwriteNoExistingAppointmentsWithAppointment_failure() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        Person testPerson = new PersonBuilder(MUSAB_WITH_NO_APPT).build();
        testModel.addPerson(testPerson);

        Person expectedPerson = new PersonBuilder(MUSAB_WITH_NO_APPT)
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build())
                .build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(expectedPerson).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = MESSAGE_NO_APPOINTMENT_TO_EDIT;

        assertCommandFailure(editAppointmentCommand, testModel, expectedMessage);
    }

    @Test
    public void execute_overwriteUsingAppointmentsWithInvalidIndex_failure() {
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        Person testPerson = new PersonBuilder(MUSAB_WITH_NO_APPT)
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_21_JAN_2023)
                        .withLocation(VALID_LOCATION_NUS).build())
                .withAppointment(new AppointmentBuilder()
                        .withDateTime(VALID_DATETIME_22_JAN_2023)
                        .withLocation(VALID_LOCATION_JURONGPOINT).build())
                .build();

        Index outOfBoundIndex = Index.fromOneBased(testModel.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(testPerson).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        assertCommandFailure(editAppointmentCommand, testModel, expectedMessage);
    }

    @Test
    public void equals() {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(
                new PersonBuilder(MUSAB_WITH_NO_APPT).build()).build();
        final EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(
                INDEX_FIRST_PERSON, descriptor);

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        EditAppointmentCommand commandWithSameValues = new EditAppointmentCommand(INDEX_FIRST_PERSON, descriptor);
        assertTrue(editAppointmentCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(editAppointmentCommand.equals(editAppointmentCommand));

        // null -> returns false
        assertFalse(editAppointmentCommand.equals(null));

        // different types -> returns false
        assertFalse(editAppointmentCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        EditPersonDescriptor differentDescriptor = new EditPersonDescriptorBuilder(
                new PersonBuilder(BENSON).build()).build();
        assertFalse(editAppointmentCommand.equals(new EditAppointmentCommand(INDEX_FIRST_PERSON, differentDescriptor)));
    }

}
