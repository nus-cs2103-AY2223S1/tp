package seedu.codeconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codeconnect.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeconnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeconnect.testutil.TypicalTasks.FINISH_TUTORIAL;
import static seedu.codeconnect.testutil.TypicalTasks.WATCH_LECTURE;
import static seedu.codeconnect.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.ModelManager;
import seedu.codeconnect.model.UserPrefs;
import seedu.codeconnect.model.task.TaskContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList("first"));
        TaskContainsKeywordsPredicate secondPredicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Tutorial lecture");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(WATCH_LECTURE, FINISH_TUTORIAL), model.getSortedTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TaskContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TaskContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
