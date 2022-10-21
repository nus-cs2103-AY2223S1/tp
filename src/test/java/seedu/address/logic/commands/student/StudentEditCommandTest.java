package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STUDENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STUDENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.student.StudentEditCommand.EditStudentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class StudentEditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        StudentEditCommand studentEditCommand = new StudentEditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(StudentEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(studentEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder personInList = new StudentBuilder(lastStudent);
        Student editedStudent = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        StudentEditCommand editCommand = new StudentEditCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(StudentEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        StudentEditCommand editCommand = new StudentEditCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(StudentEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        StudentEditCommand editCommand = new StudentEditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(StudentEditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        StudentEditCommand editCommand = new StudentEditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, StudentEditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Student studentInList = model.getAddressBook().getStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        StudentEditCommand editCommand = new StudentEditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, StudentEditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        StudentEditCommand editCommand = new StudentEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        StudentEditCommand editCommand = new StudentEditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final StudentEditCommand standardCommand = new StudentEditCommand(INDEX_FIRST_PERSON, DESC_STUDENT_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_STUDENT_AMY);
        StudentEditCommand commandWithSameValues = new StudentEditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new StudentEditCommand(INDEX_SECOND_PERSON, DESC_STUDENT_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new StudentEditCommand(INDEX_FIRST_PERSON, DESC_STUDENT_BOB)));
    }

}
