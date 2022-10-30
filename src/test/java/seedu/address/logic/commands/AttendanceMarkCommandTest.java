package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
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
import seedu.address.model.attendance.Attendance;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

class AttendanceMarkCommandTest {
    private static final String bensonMod = "CS2040";
    private static final String size = "5";

    private static final Attendance emptyAttendance = new Attendance();
    private static final Attendance markedAttendance = new Attendance("1");
    private static final String validLessonIndex = "1";
    private static final String invalidLessonIndex = "0";
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBookWithToDos(), new UserPrefs());

    @Test
    public void execute_markAttendanceUnfilteredList_success() {
        Student secondStudent = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(secondStudent).withAttendanceList(bensonMod, size).build();
        editedStudent.getAttendanceList().mark(validLessonIndex, markedAttendance);
        AttendanceMarkCommand attendanceMarkCommand = new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                validLessonIndex, markedAttendance);

        String expectedMessage = String.format(AttendanceMarkCommand.MESSAGE_MARK_ATTENDANCE_SUCCESS, editedStudent);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudent);

        assertCommandSuccess(attendanceMarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unmarkAttendanceUnfilteredList_success() {
        Student secondStudent = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(secondStudent).withAttendanceList(bensonMod, size).build();
        editedStudent.getAttendanceList().mark(validLessonIndex, emptyAttendance);
        AttendanceMarkCommand attendanceMarkCommand = new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                validLessonIndex, emptyAttendance);

        String expectedMessage = String.format(AttendanceMarkCommand.MESSAGE_UNMARK_ATTENDANCE_SUCCESS, editedStudent);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudent);

        assertCommandSuccess(attendanceMarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AttendanceMarkCommand attendanceMarkCommand = new AttendanceMarkCommand(outOfBoundIndex,
                validLessonIndex, emptyAttendance);
        assertCommandFailure(attendanceMarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        AttendanceMarkCommand attendanceMarkCommand = new AttendanceMarkCommand(outOfBoundIndex,
                validLessonIndex, emptyAttendance);

        assertCommandFailure(attendanceMarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidAttendanceList_failure() {
        AttendanceMarkCommand attendanceMarkCommand = new AttendanceMarkCommand(INDEX_FIRST_STUDENT,
                validLessonIndex, markedAttendance);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_ATTENDANCE_LIST_INDEX);
        assertThrows(CommandException.class, expectedMessage, ()
                -> attendanceMarkCommand.execute(model));
    }

    @Test
    public void execute_invalidLessonIndex_failure() {
        AttendanceMarkCommand attendanceMarkCommand = new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                invalidLessonIndex, markedAttendance);
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_ATTENDANCE_LIST_INDEX);
        assertThrows(CommandException.class, expectedMessage, ()
                -> attendanceMarkCommand.execute(model));
    }

    @Test
    public void equals() {
        final AttendanceMarkCommand standardCommand = new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                validLessonIndex, markedAttendance);
        AttendanceMarkCommand commandWithSameValues = new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                validLessonIndex, markedAttendance);

        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different student index -> returns false
        assertFalse(standardCommand.equals(new AttendanceMarkCommand(INDEX_FIRST_STUDENT,
                validLessonIndex, markedAttendance)));
        // different lesson index -> return false
        assertFalse(standardCommand.equals(new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                "2", markedAttendance)));

        // different markedAttendance -> return false
        assertFalse(standardCommand.equals(new AttendanceMarkCommand(INDEX_SECOND_STUDENT,
                validLessonIndex, emptyAttendance)));
    }
}
