package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListTagCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TagContainsKeywordsPredicate;

public class ListTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        ListTagCommand listFirstModuleCommand = new ListTagCommand(firstPredicate);
        ListTagCommand listSecondModuleCommand = new ListTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listFirstModuleCommand.equals(listFirstModuleCommand));

        // same values -> returns true
        ListTagCommand findFirstCommandCopy = new ListTagCommand(firstPredicate);
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
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListTagCommand command = new ListTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        TagContainsKeywordsPredicate predicate = preparePredicate("optional");
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        ListTagCommand command = new ListTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     * @return
     */
    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
