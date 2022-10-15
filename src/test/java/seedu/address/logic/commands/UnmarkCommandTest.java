package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;
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

class UnmarkCommandTest {

    private final Model typicalModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_unmarkMarkedAppointment_success() {
        Index targetPersonIndex = INDEX_FIRST_PERSON;
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        AddressBook testAddressBook = new AddressBook();
        Model testModel = new ModelManager(testAddressBook, new UserPrefs());

        Appointment markedAppointment = new Appointment("Cough", "2010-12-22 12:45", "", true);
        Person markedPerson = new PersonBuilder().withAppointment(markedAppointment).build();
        markedAppointment.setPatient(markedPerson);

        testModel.addPerson(markedPerson);
        testModel.addAppointment(markedAppointment);
        Person personToUnmarkFor = testModel.getFilteredPersonList().get(targetPersonIndex.getZeroBased());

        Appointment unmarkedAppointment = new Appointment("Cough", "2010-12-22 12:45", "", false);
        Person unmarkedPerson = new PersonBuilder().withAppointment(unmarkedAppointment).build();
        unmarkedAppointment.setPatient(unmarkedPerson);

        UnmarkCommand unmarkCommand = new UnmarkCommand(targetPersonIndex, targetAppointmentIndex);
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_PERSON_SUCCESS,
                targetAppointmentIndex.getOneBased(),
                unmarkedPerson.getName());

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToUnmarkFor, unmarkedPerson);
        expectedModel.setAppointment(markedAppointment, unmarkedAppointment);

        assertCommandSuccess(unmarkCommand, testModel, expectedMessage, expectedModel);
    }

    @Test
    void execute_unmarkAlreadyUnmarkedAppointment_throwsCommandException() {
        Index targetPersonIndex = INDEX_SECOND_PERSON;
        Index targetAppointmentIndex = INDEX_FIRST_APPOINTMENT;

        UnmarkCommand unmarkCommand = new UnmarkCommand(targetPersonIndex, targetAppointmentIndex);

        assertCommandFailure(unmarkCommand, typicalModel, UnmarkCommand.MESSAGE_ALREADY_UNMARKED);
    }

    @Test
    public void execute_unmarkNonExistentAppointment_throwsCommandException() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, INDEX_THIRD_APPOINTMENT);

        assertCommandFailure(unmarkCommand, typicalModel, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}
