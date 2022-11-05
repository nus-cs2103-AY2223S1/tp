package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Attendance;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkCommand}.
 */
class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToUnmark = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T01", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(new SingleIndexGenerator(INDEX_SECOND_STUDENT),
                new UnmarkCommand.UnmarkCommandStudentEditor(attendance));

        Set<Attendance> attendanceSet = new HashSet<>(studentToUnmark.getAttendances());
        attendanceSet.remove(attendance);
        Student unmarkedStudent = new StudentBuilder(studentToUnmark).setAttended(attendanceSet).build();

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_SINGLE_ATTENDANCE_SUCCESS,
                attendance.className, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList()
                .get(INDEX_SECOND_STUDENT.getZeroBased()), unmarkedStudent);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_SECOND_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(0);
        Attendance attendance = new Attendance("T01", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new UnmarkCommand.UnmarkCommandStudentEditor(attendance));

        Set<Attendance> attendanceSet = new HashSet<>(studentInFilteredList.getAttendances());
        attendanceSet.remove(attendance);
        Student unmarkedStudent = new StudentBuilder(studentInFilteredList).setAttended(attendanceSet).build();

        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_SINGLE_ATTENDANCE_SUCCESS,
                attendance.className, unmarkedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_SECOND_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), unmarkedStudent);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Attendance attendance = new Attendance("T04", true);
        UnmarkCommand unmarkCommand = new UnmarkCommand(new SingleIndexGenerator(outOfBoundIndex),
                new UnmarkCommand.UnmarkCommandStudentEditor(attendance));

        assertCommandFailure(unmarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
