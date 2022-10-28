package seedu.watson.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_SUBJECTHANDLER;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.watson.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.watson.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.watson.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.watson.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.watson.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.watson.testutil.TypicalStudents.getTypicalDatabase;

import org.junit.jupiter.api.Test;

import seedu.watson.commons.core.Messages;
import seedu.watson.commons.core.index.Index;
import seedu.watson.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.watson.model.Database;
import seedu.watson.model.Model;
import seedu.watson.model.ModelManager;
import seedu.watson.model.UserPrefs;
import seedu.watson.model.student.Student;
import seedu.watson.testutil.EditStudentDescriptorBuilder;
import seedu.watson.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Student lastStudent = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        StudentBuilder personInList = new StudentBuilder(lastStudent);
        Student editedStudent = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                                          .withSubjectHandler(VALID_SUBJECTHANDLER)
                                          .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                                                                           .withPhone(VALID_PHONE_BOB)
                                                                           .withSubjectHandler(VALID_SUBJECTHANDLER)
                                                                           .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setPerson(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Student editedStudent = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB)
                                                                         .withSubjectHandler(VALID_SUBJECTHANDLER)
                                                                         .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                                                  new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                                                          .withSubjectHandler(VALID_SUBJECTHANDLER).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Student firstStudent = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit student in filtered list into a duplicate in watson book
        Student studentInList = model.getDatabase().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                                                  new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of watson book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of watson book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatabase().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                                                  new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
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
