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

        Appointment unmarkedAppointment = new Appointment("Fever", "2019-12-31 23:45", false);
        Person unmarkedPerson = new PersonBuilder().withAppointment(unmarkedAppointment).build();

        testModel.addPerson(unmarkedPerson);
        Person personToMarkFor = testModel.getFilteredPersonList().get(targetPersonIndex.getZeroBased());

        Appointment markedAppointment = new Appointment("Fever", "2019-12-31 23:45", true);
        Person markedPerson = new PersonBuilder().withAppointment(markedAppointment).build();

        MarkCommand markCommand = new MarkCommand(targetPersonIndex, targetAppointmentIndex);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                targetAppointmentIndex.getOneBased(),
                markedPerson.getName());

        ModelManager expectedModel = new ModelManager(testModel.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToMarkFor, markedPerson);

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
