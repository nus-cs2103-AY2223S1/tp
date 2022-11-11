package seedu.address.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.property.SortPropertiesCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.sortcomparators.Order;
import seedu.address.logic.sortcomparators.PriceComparator;
import seedu.address.logic.sortcomparators.PropertyComparator;
import seedu.address.logic.sortcomparators.PropertyNameComparator;
import seedu.address.logic.sortcomparators.TimeComparator;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortPropertiesCommandTest {
    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    private PropertyComparator nameComparatorAsc = new PropertyComparator(
            new PropertyNameComparator(new Order("asc")), null, null);
    private PropertyComparator nameComparatorDesc = new PropertyComparator(
            new PropertyNameComparator(new Order("desc")), null, null);
    private PropertyComparator priceComparatorAsc = new PropertyComparator(
            null, new PriceComparator(new Order("asc")), null);
    private PropertyComparator priceComparatorDesc = new PropertyComparator(
            null, new PriceComparator(new Order("desc")), null);
    private PropertyComparator entryTimeComparatorAsc = new PropertyComparator(
            null, null, new TimeComparator(new Order("asc")));
    private PropertyComparator entryTimeComparatorDesc = new PropertyComparator(
            null, null, new TimeComparator(new Order("desc")));

    @Test
    public void equals() {
        SortPropertiesCommand sortFirstCommand = new SortPropertiesCommand(nameComparatorAsc);
        SortPropertiesCommand sortSecondCommand = new SortPropertiesCommand(priceComparatorAsc);
        SortPropertiesCommand sortThirdCommand = new SortPropertiesCommand(priceComparatorDesc);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortPropertiesCommand sortFirstCommandCopy = new SortPropertiesCommand(nameComparatorAsc);
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
        SortPropertiesCommand command = new SortPropertiesCommand(entryTimeComparatorAsc);
        expectedModel.sortPropertyList(entryTimeComparatorAsc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priceAsc_success() {
        String expectedMessage = String.format(MESSAGE_SUCCESS, priceComparatorAsc);
        SortPropertiesCommand command = new SortPropertiesCommand(priceComparatorAsc);
        expectedModel.sortPropertyList(priceComparatorAsc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nameDesc_success() {
        String expectedMessage = String.format(MESSAGE_SUCCESS, nameComparatorDesc);
        SortPropertiesCommand command = new SortPropertiesCommand(nameComparatorDesc);
        expectedModel.sortPropertyList(nameComparatorDesc);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
