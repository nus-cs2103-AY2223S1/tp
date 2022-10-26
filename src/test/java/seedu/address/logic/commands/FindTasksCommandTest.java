//package seedu.address.logic.commands;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
//
///**
// * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
// */
//public class FindTasksCommandTest {
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void equals() {
//
//        DescriptionContainsKeywordsPredicate firstPredicate =
//                new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
//        DescriptionContainsKeywordsPredicate secondPredicate =
//                new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));
//
//        FindTasksCommand findTaskFirstCommand = new FindTasksCommand(firstPredicate);
//        FindTasksCommand findTaskSecondCommand = new FindTasksCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(findTaskFirstCommand.equals(findTaskFirstCommand));
//
//        // same values -> returns true
//        FindTasksCommand findTaskFirstCommandCopy = new FindTasksCommand(firstPredicate);
//        assertTrue(findTaskFirstCommand.equals(findTaskFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findTaskFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findTaskFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(findTaskFirstCommand.equals(findTaskSecondCommand));
//    }
//
//
//    @Test
//    public void execute_zeroKeywords_noTaskFound() {
//        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
//        DescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
//        FindTasksCommand command = new FindTasksCommand(predicate);
//        expectedModel.updateFilteredTaskList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
//     */
//    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
//    }
//}
