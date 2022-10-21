package seedu.classify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_EXAM_1;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.classify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.classify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.classify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.classify.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.classify.commons.core.Messages;
import seedu.classify.commons.core.index.Index;
import seedu.classify.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.StudentRecord;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Student;
import seedu.classify.testutil.EditStudentDescriptorBuilder;
import seedu.classify.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedPerson = new StudentBuilder().withExams("CA2 90", "SA1 60").build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedPerson);
        Model expectedModel = new ModelManager(new StudentRecord(model.getStudentRecord()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastPerson = model.getFilteredStudentList().get(indexLastPerson.getZeroBased());

        StudentBuilder personInList = new StudentBuilder(lastPerson);
        Student editedPerson = personInList.withStudentName(VALID_STUDENT_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withExams(VALID_EXAM_1).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentName(VALID_STUDENT_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withExams(VALID_EXAM_1).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new StudentRecord(model.getStudentRecord()), new UserPrefs());
        expectedModel.setStudent(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedPerson = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new StudentRecord(model.getStudentRecord()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student personInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedPerson = new StudentBuilder(personInFilteredList).withStudentName(VALID_STUDENT_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder().withStudentName(VALID_STUDENT_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new StudentRecord(model.getStudentRecord()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Student firstPerson = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Student personInList = model.getStudentRecord().getStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentRecord().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withStudentName(VALID_STUDENT_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
