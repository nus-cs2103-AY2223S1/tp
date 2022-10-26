package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListModuleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;

public class ListModuleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("second"));

        ListModuleCommand listFirstModuleCommand = new ListModuleCommand(firstPredicate);
        ListModuleCommand listSecondModuleCommand = new ListModuleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listFirstModuleCommand.equals(listFirstModuleCommand));

        // same values -> returns true
        ListModuleCommand findFirstCommandCopy = new ListModuleCommand(firstPredicate);
        assertTrue(listFirstModuleCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(listFirstModuleCommand.equals(1));

        // null -> returns false
        assertFalse(listFirstModuleCommand.equals(null));

        // different person -> returns false
        assertFalse(listFirstModuleCommand.equals(listSecondModuleCommand));
    }

    @Test
    public void execute_zeroKeywords_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ModuleContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListModuleCommand command = new ListModuleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        ModuleContainsKeywordsPredicate predicate = preparePredicate("CS2100");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ListModuleCommand command = new ListModuleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleContainsKeywordsPredicate}.
     * @return
     */
    private ModuleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ModuleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
