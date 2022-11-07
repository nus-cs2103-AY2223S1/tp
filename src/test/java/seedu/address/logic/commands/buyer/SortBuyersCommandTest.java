package seedu.address.logic.commands.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BUYERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.buyer.SortBuyersCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalBuyers.KEYWORD_MATCHING_MEIER;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.sortcomparators.BuyerComparator;
import seedu.address.logic.sortcomparators.BuyerNameComparator;
import seedu.address.logic.sortcomparators.Order;
import seedu.address.logic.sortcomparators.PriceComparator;
import seedu.address.logic.sortcomparators.PriceRangeComparator;
import seedu.address.logic.sortcomparators.PriorityComparator;
import seedu.address.logic.sortcomparators.TimeComparator;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.BuyerNameContainsSubstringPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortBuyersCommandTest {
    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    private BuyerComparator nameComparatorAsc = new BuyerComparator(
            new BuyerNameComparator(new Order("asc")), null, null, null);
    private BuyerComparator nameComparatorDesc = new BuyerComparator(
            new BuyerNameComparator(new Order("desc")), null, null, null);
    private BuyerComparator priceRangeComparatorAsc = new BuyerComparator(
            null, new PriceRangeComparator(new Order("asc")), null, null);
    private BuyerComparator priceRangeComparatorDesc = new BuyerComparator(
            null, new PriceRangeComparator(new Order("desc")), null, null);
    private BuyerComparator priorityComparatorAsc = new BuyerComparator(
            null, null, new PriorityComparator(new Order("asc")), null);
    private BuyerComparator priorityComparatorDesc = new BuyerComparator(
            null, null, new PriorityComparator(new Order("desc")), null);
    private BuyerComparator entryTimeComparatorAsc = new BuyerComparator(
            null, null, null, new TimeComparator(new Order("asc"))
    );
    private BuyerComparator entryTimeComparatorDesc = new BuyerComparator(
            null, null, null, new TimeComparator(new Order("desc"))
    );

    @Test
    public void equals() {
        SortBuyersCommand sortFirstCommand = new SortBuyersCommand(nameComparatorAsc);
        SortBuyersCommand sortSecondCommand = new SortBuyersCommand(priorityComparatorAsc);
        SortBuyersCommand sortThirdCommand = new SortBuyersCommand(priorityComparatorDesc);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortBuyersCommand sortFirstCommandCopy = new SortBuyersCommand(nameComparatorAsc);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // same order, different sorting condition -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));

        // same sorting condition, different order -> returns false
        assertFalse(sortSecondCommand.equals(sortThirdCommand));
    }

    @Test
    public void execute_timeAsc_sameOrder() {
        String expectedMessage = String.format(MESSAGE_SUCCESS, entryTimeComparatorAsc);
        SortBuyersCommand command = new SortBuyersCommand(entryTimeComparatorAsc);
        expectedModel.sortBuyerList(entryTimeComparatorAsc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priorityDesc_success() {
        String expectedMessage = String.format(MESSAGE_SUCCESS, priorityComparatorDesc);
        SortBuyersCommand command = new SortBuyersCommand(priorityComparatorDesc);
        expectedModel.sortBuyerList(priorityComparatorDesc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priceRangeAsc_success() {
        String expectedMessage = String.format(MESSAGE_SUCCESS, priceRangeComparatorAsc);
        SortBuyersCommand command = new SortBuyersCommand(priceRangeComparatorAsc);
        expectedModel.sortBuyerList(priceRangeComparatorAsc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nameDesc_success() {
        String expectedMessage = String.format(MESSAGE_SUCCESS, nameComparatorDesc);
        SortBuyersCommand command = new SortBuyersCommand(nameComparatorDesc);
        expectedModel.sortBuyerList(nameComparatorDesc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
