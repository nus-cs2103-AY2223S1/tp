package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EXAM;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EXAMONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EXAMTWO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertTasksHaveSameExamSuccess;
import static seedu.address.logic.commands.EditExamCommand.MESSAGE_EXAM_NOT_EDITED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FORTH_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTEENTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRTEENTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_TWELVE_TASK;
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
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FORTH_EXAM, descriptor);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceExam(model.getFilteredExamList().get(3), editedExam, false);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListWithoutAnyTaskLinked_success() {
        Exam editedExam = new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("CA PAPER"), model.getFilteredExamList().get(3).getExamDate());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FORTH_EXAM, descriptor);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceExam(model.getFilteredExamList().get(3), editedExam, false);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    public void linkHelper(Model model, Index examIndex, Index taskIndex) throws CommandException {
        LinkExamCommand linkExamCommand = new LinkExamCommand(examIndex, taskIndex);
        linkExamCommand.execute(model);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithoutTaskLinkedToTheSpecificExam_success()
            throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("Final Exam"), new ExamDate("01-11-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FORTH_EXAM, descriptor);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        Exam exam = model.getFilteredExamList().get(3);
        expectedModel.replaceExam(exam, editedExam, false);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    //tasks link to that exam but exam field edits only the description and date, module remains same.
    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithTasksLinkedToTheSpecificExam_success()
            throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2030S")),
                new ExamDescription("Final Exam"), new ExamDate("01-11-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_THIRD_EXAM, descriptor);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_FOURTEENTH_TASK);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_FOURTEENTH_TASK);
        Exam exam = model.getFilteredExamList().get(2);
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
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_THIRD_EXAM, descriptor);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_FOURTEENTH_TASK);
        linkHelper(model, INDEX_FORTH_EXAM, INDEX_THIRTEENTH_TASK);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS + "\n"
                + "Warning! All the tasks previously linked to this exam are now unlinked.", editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_FOURTEENTH_TASK);
        linkHelper(expectedModel, INDEX_FORTH_EXAM, INDEX_THIRTEENTH_TASK);
        Exam exam = model.getFilteredExamList().get(2);
        expectedModel.replaceExam(exam, editedExam, false);
        expectedModel.unlinkTasksFromExam(exam);
        expectedModel.updateExamFieldForTask(exam, editedExam);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_allFieldsChangeWithTasksLinkToExam_success() throws CommandException {
        Exam editedExam = new ExamBuilder(new Exam(new Module(new ModuleCode("CS2040s")),
                new ExamDescription("Midterm Paper"), new ExamDate("28-12-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_THIRD_EXAM, descriptor);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_FOURTEENTH_TASK);
        linkHelper(model, INDEX_FORTH_EXAM, INDEX_THIRTEENTH_TASK);
        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS + "\n"
                + "Warning! All the tasks previously linked to this exam are now unlinked.", editedExam);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_FOURTEENTH_TASK);
        linkHelper(expectedModel, INDEX_FORTH_EXAM, INDEX_THIRTEENTH_TASK);
        Exam exam = model.getFilteredExamList().get(2);
        expectedModel.replaceExam(exam, editedExam, false);
        expectedModel.unlinkTasksFromExam(exam);
        expectedModel.updateExamFieldForTask(exam, editedExam);
        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    //no tasks link to the exam but exam changed to be duplicate.
    @Test
    public void execute_duplicateExamUnfilteredList_failure() {
        Exam exam = model.getFilteredExamList().get(INDEX_THIRD_EXAM.getZeroBased());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FORTH_EXAM, descriptor);
        assertCommandFailure(editExamCommand, model, MESSAGE_DUPLICATE_EXAM);
    }

    @Test
    public void execute_duplicateExamUnfilteredListWithTasksLinked_failure() throws CommandException {
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        Exam exam = model.getFilteredExamList().get(INDEX_THIRD_EXAM.getZeroBased());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FORTH_EXAM, descriptor);
        assertCommandFailure(editExamCommand, model, MESSAGE_DUPLICATE_EXAM);
        assertTrue(model.getFilteredTaskList().get(11).getExam().isSameExam(exam));
    }

    @Test
    public void execute_editExamToANonExistingModule_failure() throws CommandException {
        Exam exam = new ExamBuilder(new Exam(new Module(new ModuleCode("XX2000s")),
                new ExamDescription("Midterm Paper"), new ExamDate("28-12-2023"))).build();
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_THIRD_EXAM, descriptor);
        linkHelper(model, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        linkHelper(expectedModel, INDEX_THIRD_EXAM, INDEX_TWELVE_TASK);
        String expectedMessage = "This module does not exist";
        assertThrows(CommandException.class, expectedMessage, () -> editExamCommand.execute(model));
        assertEquals(expectedModel, model);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    //no fields change
    @Test
    public void execute_noFieldChangeUnfilteredList_failure() {
        Exam exam = model.getFilteredExamList().get(INDEX_THIRD_EXAM.getZeroBased());
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder(exam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_THIRD_EXAM, descriptor);
        assertCommandFailure(editExamCommand, model, MESSAGE_EXAM_NOT_EDITED);
    }

    @Test
    public void execute_invalidExamIndexUnfilteredList_failure() {
        int index = model.getFilteredExamList().size() + 1;
        Index outOfBoundIndex = Index.fromOneBased(index);
        EditExamCommand.EditExamDescriptor descriptor =
                new EditExamDescriptorBuilder().withDescription("Final exam paper").build();
        EditExamCommand editExamCommand = new EditExamCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editExamCommand, model,
                String.format(Messages.MESSAGE_INVALID_EXAM_INDEX_TOO_LARGE, index));
    }

    @Test
    public void equals() {
        final EditExamCommand standardCommand =
                new EditExamCommand(INDEX_FIRST_EXAM, DESC_EXAMONE);

        // same values -> returns true
        EditExamCommand.EditExamDescriptor copyDescriptor =
                new EditExamCommand.EditExamDescriptor(DESC_EXAMONE);
        EditExamCommand commandWithSameValues =
                new EditExamCommand(INDEX_FIRST_EXAM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAllCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(INDEX_SECOND_EXAM, DESC_EXAMONE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(INDEX_FIRST_EXAM, DESC_EXAMTWO)));
    }
}
