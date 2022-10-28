package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.Assert;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTasksCommandTest {
    // private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    //private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model model = new ModelManager(getTypicalAddressBookForTask(), new UserPrefs());
 //   private Model expectedModel = model;
    private Model expectedModel = new ModelManager(getTypicalAddressBookForTask(), new UserPrefs());
   // private Model expectedModel = new ModelManager((model.getAddressBook()), new UserPrefs());

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

        // different person -> returns false
        assertFalse(findTaskFirstCommand.equals(findTaskSecondCommand));
    }


//    @Test
//    public void execute_zeroKeywords_noTaskFound() {
//        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
//        DescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindTasksCommand command = new FindTasksCommand(predicate);
//        expectedModel.updateFilteredTaskList(predicate);
//        System.out.println(command.execute(model).getFeedbackToUser());
//        assertEquals(command.execute(model), new CommandResult(expectedMessage));
//        assertTrue(model.equals(expectedModel));
//        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        //assertEquals(Collections.emptyList(), model.getFilteredTaskList());
//    }

//    @Test
//    public void t() {
//         Model model = new ModelManager(getTypicalAddressBookForTask(), new UserPrefs());
//         Model expectedModel = new ModelManager(getTypicalAddressBookForTask(), new UserPrefs());
//
//        System.out.println(model.equals(expectedModel));
//        assertTrue(model.equals(expectedModel));
//        //assertEquals
//      //  assertEquals(model, expectedModel);
//    }
    @Test
    public void executeFullWord_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);

        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Task one");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
      //  assertEquals(command.execute(model), new CommandResult(expectedMessage));
       // assertTrue(model.equals(expectedModel));
       // assertEquals(Arrays.asList(TASKONE), model.getFilteredTaskList());

      //  assertEquals(model, expectedModel);
        //  assertCommandSuccess(command, model, new CommandResult(expectedMessage), expectedModel);
       // System.out.println(command);
          System.out.println(new FindTasksCommand(predicate).execute(model).getFeedbackToUser());
         System.out.println(String.format(MESSAGE_TASKS_LISTED_OVERVIEW,0));

        assertCommandSuccess(command, model,
                String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0), expectedModel);
    }

    @Test
    public void executePartialStartingWord_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);

        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Tas");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        System.out.println(new FindTasksCommand(predicate).execute(model).getFeedbackToUser());
        System.out.println(String.format(MESSAGE_TASKS_LISTED_OVERVIEW,2));
        //assertCommandSuccess(command, model,
          //      String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2), expectedModel);
       // assertEquals(command.execute(model), new CommandResult(expectedMessage));
       // assertTrue(model.equals(expectedModel));
       // assertEquals(Arrays.asList(TASKONE, TASKTWO), model.getFilteredTaskList());
    }

    @Test
    public void executePartialMiddleWord_tasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);

        DescriptionContainsKeywordsPredicate predicate = preparePredicate("as ");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
        assertTrue(model.equals(expectedModel));
        assertEquals(Arrays.asList(TASKONE, TASKTWO), model.getFilteredTaskList());
        assertCommandSuccess(command, model,
                      String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2), expectedModel);
    }

    @Test
    public void executeNoMatchingTask_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);

        DescriptionContainsKeywordsPredicate predicate = preparePredicate("HOMEWORK");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
        assertTrue(model.equals(expectedModel));
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void executePartialWordWithSpacing_taskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);

        DescriptionContainsKeywordsPredicate predicate = preparePredicate("SK on");
        FindTasksCommand command = new FindTasksCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        command.execute(model);
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
        assertTrue(model.equals(expectedModel));
        assertEquals(Arrays.asList(TASKONE), model.getFilteredTaskList());
    }









    //multiple found

    //entered a keyword but not found..

    //found whole word

    //found partial word at the front

    //found partial word at the back\


    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.trim().toLowerCase()));
    }
}


