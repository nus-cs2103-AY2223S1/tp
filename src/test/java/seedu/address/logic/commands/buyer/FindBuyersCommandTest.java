package seedu.address.logic.commands.buyer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BUYERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBuyers.KEYWORD_MATCHING_MEIER;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyers;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersWithMeier;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.BuyerNameContainsSubstringPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindBuyersCommandTest {
    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void equals() {
        BuyerNameContainsSubstringPredicate firstPredicate =
                new BuyerNameContainsSubstringPredicate("first");
        BuyerNameContainsSubstringPredicate secondPredicate =
                new BuyerNameContainsSubstringPredicate("second");

        FindBuyersCommand findFirstCommand = new FindBuyersCommand(firstPredicate);
        FindBuyersCommand findSecondCommand = new FindBuyersCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBuyersCommand findFirstCommandCopy = new FindBuyersCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different buyer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noKeywords_allBuyersFound() {
        String expectedMessage = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 7);
        BuyerNameContainsSubstringPredicate predicate = new BuyerNameContainsSubstringPredicate("");
        FindBuyersCommand command = new FindBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalBuyers(), model.getFilteredBuyerList());
    }

    @Test
    public void execute_oneKeyword_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_BUYERS_LISTED_OVERVIEW, 2);
        BuyerNameContainsSubstringPredicate predicate = new BuyerNameContainsSubstringPredicate(KEYWORD_MATCHING_MEIER);
        FindBuyersCommand command = new FindBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalBuyersWithMeier(), model.getFilteredBuyerList());
    }
}
