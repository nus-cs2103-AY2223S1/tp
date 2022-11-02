package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTasksCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void equals() {

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTasksCommand findTaskFirstCommand = new FindTasksCommand(firstPredicate);
        FindTasksCommand findTaskSecondCommand = new FindTasksCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findTaskFirstCommand.equals(findTaskFirstCommand));

        // same values -> returns true
        FindTasksCommand findTaskFirstCommandCopy = new FindTasksCommand(firstPredicate);
        assertTrue(findTaskFirstCommand.equals(findTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(findTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findTaskFirstCommand.equals(findTaskSecondCommand));
    }

//
//    @Test
//    public void execute_zeroKeywords_noTaskFound() {
//        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
//        DescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindTasksCommand command = new FindTasksCommand(predicate);
//        expectedModel.updateFilteredTaskList(predicate);
//        System.out.println(command.execute(model).getFeedbackToUser());
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
//    }


    @Test
    public void executeFullWord_taskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);

        DescriptionContainsKeywordsPredicate predicate = preparePredicate("task one");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

    }

    @Test
    public void executePartialStartingWord_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Tas");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executePartialMiddleWord_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("as ");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeTaskWithAllLowerCase_taskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("HoMEWorK");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeTaskWithAllUpperCase_taskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Past YeaR");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeTaskWithAllUpperCase_taskFound2() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("past year");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void executeNoMatchingTask_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("ZXZ");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void executePartialWordWithSpacing_taskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("SK on");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.trim().toLowerCase()));
    }
}


