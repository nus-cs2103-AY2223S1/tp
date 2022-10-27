package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListUnmarkedCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskIsDonePredicate;

public class ListUnmarkedCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskIsDonePredicate firstPredicate =
                new TaskIsDonePredicate(Collections.singletonList("false"));

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
        TaskIsDonePredicate predicate = preparePredicate(" ");
        ListUnmarkedCommand command = new ListUnmarkedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleUnmarked_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TaskIsDonePredicate predicate = preparePredicate("false");
        ListUnmarkedCommand command = new ListUnmarkedCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskIsDonePredicate}.
     * @return
     */
    private TaskIsDonePredicate preparePredicate(String userInput) {
        return new TaskIsDonePredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
