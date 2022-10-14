package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.LocationContainsKeywordsPredicate;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalSuppliers;

public class FilterLocCommandTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model bExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model dModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(), new UserPrefs());
    private Model dExpectedModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(),
            new UserPrefs());
    private Model sModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());
    private Model sExpectedModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterLocCommand firstCommand = new FilterLocCommand(
                new LocationContainsKeywordsPredicate<>("first"),
                new LocationContainsKeywordsPredicate<>("first"),
                new LocationContainsKeywordsPredicate<>("first"));
        FilterLocCommand secondCommand = new FilterLocCommand(
                new LocationContainsKeywordsPredicate<>("second"),
                new LocationContainsKeywordsPredicate<>("second"),
                new LocationContainsKeywordsPredicate<>("second"));

        assertTrue(firstCommand.equals(firstCommand));

        FilterLocCommand firstCommandCopy = new FilterLocCommand(
                new LocationContainsKeywordsPredicate<>("first"),
                new LocationContainsKeywordsPredicate<>("first"),
                new LocationContainsKeywordsPredicate<>("first"));
        assertTrue(firstCommand.equals(firstCommandCopy));

        assertFalse(firstCommand.equals(1));

        assertFalse(firstCommand.equals(null));

        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBuyerFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        LocationContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("    ");
        FilterLocCommand command = new FilterLocCommand(predicate,
                new LocationContainsKeywordsPredicate<>(" "),
                new LocationContainsKeywordsPredicate<>(" "));
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Collections.emptyList(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleBuyersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        LocationContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("Singapore");
        FilterLocCommand command = new FilterLocCommand(predicate,
                new LocationContainsKeywordsPredicate<>("Singapore"),
                new LocationContainsKeywordsPredicate<>("Singapore"));
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(TypicalBuyers.getTypicalBuyers(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleDeliverersFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        LocationContainsKeywordsPredicate<Deliverer> predicate = preparePredicateDeliverer("Singapore");
        FilterLocCommand command = new FilterLocCommand(new LocationContainsKeywordsPredicate<>("Singapore"),
                predicate, new LocationContainsKeywordsPredicate<>("Singapore"));
        dExpectedModel.updateFilteredDelivererList(predicate);
        command.execute(dModel);
        //assertCommandSuccess(command, dModel, expectedMessage, dExpectedModel);
        assertEquals(TypicalDeliverers.getTypicalDeliverers(), dModel.getFilteredDelivererList());
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        LocationContainsKeywordsPredicate<Supplier> predicate = preparePredicateSupplier("Singapore");
        FilterLocCommand command = new FilterLocCommand(new LocationContainsKeywordsPredicate<>("Singapore"),
                new LocationContainsKeywordsPredicate<>("Singapore"), predicate);
        sExpectedModel.updateFilteredSupplierList(predicate);
        command.execute(sModel);
        //assertCommandSuccess(command, sModel, expectedMessage, sExpectedModel);
        assertEquals(TypicalSuppliers.getTypicalSuppliers(), sModel.getFilteredSupplierList());
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate<Buyer> preparePredicateBuyer(String userInput) {
        return new LocationContainsKeywordsPredicate<>(userInput.trim());
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate<Deliverer> preparePredicateDeliverer(String userInput) {
        return new LocationContainsKeywordsPredicate<>(userInput.trim());
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate<Supplier> preparePredicateSupplier(String userInput) {
        return new LocationContainsKeywordsPredicate<>(userInput.trim());
    }
}
