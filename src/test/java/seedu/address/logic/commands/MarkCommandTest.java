package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



class MarkCommandTest {

    private final Model typicalModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markUnmarkedAppointment_success() {
        Index targetPersonIndex = INDEX_FIRST_PERSON;
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        AddressBook testAddressBook = new AddressBook();
        Model testModel = new ModelManager(testAddressBook, new UserPrefs());

        Appointment unmarkedAppointment = new Appointment("Fever", "2019-12-31 23:45", "", false);
        Person unmarkedPerson = new PersonBuilder().withAppointment(unmarkedAppointment).build();
        unmarkedAppointment.setPatient(unmarkedPerson);

        testModel.addPerson(unmarkedPerson);
        testModel.addAppointment(unmarkedAppointment);
        Person personToMarkFor = testModel.getFilteredPersonList().get(targetPersonIndex.getZeroBased());

        Appointment markedAppointment = new Appointment("Fever", "2019-12-31 23:45", "", true);
        Person markedPerson = new PersonBuilder().withAppointment(markedAppointment).build();
        markedAppointment.setPatient(markedPerson);

        MarkCommand markCommand = new MarkCommand(targetPersonIndex, targetAppointmentIndex);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                targetAppointmentIndex.getOneBased(),
                markedPerson.getName());

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToMarkFor, markedPerson);
        expectedModel.setAppointment(unmarkedAppointment, markedAppointment);

        assertCommandSuccess(markCommand, testModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_recurringBooking_success() {
        Index targetPersonIndex = INDEX_FIRST_PERSON;
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        AddressBook testAddressBook = new AddressBook();
        Model testModel = new ModelManager(testAddressBook, new UserPrefs());

        Appointment unmarkedAppointment = new Appointment("Fever", "2019-10-31 23:45", "2M", false);
        Person unmarkedPerson = new PersonBuilder().withAppointment(unmarkedAppointment).build();
        unmarkedAppointment.setPatient(unmarkedPerson);

        testModel.addPerson(unmarkedPerson);
        testModel.addAppointment(unmarkedAppointment);
        Person personToMarkFor = testModel.getFilteredPersonList().get(targetPersonIndex.getZeroBased());

        Appointment markedAppointment = new Appointment("Fever", "2019-10-31 23:45", "2M", true);
        Appointment recurringAppointment = new Appointment(markedAppointment);
        Person markedPerson = new PersonBuilder().withAppointment(markedAppointment)
                .withAppointment(recurringAppointment).build();
        markedAppointment.setPatient(markedPerson);
        recurringAppointment.setPatient(markedPerson);

        MarkCommand markCommand = new MarkCommand(targetPersonIndex, targetAppointmentIndex);
        String recurringMessage = "\nA recurring appointment has been automatically added";
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                targetAppointmentIndex.getOneBased(),
                markedPerson.getName()) + recurringMessage;

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToMarkFor, markedPerson);
        expectedModel.setAppointment(unmarkedAppointment, markedAppointment);
        expectedModel.addAppointment(recurringAppointment);

        assertCommandSuccess(markCommand, testModel, expectedMessage, expectedModel);
    }



    @Test
    public void execute_markAlreadyMarkedAppointment_throwsCommandException() {
        Index targetPersonIndex = INDEX_THIRD_PERSON;
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        MarkCommand markCommand = new MarkCommand(targetPersonIndex, targetAppointmentIndex);

        assertCommandFailure(markCommand, typicalModel, MarkCommand.MESSAGE_ALREADY_MARKED);
    }

    @Test
    public void execute_markNonExistentAppointment_throwsCommandException() {
        Index targetPersonIndex = INDEX_FIRST_PERSON;
        Index targetAppointmentIndex = INDEX_THIRD_APPOINTMENT;

        MarkCommand markCommand = new MarkCommand(targetPersonIndex, targetAppointmentIndex);

        assertCommandFailure(markCommand, typicalModel, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}
