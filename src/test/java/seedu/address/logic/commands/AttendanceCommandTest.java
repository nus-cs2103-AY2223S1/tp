package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AttendanceCommandTest {

    private static final String ATTENDANCE_STUB = "2022-01-07";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addAttendanceUnfilteredList_success() {
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(secondPerson).withAttendance(ATTENDANCE_STUB).build();
        secondPerson.clearAttendanceList();

        AttendanceCommand attendanceCommand = new AttendanceCommand(INDEX_SECOND_PERSON,
                new Attendance(ATTENDANCE_STUB));

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setPerson(secondPerson, editedPerson);
        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_addAttendanceFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withAttendance(ATTENDANCE_STUB).build();

        personInFilteredList.clearAttendanceList();

        AttendanceCommand attendanceCommand = new AttendanceCommand(INDEX_FIRST_PERSON,
                new Attendance(ATTENDANCE_STUB));
        String expectedMessage = String.format(AttendanceCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withAttendance(ATTENDANCE_STUB).build();
        firstPerson.clearAttendanceList();

        AttendanceCommand attendanceCommand = new AttendanceCommand(
                INDEX_FIRST_PERSON, new Attendance(ATTENDANCE_STUB));

        String expectedMessage = String.format(AttendanceCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(attendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AttendanceCommand attendanceCommand = new AttendanceCommand(
                outOfBoundIndex, new Attendance(ATTENDANCE_STUB));

        assertCommandFailure(attendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        AttendanceCommand attendanceCommand = new AttendanceCommand(
                outOfBoundIndex, new Attendance(ATTENDANCE_STUB));
        assertCommandFailure(attendanceCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AttendanceCommand attendanceCommand = new AttendanceCommand(INDEX_FIRST_PERSON,
                new Attendance(VALID_ATTENDANCE_AMY));

        AttendanceCommand commandWithSameValue = new AttendanceCommand(INDEX_FIRST_PERSON,
                new Attendance(VALID_ATTENDANCE_AMY));
        // same values -> returns true
        assertTrue(attendanceCommand.equals(commandWithSameValue));
        // same object -> returns true
        assertTrue(attendanceCommand.equals(attendanceCommand));

        assertFalse(attendanceCommand.equals(null));

        assertFalse(attendanceCommand.equals(new ClearCommand()));

        assertFalse(attendanceCommand
                .equals(new AttendanceCommand(INDEX_SECOND_PERSON, new Attendance(VALID_ATTENDANCE_AMY))));

        assertFalse(attendanceCommand
                .equals(new AttendanceCommand(INDEX_SECOND_PERSON, new Attendance(VALID_ATTENDANCE_BOB))));

    }


}
