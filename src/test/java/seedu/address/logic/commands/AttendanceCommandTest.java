package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AttendanceCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.Attendance;


public class AttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Attendance attendance = new Attendance("attendance");

        assertCommandFailure(new AttendanceCommand(INDEX_FIRST_STUDENT, attendance), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_STUDENT.getOneBased(), attendance));
    }


    @Test
    public void equals() {
        final AttendanceCommand standardCommand = new AttendanceCommand(INDEX_FIRST_STUDENT, new Attendance(VALID_REMARK_AMY));

        // same values -> returns true
        AttendanceCommand commandWithSameValues = new AttendanceCommand(INDEX_FIRST_STUDENT, new Attendance(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AttendanceCommand(INDEX_SECOND_STUDENT, new Attendance(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AttendanceCommand(INDEX_FIRST_STUDENT, new Attendance(VALID_REMARK_BOB))));
    }
}
