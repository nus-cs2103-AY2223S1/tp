package seedu.address.logic.commands.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BUYERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.FilterBuyersByPricePredicate;
import seedu.address.model.buyer.FilterBuyersByPriorityPredicate;
import seedu.address.model.buyer.FilterBuyersContainingAllCharacteristicsPredicate;
import seedu.address.model.buyer.FilterBuyersContainingAnyCharacteristicPredicate;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.price.Price;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterBuyers}.
 */
public class FilterBuyersCommandTest {
    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterBuyersByPricePredicate pricePred = new FilterBuyersByPricePredicate(new Price("1000000"));
        FilterBuyersContainingAllCharacteristicsPredicate allCharPred =
                new FilterBuyersContainingAllCharacteristicsPredicate((new Characteristics("char1; char2")));
        FilterBuyersContainingAnyCharacteristicPredicate anyCharPred =
                new FilterBuyersContainingAnyCharacteristicPredicate((new Characteristics("char1; char2")));

        FilterBuyersCommand priceFilter = new FilterBuyersCommand(pricePred);
        FilterBuyersCommand allCharFilter = new FilterBuyersCommand(allCharPred);
        FilterBuyersCommand anyCharFilter = new FilterBuyersCommand(anyCharPred);

        // same object -> returns true
        assertTrue(priceFilter.equals(priceFilter));

        // same values -> returns true
        FilterBuyersCommand priceFilterCopy = new FilterBuyersCommand(pricePred);
        assertTrue(priceFilter.equals(priceFilterCopy));

        // different types -> returns false
        assertFalse(priceFilter.equals(1));

        // null -> returns false
        assertFalse(priceFilter.equals(null));

        // different condition -> returns false
        assertFalse(priceFilter.equals(allCharFilter));

        // both filtering on same characteristics, but one is all, one is any -> returns false
        assertFalse(allCharFilter.equals(anyCharFilter));
    }

    @Test
    public void execute_price_buyersFound() {
        FilterBuyersByPricePredicate predicate = new FilterBuyersByPricePredicate(new Price("300"));
        FilterBuyersCommand command = new FilterBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        String expectedMessage = String.format(
                MESSAGE_BUYERS_LISTED_OVERVIEW, expectedModel.getFilteredBuyerList().size()); // 6 buyers found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_price_onlyBuyersWithNoPriceRangeFound() {
        FilterBuyersByPricePredicate predicate = new FilterBuyersByPricePredicate(new Price("99999999"));
        FilterBuyersCommand command = new FilterBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        String expectedMessage = String.format(
                MESSAGE_BUYERS_LISTED_OVERVIEW, expectedModel.getFilteredBuyerList().size()); // 3 buyers found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priority_buyersFound() {
        FilterBuyersByPriorityPredicate predicate = new FilterBuyersByPriorityPredicate(new Priority("HIGH"));
        FilterBuyersCommand command = new FilterBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        String expectedMessage = String.format(
                MESSAGE_BUYERS_LISTED_OVERVIEW, expectedModel.getFilteredBuyerList().size()); // 2 buyers found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allChar_noBuyerFound() {
        FilterBuyersContainingAllCharacteristicsPredicate predicate =
                new FilterBuyersContainingAllCharacteristicsPredicate(new Characteristics("cooling; kid-friendly"));
        FilterBuyersCommand command = new FilterBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        String expectedMessage = String.format(
                MESSAGE_BUYERS_LISTED_OVERVIEW, expectedModel.getFilteredBuyerList().size()); // 0 buyers found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_anyChar_buyersFound() {
        FilterBuyersContainingAnyCharacteristicPredicate predicate =
                new FilterBuyersContainingAnyCharacteristicPredicate(new Characteristics("cooling; kid-friendly"));
        FilterBuyersCommand command = new FilterBuyersCommand(predicate);
        expectedModel.updateFilteredBuyerList(predicate);
        String expectedMessage = String.format(
                MESSAGE_BUYERS_LISTED_OVERVIEW, expectedModel.getFilteredBuyerList().size()); // 2 buyers found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
