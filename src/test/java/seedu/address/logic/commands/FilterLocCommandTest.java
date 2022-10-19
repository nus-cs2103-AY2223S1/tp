package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
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
                new LocationContainsKeywordsPredicate<>(Arrays.asList("first")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("first")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("first")));
        FilterLocCommand secondCommand = new FilterLocCommand(
                new LocationContainsKeywordsPredicate<>(Arrays.asList("second")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("second")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("second")));

        assertTrue(firstCommand.equals(firstCommand));

        FilterLocCommand firstCommandCopy = new FilterLocCommand(
                new LocationContainsKeywordsPredicate<>(Arrays.asList("first")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("first")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("first")));
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
                new LocationContainsKeywordsPredicate<>(Arrays.asList(" ")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList(" ")));
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Collections.emptyList(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleBuyersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        LocationContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("Singapore");
        FilterLocCommand command = new FilterLocCommand(predicate,
                new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")));
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(TypicalBuyers.getTypicalBuyers(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleDeliverersFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        LocationContainsKeywordsPredicate<Deliverer> predicate = preparePredicateDeliverer("Singapore");
        FilterLocCommand command = new FilterLocCommand(new LocationContainsKeywordsPredicate<>(
                Arrays.asList("Singapore")),
                predicate, new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")));
        dExpectedModel.updateFilteredDelivererList(predicate);
        command.execute(dModel);
        assertEquals(TypicalDeliverers.getTypicalDeliverers(), dModel.getFilteredDelivererList());
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        LocationContainsKeywordsPredicate<Supplier> predicate = preparePredicateSupplier("Singapore");
        FilterLocCommand command = new FilterLocCommand(new LocationContainsKeywordsPredicate<>(
                Arrays.asList("Singapore")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")), predicate);
        sExpectedModel.updateFilteredSupplierList(predicate);
        command.execute(sModel);
        assertEquals(TypicalSuppliers.getTypicalSuppliers(), sModel.getFilteredSupplierList());
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate<Buyer> preparePredicateBuyer(String userInput) {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            return new LocationContainsKeywordsPredicate<>(new ArrayList<>());
        }
        return new LocationContainsKeywordsPredicate<>(Arrays.asList(trimmedArgs.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate<Deliverer> preparePredicateDeliverer(String userInput) {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            return new LocationContainsKeywordsPredicate<>(new ArrayList<>());
        }
        return new LocationContainsKeywordsPredicate<>(Arrays.asList(trimmedArgs.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code LocationContainsKeywordsPredicate}.
     */
    private LocationContainsKeywordsPredicate<Supplier> preparePredicateSupplier(String userInput) {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            return new LocationContainsKeywordsPredicate<>(new ArrayList<>());
        }
        return new LocationContainsKeywordsPredicate<>(Arrays.asList(trimmedArgs.split("\\s+")));
    }
}
