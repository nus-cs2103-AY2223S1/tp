package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToMark = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_STUDENT, attendance);

        Student markedStudent = new StudentBuilder(studentToMark).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SUCCESS,
                attendance.getAttendance(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_STUDENT, attendance);

        Student markedStudent = new StudentBuilder(studentInFilteredList).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SUCCESS,
                attendance.getAttendance(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Attendance attendance = new Attendance("T04", true);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, attendance);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
