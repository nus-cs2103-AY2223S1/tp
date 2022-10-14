package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;


public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment appointment = new Appointment("Sore Throat", "2019-10-10 16:30", "1Y2M", true);
        Person person = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        appointment.setPatient(person);

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(appointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_THIRD_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), appointment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person expectedPerson = new PersonBuilder(expectedModel.getFilteredPersonList().get(2)).build();
        expectedPerson.getAppointments().set(0, appointment);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(2), expectedPerson);
        expectedModel.setAppointment(expectedModel.getFilteredAppointmentList().get(0), appointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        Appointment appointment = new Appointment("Sore Throat", "2010-12-31 23:45", "", true);
        Person person = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        appointment.setPatient(person);

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason("Sore Throat").build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_THIRD_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), appointment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person expectedPerson = new PersonBuilder(expectedModel.getFilteredPersonList().get(2)).build();
        expectedPerson.getAppointments().set(0, appointment);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(2), expectedPerson);
        expectedModel.setAppointment(expectedModel.getFilteredAppointmentList().get(0), appointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_THIRD_PERSON,
                INDEX_FIRST_APPOINTMENT, new EditAppointmentDescriptor());
        Person person = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        Appointment appointment = person.getAppointments().get(0);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), appointment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);

        Appointment appointment = new Appointment("Sore Throat", "2010-12-31 23:45", "", true);
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        appointment.setPatient(personInFilteredList);

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason("Sore Throat").build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                personInFilteredList.getName(), appointment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person expectedPerson = new PersonBuilder(expectedModel.getFilteredPersonList().get(2)).build();
        expectedPerson.getAppointments().set(0, appointment);
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);
        expectedModel.setAppointment(expectedModel.getFilteredAppointmentList().get(0), appointment);
        showPersonAtIndex(expectedModel, INDEX_THIRD_PERSON);
        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentTimeUnfilteredList_failure() {
        Appointment appointment = new Appointment("Cough", "2019-12-10 16:30", "", true);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(appointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_THIRD_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_THIRD_PERSON);

        Appointment appointment = new Appointment("Cough", "2019-12-10 16:30", "", true);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(appointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason("Cough").build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex,
                INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Person person = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(person.getAppointments().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason("Cough").build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON,
                outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}
