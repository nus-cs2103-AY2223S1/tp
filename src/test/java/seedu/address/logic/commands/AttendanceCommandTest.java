//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
//import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
//import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
//import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.model.AddressBook;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.attendance.Attendance;
//import seedu.address.model.student.Student;
//import seedu.address.testutil.StudentBuilder;
//
//
//public class AttendanceCommandTest {
//
//    private static final String ATTENDANCE_STUB = "1";
//    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(), new UserPrefs());
//
//    //@Test
//    //public void execute_addAttendanceUnfilteredList_success() {
//    //    Student firstStudent = model.getFilteredStudentList()
//    //    .get(INDEX_FIRST_STUDENT.getZeroBased());
//    //    Student editedStudent = new StudentBuilder(firstStudent)
//    //            .withAttendance(ATTENDANCE_STUB).build();
//    //    AttendanceCommand remarkCommand = new AttendanceCommand(INDEX_FIRST_STUDENT,
//    //    new Attendance(editedStudent.getAttendance().value));
//
//    //    String expectedMessage = String.format(AttendanceCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS, editedStudent);
//
//    //    Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//    //    expectedModel.setStudent(firstStudent, editedStudent);
//    //    assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
//    //    }
//
//    @Test
//    public void execute_deleteAttendanceUnfilteredList_success() {
//        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
//        Student editedStudent = new StudentBuilder(firstStudent).withAttendance("0").build();
//
//        AttendanceCommand remarkCommand = new AttendanceCommand(INDEX_FIRST_STUDENT,
//                new Attendance(editedStudent.getAttendance().toString()));
//
//        String expectedMessage = String.format(AttendanceCommand.MESSAGE_DELETE_ATTENDANCE_SUCCESS, editedStudent);
//
//        Model expectedModel = new ModelManager(
//                new AddressBook(model.getAddressBook()), model.getTaskBook(), new UserPrefs());
//        expectedModel.setStudent(firstStudent, editedStudent);
//
//        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
//    }
//    @Test
//    public void execute_invalidStudentIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
//        AttendanceCommand remarkCommand = new AttendanceCommand(outOfBoundIndex
//        , new Attendance(VALID_ATTENDANCE_BOB));
//
//        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidStudentIndexFilteredList_failure() {
//        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
//        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());
//
//        AttendanceCommand remarkCommand = new AttendanceCommand(outOfBoundIndex
//        , new Attendance(VALID_ATTENDANCE_BOB));
//        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
//    }
//    @Test
//    public void equals() {
//        final AttendanceCommand standardCommand = new AttendanceCommand(INDEX_FIRST_STUDENT,
//                new Attendance(VALID_ATTENDANCE_AMY));
//
//        // same values -> returns true
//        AttendanceCommand commandWithSameValues = new AttendanceCommand(INDEX_FIRST_STUDENT,
//                new Attendance(VALID_ATTENDANCE_AMY));
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new AttendanceCommand(INDEX_SECOND_STUDENT,
//                new Attendance(VALID_ATTENDANCE_AMY))));
//
//        // different remark -> returns false
//        assertFalse(standardCommand.equals(new AttendanceCommand(INDEX_FIRST_STUDENT,
//                new Attendance(VALID_ATTENDANCE_BOB))));
//    }
//
//}
