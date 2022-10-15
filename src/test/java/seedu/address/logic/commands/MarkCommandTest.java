package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Person;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToMark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, attendance);

        Person markedPerson = new PersonBuilder(personToMark).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SUCCESS,
                attendance.getAttendance(), markedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), markedPerson);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, attendance);

        Person markedPerson = new PersonBuilder(personInFilteredList).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SUCCESS,
                attendance.getAttendance(), markedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), markedPerson);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, attendance);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
