package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.List.ListModuleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;


import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.*;
import static seedu.address.testutil.TypicalPersons.FIONA;

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
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ModuleContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListModuleCommand command = new ListModuleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ModuleContainsKeywordsPredicate predicate = preparePredicate("cs2103t");
        ListModuleCommand command = new ListModuleCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     * @return
     */
    private ModuleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ModuleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
