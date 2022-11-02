package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_PLURAL_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_2;
import static seedu.address.testutil.TypicalTasks.TASK_3;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskDescriptionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskDescriptionContainsKeywordsPredicate firstPredicate =
                new TaskDescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        TaskDescriptionContainsKeywordsPredicate secondPredicate =
                new TaskDescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTaskCommand findFirstCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand findSecondCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTaskCommand findFirstCommandCopy = new FindTaskCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_PLURAL_OVERVIEW, 0);
        TaskDescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_PLURAL_OVERVIEW, 3);
        TaskDescriptionContainsKeywordsPredicate predicate = preparePredicate("assign 2102 2103");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TASK_1, TASK_2, TASK_3), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskDescriptionContainsKeywordsPredicate}.
     */
    private TaskDescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TaskDescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
