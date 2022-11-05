package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_CS2040_FINAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_LINKED_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.task.Task;

public class LinkExamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullExamIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkExamCommand(null,
                INDEX_FIRST_TASK));
    }

    @Test
    public void constructor_nullTaskIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkExamCommand(INDEX_FIRST_EXAM,
                null));
    }

    @Test
    public void execute_validTaskIndexValidExamIndexUnfilteredList_success() {
        Task task = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        Exam exam = model.getFilteredExamList().get(INDEX_FIRST_EXAM.getZeroBased());

        LinkExamCommand linkExamCommand = new LinkExamCommand(INDEX_FIRST_EXAM, INDEX_THIRD_TASK);
        String expectedMessage = LinkExamCommand.EXAM_LINKED_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task linkedTask = task.linkTask(exam);
        expectedModel.replaceTask(task, linkedTask, true);

        assertCommandSuccess(linkExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexValidExamIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsTaskIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        LinkExamCommand linkExamCommand = new LinkExamCommand(INDEX_FIRST_EXAM, outOfBoundsTaskIndex);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(linkExamCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_validTaskIndexInvalidExamIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsExamIndex = Index.fromOneBased(model.getFilteredExamList().size() + 1);
        LinkExamCommand linkExamCommand = new LinkExamCommand(outOfBoundsExamIndex, INDEX_FIRST_TASK);
        String expectedFailureMessage = Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX;
        assertCommandFailure(linkExamCommand, model, expectedFailureMessage);
    }

    @Test
    public void execute_taskAlreadyLinked_throwsCommandException() {
        String expectedMessage = LinkExamCommand.TASK_ALREADY_LINKED;
        LinkExamCommand linkExamCommand = new LinkExamCommand(INDEX_CS2040_FINAL, INDEX_LINKED_TASK);
        assertCommandFailure(linkExamCommand, model, expectedMessage);
    }

    @Test
    public void execute_taskDifferentModuleCodeFromExam_throwsCommandException() {
        String expectedMessage = LinkExamCommand.DIFFERENT_MODULE_CODE;
        LinkExamCommand linkExamCommand = new LinkExamCommand(INDEX_CS2040_FINAL, INDEX_FIRST_TASK);
        assertCommandFailure(linkExamCommand, model, expectedMessage);
    }

    @Test
    public void execute_validTaskIndexValidExamIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_THIRD_TASK);
        Task task = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Exam exam = model.getFilteredExamList().get(INDEX_FIRST_EXAM.getZeroBased());

        LinkExamCommand linkExamCommand = new LinkExamCommand(INDEX_FIRST_EXAM, INDEX_FIRST_TASK);
        String expectedMessage = LinkExamCommand.EXAM_LINKED_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task linkedTask = task.linkTask(exam);
        expectedModel.replaceTask(task, linkedTask, true);

        assertCommandSuccess(linkExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexValidExamIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_THIRD_TASK);
        Index outOfBoundsTaskIndex = INDEX_SECOND_TASK;
        //Checks that out of bounds index is still within the range of the address book task list
        assertTrue(outOfBoundsTaskIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        LinkExamCommand linkExamCommand = new LinkExamCommand(INDEX_FIRST_EXAM, outOfBoundsTaskIndex);
        String expectedMessage = Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        assertCommandFailure(linkExamCommand, model, expectedMessage);
    }

    @Test
    public void testEquals() {
        Index examIndex = INDEX_FIRST_EXAM;
        Index taskIndex = INDEX_FIRST_TASK;
        Index examIndexClone = Index.fromOneBased(examIndex.getOneBased());
        Index taskIndexClone = Index.fromOneBased(taskIndex.getOneBased());

        LinkExamCommand linkExamCommand = new LinkExamCommand(examIndex, taskIndex);
        LinkExamCommand linkExamCommandClone = new LinkExamCommand(examIndexClone, taskIndexClone);
        LinkExamCommand linkExamCommandDifferentExamIndex = new LinkExamCommand(INDEX_SECOND_EXAM, INDEX_FIRST_TASK);
        LinkExamCommand linkExamCommandDifferentTaskIndex = new LinkExamCommand(INDEX_FIRST_EXAM, INDEX_SECOND_TASK);

        //Same LinkExamCommand object
        assertTrue(linkExamCommand.equals(linkExamCommand));

        //Different LinkExamCommand object with same task and exam index
        assertTrue(linkExamCommand.equals(linkExamCommandClone));

        //Null value
        assertFalse(linkExamCommand.equals(null));

        //Different LinkExamCommand object with different exam index
        assertFalse(linkExamCommand.equals(linkExamCommandDifferentExamIndex));

        //Different LinkExamCommand object with different task index
        assertFalse(linkExamCommand.equals(linkExamCommandDifferentTaskIndex));

        //Different object type
        assertFalse(linkExamCommand.equals(7382));
    }


}
