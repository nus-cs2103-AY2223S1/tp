package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.List.ListUnmarkedCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.ModuleIsDonePredicate;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.*;
import static seedu.address.testutil.TypicalPersons.FIONA;

public class ListUnmarkedCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleIsDonePredicate predicate =
                new ModuleIsDonePredicate(false);


        ListUnmarkedCommand listUnmarkedCommand = new ListUnmarkedCommand(predicate);

        // same object -> returns true
        assertTrue(listUnmarkedCommand.equals(listUnmarkedCommand));

        // same values -> returns true
        ListUnmarkedCommand findCommandCopy = new ListUnmarkedCommand(predicate);
        assertTrue(listUnmarkedCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(listUnmarkedCommand.equals(1));

        // null -> returns false
        assertFalse(listUnmarkedCommand.equals(null));

    }

    @Test
    public void execute_allMarked_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ModuleIsDonePredicate predicate = preparePredicate(" ");
        ListUnmarkedCommand command = new ListUnmarkedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleUnmarked_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        ModuleIsDonePredicate predicate = preparePredicate("cs2103t");
        ListUnmarkedCommand command = new ListUnmarkedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     * @return
     */
    private ModuleIsDonePredicate preparePredicate(String userInput) {
        return new ModuleIsDonePredicate(false);
    }
}
