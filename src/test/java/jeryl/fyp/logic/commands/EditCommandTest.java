package jeryl.fyp.logic.commands;

import static jeryl.fyp.logic.commands.CommandTestUtil.DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.DESC_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandFailure;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.logic.commands.CommandTestUtil.showStudentAtIndex;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static jeryl.fyp.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeryl.fyp.commons.core.Messages;
import jeryl.fyp.logic.commands.EditCommand.EditStudentDescriptor;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.testutil.EditStudentDescriptorBuilder;
import jeryl.fyp.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());

    private StudentId bobId = new StudentId(VALID_STUDENT_ID_BOB);

    private StudentId amyId = new StudentId(VALID_STUDENT_ID_AMY);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder()
                .withStudentId(model.getFilteredStudentList().get(0).getStudentId().toString()).build();
        StudentId validStudentId = editedStudent.getStudentId();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(editedStudent).build();

        EditCommand editCommand = new EditCommand(validStudentId, editStudentDescriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()),
                editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit)
                .withStudentName(VALID_STUDENT_NAME_BOB).withEmail(VALID_EMAIL_BOB).build();
        StudentId validStudentId = studentToEdit.getStudentId();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(editedStudent).build();

        EditCommand editCommand = new EditCommand(validStudentId, editStudentDescriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId validStudentId = studentToEdit.getStudentId();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        EditCommand editCommand = new EditCommand(validStudentId, editStudentDescriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, studentToEdit);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()),
                studentToEdit);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit)
                .withStudentName(VALID_STUDENT_NAME_BOB).withEmail(VALID_EMAIL_BOB).build();
        StudentId validStudentId = studentToEdit.getStudentId();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(editedStudent).build();

        EditCommand editCommand = new EditCommand(validStudentId, editStudentDescriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelManager expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentId validStudentId = model.getFilteredStudentList()
                .get(INDEX_SECOND_STUDENT.getZeroBased()).getStudentId();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(studentToEdit).build();
        EditCommand editCommand = new EditCommand(validStudentId, editStudentDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToEdit = model.getFypManager().getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        StudentId validStudentId = model.getFypManager().getStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()).getStudentId();
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder(studentToEdit).build();
        EditCommand editCommand = new EditCommand(validStudentId, editStudentDescriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(amyId, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_STUDENT_NOT_FOUND);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of FYP manager
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {

        EditCommand editCommand = new EditCommand(amyId,
                new EditStudentDescriptorBuilder().withStudentName(VALID_STUDENT_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(bobId, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(bobId, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // same studentId -> returns True
        assertTrue(standardCommand.equals(new EditCommand(bobId, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(bobId, DESC_BOB)));
    }

}
