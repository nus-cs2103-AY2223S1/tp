package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.list.ListUnmarkedCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ModuleIsDonePredicate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.*;

public class ListUnmarkedCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleIsDonePredicate firstPredicate =
                new ModuleIsDonePredicate(Collections.singletonList("false"));

        ListUnmarkedCommand listUnmarkedCommand = new ListUnmarkedCommand(firstPredicate);

        // same object -> returns true
        assertTrue(listUnmarkedCommand.equals(listUnmarkedCommand));

        // same values -> returns true
        ListUnmarkedCommand findCommandCopy = new ListUnmarkedCommand(firstPredicate);
        assertTrue(listUnmarkedCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(listUnmarkedCommand.equals(1));

        // null -> returns false
        assertFalse(listUnmarkedCommand.equals(null));

    }

    @Test
    public void execute_allMarked_noTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ModuleIsDonePredicate predicate = preparePredicate(" ");
        ListUnmarkedCommand command = new ListUnmarkedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleUnmarked_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ModuleIsDonePredicate predicate = preparePredicate("false");
        ListUnmarkedCommand command = new ListUnmarkedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     * @return
     */
    private ModuleIsDonePredicate preparePredicate(String userInput) {
        return new ModuleIsDonePredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
