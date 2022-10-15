package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

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
class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToUnmark = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T01", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_SECOND_STUDENT, attendance);

        Set<Attendance> attendanceSet = new HashSet<>(studentToUnmark.getAttendances());
        attendanceSet.remove(attendance);
        Student unmarkedStudent = new StudentBuilder(studentToUnmark).setAttended(attendanceSet).build();

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_SUCCESS,
                attendance.className, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList()
                .get(INDEX_SECOND_STUDENT.getZeroBased()), unmarkedStudent);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(0);
        Attendance attendance = new Attendance("T01", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(INDEX_FIRST_STUDENT, attendance);

        Set<Attendance> attendanceSet = new HashSet<>(studentInFilteredList.getAttendances());
        attendanceSet.remove(attendance);
        Student unmarkedStudent = new StudentBuilder(studentInFilteredList).setAttended(attendanceSet).build();

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_SUCCESS,
                attendance.className, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), unmarkedStudent);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Attendance attendance = new Attendance("T04", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndex, attendance);

        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
