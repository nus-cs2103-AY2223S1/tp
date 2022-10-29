package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalToDos.getTypicalTaskBookWithToDos;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;
class AttendanceAddCommandTest {
    private static final String aliceMod = "CS2030";
    private static final String bobMod = "CS2040";
    private static final String size = "5";

    private static final String invalidSize = "13";

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBookWithToDos(), new UserPrefs());

    @Test
    public void execute_addAttendanceUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(firstStudent).withAttendanceList(aliceMod, size).build();
        AttendanceAddCommand attendanceAddCommand = new AttendanceAddCommand(INDEX_FIRST_STUDENT,
                editedStudent.getAttendanceList().getMod(),
                String.valueOf(editedStudent.getAttendanceList().getSize()));

        String expectedMessage = String.format(AttendanceAddCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(firstStudent, editedStudent);

        assertCommandSuccess(attendanceAddCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AttendanceAddCommand attendanceAddCommand = new AttendanceAddCommand(outOfBoundIndex, bobMod, size);

        assertCommandFailure(attendanceAddCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        AttendanceAddCommand attendanceAddCommand = new AttendanceAddCommand(outOfBoundIndex, bobMod, size);

        assertCommandFailure(attendanceAddCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSize_throwsCommandException() {
        AttendanceAddCommand attendanceAddCommand = new AttendanceAddCommand(INDEX_FIRST_STUDENT,
                aliceMod, invalidSize);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_ATTENDANCE_LIST_INDEX);
        assertThrows(CommandException.class, expectedMessage,
                () -> attendanceAddCommand.execute(model));
    }

    @Test
    public void equals() {
        final AttendanceAddCommand standardCommand = new AttendanceAddCommand(INDEX_FIRST_STUDENT, aliceMod, size);
        AttendanceAddCommand commandWithSameValues = new AttendanceAddCommand(INDEX_FIRST_STUDENT, aliceMod, size);

        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AttendanceAddCommand(INDEX_SECOND_STUDENT, aliceMod, size)));
        // different mod -> return false
        assertFalse(standardCommand.equals(new AttendanceAddCommand(INDEX_FIRST_STUDENT, bobMod, size)));

        // different size -> return false
        assertFalse(standardCommand.equals(new AttendanceAddCommand(INDEX_FIRST_STUDENT, bobMod, "1")));
    }
}
