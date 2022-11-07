package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.StallContainsKeywordsPredicate;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) for {@code SFindCommand}.
 */
public class SFindCommandTest {
    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StallContainsKeywordsPredicate firstPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("first")));
        StallContainsKeywordsPredicate secondPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("first")));

        StallContainsKeywordsPredicate thirdPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("second")));
        StallContainsKeywordsPredicate fourthPredicate =
                new StallContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("second")));

        SFindCommand findFirstCommand = new SFindCommand(firstPredicate);
        SFindCommand findSecondCommand = new SFindCommand(secondPredicate);
        SFindCommand findThirdCommand = new SFindCommand(thirdPredicate);
        SFindCommand findFourthCommand = new SFindCommand(fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        SFindCommand findFirstCommandCopy = new SFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different name or tag -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand)); //different names
        assertFalse(findFirstCommand.equals(findThirdCommand)); //different tags
        assertFalse(findFirstCommand.equals(findFourthCommand)); //different names and tags
    }

    @Test
    public void execute_zeroKeywords_noStallFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STALLS_LISTED_OVERVIEW, 0);
        StallContainsKeywordsPredicate predicate = preparePredicate(" ");
        SFindCommand command = new SFindCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStallList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_STALLS_LISTED_OVERVIEW, 3);
        StallContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        SFindCommand command = new SFindCommand(predicate);
        expectedModel.updateFilteredStallList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Stall> toFind = Arrays.asList(TypicalStalls.CARL,
                TypicalStalls.ELLE,
                TypicalStalls.FIONA);
        List<Stall> foundStalls = model.getFilteredStallList();
        assertEquals(3, foundStalls.size());
        for (Stall stall : toFind) {
            assertTrue(foundStalls.stream().anyMatch(found -> found.isSameStall(stall)));
        }
    }

    /**
     * Parses {@code userInput} into a {@code StallContainsKeywordsPredicate}.
     */
    private StallContainsKeywordsPredicate preparePredicate(String userInput) {
        List<String> lst = Arrays.asList(userInput.split("\\s+"));
        List<Name> nameList = new ArrayList<>();
        for (String s : lst) {
            nameList.add(new Name(s));
        }
        return new StallContainsKeywordsPredicate(nameList, Collections.emptyList());
    }
}
