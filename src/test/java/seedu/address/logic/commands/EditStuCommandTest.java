package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalProfNus;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStuCommand.EditStudentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProfNus;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditStuCommand.
 */
public class EditStuCommandTest {

    private Model model = new ModelManager(getTypicalProfNus(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_PERSON, descriptor);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedStudent);

        assertCommandSuccess(editStuCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastStudent = model.getFilteredPersonList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withTelegramHandle("@bobtheman")
                .withStudentInfo("CS1101S").build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegramHandle("@bobtheman")
                .withStudentModuleInfo("CS1101S").build();
        EditStuCommand editStuCommand = new EditStuCommand(indexLastStudent, descriptor);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());
        expectedModel.setPerson(lastStudent, editedStudent);

        assertCommandSuccess(editStuCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedStudent = (Student) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());

        assertCommandSuccess(editStuCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(personInFilteredList).withTelegramHandle(VALID_TELEGRAM_BOB).build();
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder().withTelegramHandle(VALID_TELEGRAM_BOB).build());

        CommandResult expectedCommandResult = new CommandResult(
                String.format(EditStuCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent),
                false, false, false,
                true, false, false, false, false, false);

        Model expectedModel = new ModelManager(new ProfNus(model.getProfNus()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedStudent);

        assertCommandSuccess(editStuCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = (Student) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editStuCommand, model, EditStuCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in profNus
        Student personInList = (Student) model.getProfNus().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditStuCommand editStuCommand = new EditStuCommand(INDEX_FIRST_PERSON,
                new EditStudentDescriptorBuilder(personInList).build());

        assertCommandFailure(editStuCommand, model, EditStuCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditStuCommand editStuCommand = new EditStuCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editStuCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of profNus
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of profNus list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProfNus().getPersonList().size());

        EditStuCommand editStuCommand = new EditStuCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editStuCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStuCommand standardCommand = new EditStuCommand(INDEX_FIRST_PERSON, DESC_AMY_STUDENT);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY_STUDENT);
        EditStuCommand commandWithSameValues = new EditStuCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStuCommand(INDEX_SECOND_PERSON, DESC_AMY_STUDENT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStuCommand(INDEX_FIRST_PERSON, DESC_BOB_STUDENT)));
    }

}

