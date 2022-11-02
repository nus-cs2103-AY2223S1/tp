package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.EditExamCommand.MESSAGE_EXAM_NOT_EDITED;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalExams.getTypicalAddressBookForExam;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditExamDescriptorBuilder;
import seedu.address.testutil.ExamBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditExamCommand.
 */
public class EditExamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithoutAnyTaskLinked_success() {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("Final Exam"), new ExamDate("01-11-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_SECOND, descriptor);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceExam(model.getFilteredExamList().get(1), editedExam, false);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithoutAnyTaskLinked2_success() {
        Exam editedExam = new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("CA PAPER"), model.getFilteredExamList().get(1).getExamDate());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_SECOND, descriptor);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceExam(model.getFilteredExamList().get(1), editedExam, false);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    public void linkHelper(Model model, Index examIndex, Index taskIndex) throws CommandException {
        LinkExamCommand linkExamCommand = new LinkExamCommand(examIndex, taskIndex);
        linkExamCommand.execute(model);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithoutTaskLinkedToTheSpecificExam_success() throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("Final Exam"), new ExamDate("01-11-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_SECOND, descriptor);
        linkHelper(model, INDEX_FIRST, INDEX_THIRD);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_FIRST, INDEX_THIRD);
        Exam exam = model.getFilteredExamList().get(1);
        expectedModel.replaceExam(exam, editedExam, false);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    //tasks link to that exam but exam field edits only the description and date, module remains same.
    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithTasksLinkedToTheSpecificExam_success() throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("Final Exam"), new ExamDate("01-11-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST, descriptor);
        linkHelper(model, INDEX_FIRST, INDEX_THIRD);
        linkHelper(model, INDEX_FIRST, INDEX_FIFTH);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_FIRST, INDEX_THIRD);
        linkHelper(expectedModel, INDEX_FIRST, INDEX_FIFTH);
        Exam exam = model.getFilteredExamList().get(0);
        expectedModel.replaceExam(exam, editedExam, false);
        expectedModel.updateExamFieldForTask(exam, editedExam);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }


    @Test
    public void execute_changeModuleWithTasksLinkToExam_success() throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2040s")),
                new ExamDescription("Exam one"), new ExamDate("20-08-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST, descriptor);
        linkHelper(model, INDEX_FIRST, INDEX_THIRD);
        linkHelper(model, INDEX_FIRST, INDEX_FIFTH);
        linkHelper(model, INDEX_SECOND, INDEX_FOURTH);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS + "\n"
                + "Warning! All the tasks previously linked to this exam are now unlinked.", editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_FIRST, INDEX_THIRD);
        linkHelper(expectedModel, INDEX_FIRST, INDEX_FIFTH);
        linkHelper(expectedModel, INDEX_SECOND, INDEX_FOURTH);
        Exam exam = model.getFilteredExamList().get(0);
        expectedModel.replaceExam(exam, editedExam, false);
        expectedModel.unlinkTasksFromExam(exam);
        expectedModel.updateExamFieldForTask(exam, editedExam);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_allFieldsChangeAndLinkToExam_success() throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2040s")),
                new ExamDescription("Midterm Paper"), new ExamDate("28-12-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST, descriptor);
        linkHelper(model, INDEX_FIRST, INDEX_THIRD);
        linkHelper(model, INDEX_FIRST, INDEX_FIFTH);
        linkHelper(model, INDEX_SECOND, INDEX_FOURTH);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS + "\n"
                + "Warning! All the tasks previously linked to this exam are now unlinked.", editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_FIRST, INDEX_THIRD);
        linkHelper(expectedModel, INDEX_FIRST, INDEX_FIFTH);
        linkHelper(expectedModel, INDEX_SECOND, INDEX_FOURTH);
        Exam exam = model.getFilteredExamList().get(0);
        expectedModel.replaceExam(exam, editedExam, false);
        expectedModel.unlinkTasksFromExam(exam);
        expectedModel.updateExamFieldForTask(exam, editedExam);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    //no tasks link to the exam but exam changed to be duplicate.
    @Test
    public void execute_duplicateExamUnfilteredList_failure() {
        Exam exam = model.getFilteredExamList().get(INDEX_FIRST.getZeroBased());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_SECOND, descriptor);
        assertCommandFailure(editExamCommand, model, MESSAGE_DUPLICATE_EXAM);
    }

    @Test
    public void execute_duplicateExamUnfilteredListWithTasksLinked_failure() throws CommandException {
        linkHelper(model, INDEX_FIRST, INDEX_THIRD);
        Exam exam = model.getFilteredExamList().get(INDEX_FIRST.getZeroBased());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_SECOND, descriptor);
        assertCommandFailure(editExamCommand, model, MESSAGE_DUPLICATE_EXAM);
        assertTrue(model.getFilteredTaskList().get(2).getExam().isSameExam(exam));
    }

    @Test
    public void execute_EditExamToANonExistingModule_failure() throws CommandException {
        Exam exam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2000s")),
                new ExamDescription("Midterm Paper"), new ExamDate("28-12-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST, descriptor);
        linkHelper(model, INDEX_FIRST, INDEX_THIRD);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_FIRST, INDEX_THIRD);
        String expectedMessage = "This module does not exist";
        assertThrows(CommandException.class, expectedMessage, () -> editExamCommand.execute(model));
        assertEquals(expectedModel, model);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    //no fields change
    @Test
    public void execute_noFieldChangeUnfilteredList_failure() {
        Exam exam = model.getFilteredExamList().get(INDEX_FIRST.getZeroBased());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST, descriptor);
        assertCommandFailure(editExamCommand, model, MESSAGE_EXAM_NOT_EDITED);
    }

    @Test
    public void execute_invalidExamIndexUnfilteredList_failure() {
        int index = model.getFilteredExamList().size() + 1;
        Index outOfBoundIndex = Index.fromOneBased(index);
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withDescription("Final exam paper").build();
        EditExamCommand editExamCommand = new EditExamCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editExamCommand, model, String.format(Messages.MESSAGE_INVALID_EXAM_INDEX_TOO_LARGE, index));
    }

    @Test
    public void equals() {
        final EditExamCommand standardCommand = new EditExamCommand(INDEX_FIRST, DESC_EXAMONE);

        // same values -> returns true
        EditExamCommand.EditExamDescriptor copyDescriptor = new EditExamCommand.EditExamDescriptor(DESC_EXAMONE);
        EditExamCommand commandWithSameValues = new EditExamCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAllCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(INDEX_SECOND, DESC_EXAMONE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(INDEX_FIRST, DESC_EXAMTWO)));
    }
}
