package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    Appointment markedAppointment = new Appointment("Sore Throat", "2019-12-10 16:30", true);

    @Test
    public void execute_markUnmarkedAppointment_success() {
        List<Appointment> markedAppointmentList = new ArrayList<>();
        markedAppointmentList.add(markedAppointment);

        Person personToMarkFor = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person markedPerson = new PersonBuilder(personToMarkFor).withAppointmentList(markedAppointmentList).build();

        MarkCommand markCommand = new MarkCommand(INDEX_SECOND_PERSON, INDEX_FIRST_APPOINTMENT);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS,
                INDEX_FIRST_APPOINTMENT.getOneBased(),
                markedPerson.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToMarkFor, markedPerson);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markAlreadyMarkedAppointment_throwsCommandException() {
        MarkCommand markCommand = new MarkCommand(INDEX_THIRD_PERSON, INDEX_FIRST_APPOINTMENT);

        assertCommandFailure(markCommand, model, MarkCommand.MESSAGE_ALREADY_MARKED);
    }

    @Test
    public void execute_markNonExistentAppointment_throwsCommandException() {
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, INDEX_THIRD_APPOINTMENT);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }
}