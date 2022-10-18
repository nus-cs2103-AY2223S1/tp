package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STU_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STU_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditStuCommand.
 */
public class EditStuCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAttendance(VALID_ATTENDANCE_BOB).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB).withAttendance(VALID_ATTENDANCE_BOB).build();
        EditStuCommand editStuCommand = new EditStuCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editStuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withAttendance(VALID_ATTENDANCE_BOB).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withAttendance(VALID_ATTENDANCE_BOB).build();
        EditStuCommand editStuCommand = new EditStuCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editStuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_STUDENT, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editStuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editStuCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_SECOND_STUDENT, descriptor);

        assertCommandFailure(editStuCommand, model, EditStuCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in address book
        Student studentInList = model.getAddressBook().getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editStuCommand, model, EditStuCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditStuCommand editStuCommand = new EditStuCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editStuCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        EditStuCommand editStuCommand = new EditStuCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editStuCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStuCommand standardCommand = new EditStuCommand(INDEX_FIRST_STUDENT, DESC_STU_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_STU_AMY);
        EditStuCommand commandWithSameValues = new EditStuCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStuCommand(INDEX_SECOND_STUDENT, DESC_STU_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStuCommand(INDEX_FIRST_STUDENT, DESC_STU_BOB)));
    }

}
