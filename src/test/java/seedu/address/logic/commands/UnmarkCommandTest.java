package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

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
class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToUnmark = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Attendance attendance = new Attendance("T01", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_SECOND_PERSON, attendance);

        Set<Attendance> attendanceSet = new HashSet<>(personToUnmark.getAttendances());
        attendanceSet.remove(attendance);
        Person unmarkedPerson = new PersonBuilder(personToUnmark).setAttended(attendanceSet).build();

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_SUCCESS,
                attendance.className, unmarkedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()), unmarkedPerson);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(0);
        Attendance attendance = new Attendance("T01", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_PERSON, attendance);

        Set<Attendance> attendanceSet = new HashSet<>(personInFilteredList.getAttendances());
        attendanceSet.remove(attendance);
        Person unmarkedPerson = new PersonBuilder(personInFilteredList).setAttended(attendanceSet).build();

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_SUCCESS,
                attendance.className, unmarkedPerson);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), unmarkedPerson);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Attendance attendance = new Attendance("T04", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex, attendance);

        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
