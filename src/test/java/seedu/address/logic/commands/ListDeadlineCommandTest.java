package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListDeadlineCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskByDeadlinePredicate;

public class ListDeadlineCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskByDeadlinePredicate firstPredicate =
                new TaskByDeadlinePredicate(Collections.singletonList("first"));
        TaskByDeadlinePredicate secondPredicate =
                new TaskByDeadlinePredicate(Collections.singletonList("second"));

        ListDeadlineCommand listFirstDeadlineCommand = new ListDeadlineCommand(firstPredicate);
        ListDeadlineCommand listSecondDeadlineCommand = new ListDeadlineCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listFirstDeadlineCommand.equals(listFirstDeadlineCommand));

        // same values -> returns true
        ListDeadlineCommand findFirstCommandCopy = new ListDeadlineCommand(firstPredicate);
        assertTrue(listFirstDeadlineCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstDeadlineCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstDeadlineCommand.equals(null));

        // different person -> returns false
        assertFalse(listFirstDeadlineCommand.equals(listSecondDeadlineCommand));
    }

    @Test
    public void execute_dateAbsent_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TaskByDeadlinePredicate predicate = preparePredicate(" ");
        ListDeadlineCommand command = new ListDeadlineCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_datePresent_multipleTasksFound() {
        TaskByDeadlinePredicate predicate = preparePredicate("2022-10-07");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ListDeadlineCommand command = new ListDeadlineCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON, DANIEL, CARL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     * @return
     */
    private TaskByDeadlinePredicate preparePredicate(String userInput) {
        return new TaskByDeadlinePredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
