package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.address.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.address.testutil.TypicalTasks.TASK_D;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.task.TaskLinkedToExamPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ListExamTasksCommand}.
 */
public class ListExamTasksCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListMultipleTasksFound_success() {
        Exam examToListTasks = model.getFilteredExamList().get(FIRST_INDEX.getZeroBased());
        String expectedMessage = String.format(ListExamTasksCommand.MESSAGE_SUCCESS, examToListTasks);
        TaskLinkedToExamPredicate predicate = preparePredicate(examToListTasks);

        ListExamTasksCommand command = new ListExamTasksCommand(FIRST_INDEX);
        expectedModel.updateFilteredTaskList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TASK_D), model.getFilteredTaskList());
    }

    @Test
    public void execute_validIndexUnfilteredListNoTasksFound_success() {
        Exam examToListTasks = model.getFilteredExamList().get(SECOND_INDEX.getZeroBased());
        String expectedMessage = String.format(ListExamTasksCommand.MESSAGE_NO_RESULTS, examToListTasks);
        TaskLinkedToExamPredicate predicate = preparePredicate(examToListTasks);

        ListExamTasksCommand command = new ListExamTasksCommand(SECOND_INDEX);
        expectedModel.updateFilteredTaskList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, model.getFilteredTaskList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExamList().size() + 1);
        ListExamTasksCommand listExamTasksCommand = new ListExamTasksCommand(outOfBoundIndex);

        assertCommandFailure(listExamTasksCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ListExamTasksCommand listFirstExamTasksCommand = new ListExamTasksCommand(FIRST_INDEX);
        ListExamTasksCommand listSecondExamTasksCommand = new ListExamTasksCommand(SECOND_INDEX);

        // same object -> returns true
        assertTrue(listFirstExamTasksCommand.equals(listFirstExamTasksCommand));

        // same values -> returns true
        ListExamTasksCommand listFirstExamTasksCommandCopy = new ListExamTasksCommand(FIRST_INDEX);
        assertTrue(listFirstExamTasksCommand.equals(listFirstExamTasksCommandCopy));

        // different types -> returns false
        assertFalse(listFirstExamTasksCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstExamTasksCommand.equals(null));

        // different exam -> returns false
        assertFalse(listFirstExamTasksCommand.equals(listSecondExamTasksCommand));
    }

    /**
     * Parses {@code userInput} into a {@code TaskLinkedToExamPredicate}.
     */
    private TaskLinkedToExamPredicate preparePredicate(Exam exam) {
        return new TaskLinkedToExamPredicate(exam);
    }
}
