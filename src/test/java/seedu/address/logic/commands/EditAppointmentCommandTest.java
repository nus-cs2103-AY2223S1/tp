package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
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

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        AddressBook testAddressBook = new AddressBook();
        Model testModel = new ModelManager(testAddressBook, new UserPrefs());

        Appointment appointment = new Appointment("Fever", "2019-12-31 23:45", "1Y2M", false);
        Person person = new PersonBuilder().withAppointment(appointment).build();
        appointment.setPatient(person);

        testModel.addPerson(person);
        testModel.addAppointment(appointment);

        Appointment editedAppointment = new Appointment("Cough", "2022-10-31 16:30", "3D", false);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                descriptor);

        Person editedPerson = new PersonBuilder().withAppointment(editedAppointment).build();
        editedAppointment.setPatient(editedPerson);
        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), editedAppointment);

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(person, editedPerson);
        expectedModel.setAppointment(appointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, testModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        AddressBook testAddressBook = new AddressBook();
        Model testModel = new ModelManager(testAddressBook, new UserPrefs());

        Appointment appointment = new Appointment("Fever", "2019-12-31 23:45", "", false);
        Person person = new PersonBuilder().withAppointment(appointment).build();
        appointment.setPatient(person);

        testModel.addPerson(person);
        testModel.addAppointment(appointment);

        Appointment editedAppointment = new Appointment("Cough", "2019-12-31 23:45", "", false);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                descriptor);

        Person editedPerson = new PersonBuilder().withAppointment(editedAppointment).build();
        editedAppointment.setPatient(editedPerson);
        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), editedAppointment);

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(person, editedPerson);
        expectedModel.setAppointment(appointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, testModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        AddressBook testAddressBook = new AddressBook();
        Model testModel = new ModelManager(testAddressBook, new UserPrefs());

        Appointment appointment = new Appointment("Fever", "2019-12-31 23:45", "", false);
        Person person = new PersonBuilder().withAppointment(appointment).build();
        appointment.setPatient(person);

        testModel.addPerson(person);
        testModel.addAppointment(appointment);

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                person.getName(), appointment);

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        assertCommandSuccess(editAppointmentCommand, testModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentTimeUnfilteredList_failure() {
        Appointment appointment = new Appointment("Cough", "2019-12-10 16:30", "", true);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(appointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withReason("Cough").build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}
