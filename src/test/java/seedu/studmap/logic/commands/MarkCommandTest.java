package seedu.studmap.logic.commands;

import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.AllIndexGenerator;
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
class MarkCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToMark = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T04", Attendance.Status.PRESENT);
        MarkCommand markCommand = new MarkCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new MarkCommand.MarkCommandStudentEditor(attendance));

        Student markedStudent = new StudentBuilder(studentToMark).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SINGLE_SUCCESS_ATTENDANCE,
                attendance.getString(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T04", Attendance.Status.PRESENT);
        MarkCommand markCommand = new MarkCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new MarkCommand.MarkCommandStudentEditor(attendance));

        Student markedStudent = new StudentBuilder(studentInFilteredList).addAttended("T04").build();

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SINGLE_SUCCESS_ATTENDANCE,
                attendance.getString(), markedStudent);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), markedStudent);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Attendance attendance = new Attendance("T04", Attendance.Status.PRESENT);
        MarkCommand markCommand = new MarkCommand(new SingleIndexGenerator(outOfBoundIndex),
                new MarkCommand.MarkCommandStudentEditor(attendance));

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_presentNoEdit_success() {

        // student not edited if already marked

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T62", Attendance.Status.PRESENT);
        Student studentToMark = new StudentBuilder(studentInFilteredList).addAttendances(attendance).build();
        model.setStudent(studentInFilteredList, studentToMark);

        MarkCommand markCommand = new MarkCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new MarkCommand.MarkCommandStudentEditor(attendance));
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SINGLE_UNEDITED_ATTENDANCE,
                attendance.getString(), studentToMark);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), studentToMark);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_absentNoEdit_success() {

        // student not edited if already marked

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Attendance attendance = new Attendance("T62", Attendance.Status.ABSENT);
        Student studentToMark = new StudentBuilder(studentInFilteredList).addAttendances(attendance).build();
        model.setStudent(studentInFilteredList, studentToMark);

        MarkCommand markCommand = new MarkCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new MarkCommand.MarkCommandStudentEditor(attendance));
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_SINGLE_UNEDITED_ATTENDANCE,
                attendance.getString(), studentToMark);

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), studentToMark);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_absentNoEditMulti_success() {

        // students not edited if already marked

        Attendance attendance = new Attendance("T62", Attendance.Status.ABSENT);
        model.getFilteredStudentList().forEach(x -> model.setStudent(x,
                new StudentBuilder(x).addAttendances(attendance).build()));

        MarkCommand markCommand = new MarkCommand(new AllIndexGenerator(),
                new MarkCommand.MarkCommandStudentEditor(attendance));
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_MULTI_UNEDITED_ATTENDANCE,
                model.getFilteredStudentList().size(), attendance.getString()).trim();

        ModelManager expectedModel = new ModelManager(model.getStudMap(), new UserPrefs());
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);

    }
}
