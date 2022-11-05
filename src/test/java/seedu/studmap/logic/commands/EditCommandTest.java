package seedu.studmap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.studmap.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.studmap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.studmap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.studmap.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.studmap.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import org.junit.jupiter.api.Test;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.logic.commands.EditCommand.EditCommandStudentEditor;
import seedu.studmap.model.Model;
import seedu.studmap.model.ModelManager;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.UserPrefs;
import seedu.studmap.model.student.Student;
import seedu.studmap.testutil.EditStudentDescriptorBuilder;
import seedu.studmap.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalStudMap(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), editor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLaststudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLaststudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(indexLaststudent), editor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new EditCommandStudentEditor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = EditCommand.MESSAGE_NOT_EDITED;

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new StudMap(model.getStudMap()), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatestudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(INDEX_SECOND_STUDENT),
                editor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicatestudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in student map
        Student studentInList = model.getStudMap().getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT),
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidstudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditCommandStudentEditor editor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(outOfBoundIndex), editor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of student map
     */
    @Test
    public void execute_invalidstudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of student map list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudMap().getStudentList().size());

        EditCommand editCommand = new EditCommand(new SingleIndexGenerator(outOfBoundIndex),
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand =
                new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), DESC_AMY);

        // same values -> returns true
        EditCommandStudentEditor copyDescriptor = new EditCommandStudentEditor(DESC_AMY);
        EditCommand commandWithSameValues =
                new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new SingleIndexGenerator(INDEX_SECOND_STUDENT), DESC_AMY)));

        // different editor -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new SingleIndexGenerator(INDEX_FIRST_STUDENT), DESC_BOB)));
    }

}
