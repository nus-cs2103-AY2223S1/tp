package seedu.address.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTY_LISTED_OVERVIEW;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBuyers.getTypicalBuyersBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Name;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.price.PriceRange;
import seedu.address.model.property.FilterPropsByOwnerNamePredicate;
import seedu.address.model.property.FilterPropsByPriceRangePredicate;
import seedu.address.model.property.FilterPropsContainingAllCharacteristicsPredicate;
import seedu.address.model.property.FilterPropsContainingAnyCharacteristicPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterProperties}.
 */
public class FilterPropertiesCommandTest {
    private Model model = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBuyersBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterPropsByPriceRangePredicate priceRangePred =
                new FilterPropsByPriceRangePredicate(new PriceRange("500000-1000000"));
        FilterPropsContainingAllCharacteristicsPredicate allCharPred =
                new FilterPropsContainingAllCharacteristicsPredicate((new Characteristics("char1; char2")));
        FilterPropsContainingAnyCharacteristicPredicate anyCharPred =
                new FilterPropsContainingAnyCharacteristicPredicate((new Characteristics("char1; char2")));

        FilterPropertiesCommand priceRangeFilter = new FilterPropertiesCommand(priceRangePred);
        FilterPropertiesCommand allCharFilter = new FilterPropertiesCommand(allCharPred);
        FilterPropertiesCommand anyCharFilter = new FilterPropertiesCommand(anyCharPred);

        // same object -> returns true
        assertTrue(priceRangeFilter.equals(priceRangeFilter));

        // same values -> returns true
        FilterPropertiesCommand priceRangeFilterCopy = new FilterPropertiesCommand(priceRangePred);
        assertTrue(priceRangeFilter.equals(priceRangeFilterCopy));

        // different types -> returns false
        assertFalse(priceRangeFilter.equals(1));

        // null -> returns false
        assertFalse(priceRangeFilter.equals(null));

        // different condition -> returns false
        assertFalse(priceRangeFilter.equals(allCharFilter));

        // both filtering on same characteristics, but one is all, one is any -> returns false
        assertFalse(allCharFilter.equals(anyCharFilter));
    }

    @Test
    public void execute_priceRange_propertiesFound() {
        FilterPropsByPriceRangePredicate predicate =
                new FilterPropsByPriceRangePredicate(new PriceRange("1000-4000000"));
        FilterPropertiesCommand command = new FilterPropertiesCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(
                MESSAGE_PROPERTY_LISTED_OVERVIEW, expectedModel.getFilteredPropertyList().size()); // 3 props found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_priceRange_noPropertiesFound() {
        FilterPropsByPriceRangePredicate predicate =
                new FilterPropsByPriceRangePredicate(new PriceRange("0-100"));
        FilterPropertiesCommand command = new FilterPropertiesCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(
                MESSAGE_PROPERTY_LISTED_OVERVIEW, expectedModel.getFilteredPropertyList().size()); // 0 props found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ownerName_propertiesFound() {
        FilterPropsByOwnerNamePredicate predicate =
                new FilterPropsByOwnerNamePredicate(new Name("John Doe"));
        FilterPropertiesCommand command = new FilterPropertiesCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(
                MESSAGE_PROPERTY_LISTED_OVERVIEW, expectedModel.getFilteredPropertyList().size()); // 2 props found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allChar_noPropertiesFound() {
        FilterPropsContainingAllCharacteristicsPredicate predicate =
                new FilterPropsContainingAllCharacteristicsPredicate(new Characteristics("homey; modern; big"));
        FilterPropertiesCommand command = new FilterPropertiesCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(
                MESSAGE_PROPERTY_LISTED_OVERVIEW, expectedModel.getFilteredPropertyList().size()); // 0 props found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_anyChar_buyersFound() {
        FilterPropsContainingAnyCharacteristicPredicate predicate =
                new FilterPropsContainingAnyCharacteristicPredicate(new Characteristics("homey; modern; big"));
        FilterPropertiesCommand command = new FilterPropertiesCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate);
        String expectedMessage = String.format(
                MESSAGE_PROPERTY_LISTED_OVERVIEW, expectedModel.getFilteredPropertyList().size()); // 1 prop found
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
