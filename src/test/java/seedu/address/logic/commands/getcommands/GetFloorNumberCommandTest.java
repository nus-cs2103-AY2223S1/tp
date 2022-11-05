package seedu.address.logic.commands.getcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FloorNumberPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code GetFloorNumberCommand}.
 */
public class GetFloorNumberCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FloorNumberPredicate firstPredicate =
                new FloorNumberPredicate(Collections.singletonList(3));
        FloorNumberPredicate secondPredicate =
                new FloorNumberPredicate(Collections.singletonList(1));

        GetFloorNumberCommand getFirstFloorNumberCommand = new GetFloorNumberCommand(firstPredicate);
        GetFloorNumberCommand getSecondFloorNumberCommand = new GetFloorNumberCommand(secondPredicate);

        // same object -> returns true
        assertTrue(getFirstFloorNumberCommand.equals(getFirstFloorNumberCommand));

        // same values -> returns true
        GetFloorNumberCommand getFirstFloorNumberCommandCopy = new GetFloorNumberCommand(firstPredicate);
        assertTrue(getFirstFloorNumberCommand.equals(getFirstFloorNumberCommandCopy));

        // different types -> returns false
        assertFalse(getFirstFloorNumberCommand.equals("1"));

        // null -> returns false
        assertFalse(getFirstFloorNumberCommand.equals(null));

        // different floor numbers -> returns false
        assertFalse(getFirstFloorNumberCommand.equals(getSecondFloorNumberCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FloorNumberPredicate predicate = preparePredicate(" ");
        GetFloorNumberCommand command = new GetFloorNumberCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FloorNumberPredicate predicate = preparePredicate("1 2");
        GetFloorNumberCommand command = new GetFloorNumberCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_noneMatching_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FloorNumberPredicate predicate = preparePredicate("10 6 7");
        GetFloorNumberCommand command = new GetFloorNumberCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private FloorNumberPredicate preparePredicate(String userInput) {
        String[] st = userInput.split("\\s+");
        List<Integer> floors = new ArrayList<>();
        for (String i : st) {
            Integer floor = Integer.parseInt(i);
            floors.add(floor);
        }
        return new FloorNumberPredicate(floors);
    }
}
