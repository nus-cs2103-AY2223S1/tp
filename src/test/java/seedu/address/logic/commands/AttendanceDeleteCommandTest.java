package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalToDos.getTypicalTaskBookWithToDos;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

class AttendanceDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBookWithToDos(), new UserPrefs());

    @Test
    public void execute_deleteAttendanceUnfilteredList_success() {
        Student secondStudent = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(secondStudent).withAttendanceList("NA", "0").build();
        AttendanceDeleteCommand attendanceDeleteCommand = new AttendanceDeleteCommand(INDEX_SECOND_STUDENT);

        String expectedMessage = String.format(AttendanceDeleteCommand.MESSAGE_DELETE_ATTENDANCE_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                model.getTaskBook(), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudent);

        assertCommandSuccess(attendanceDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AttendanceDeleteCommand attendanceDeleteCommand = new AttendanceDeleteCommand(outOfBoundIndex);

        assertCommandFailure(attendanceDeleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        AttendanceDeleteCommand attendanceDeleteCommand = new AttendanceDeleteCommand(outOfBoundIndex);

        assertCommandFailure(attendanceDeleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        final AttendanceDeleteCommand standardCommand = new AttendanceDeleteCommand(INDEX_FIRST_STUDENT);
        AttendanceDeleteCommand commandWithSameValues = new AttendanceDeleteCommand(INDEX_FIRST_STUDENT);

        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AttendanceDeleteCommand(INDEX_SECOND_STUDENT)));
    }
}
