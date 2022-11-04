package seedu.address.logic.commands.findcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalSuppliers;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model bExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model dModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(), new UserPrefs());
    private Model dExpectedModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(),
            new UserPrefs());
    private Model sModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());
    private Model sExpectedModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindCommand findFirstCommand = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                PersonCategory.BUYER);
        FindCommand findSecondCommand = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
                PersonCategory.BUYER);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                PersonCategory.BUYER);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBuyerFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("    ");
        FindCommand command = new FindCommand(predicate,
                new NameContainsKeywordsPredicate<>(new ArrayList<>()),
                new NameContainsKeywordsPredicate<>(new ArrayList<>()), PersonCategory.BUYER);
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Collections.emptyList(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleBuyersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate,
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                PersonCategory.BUYER);
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Arrays.asList(TypicalBuyers.CARL, TypicalBuyers.ELLE, TypicalBuyers.FIONA),
                bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleDeliverersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Deliverer> predicate = preparePredicateDeliverer("Kurz Elle Kunz");
        FindCommand command = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                predicate, new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                PersonCategory.DELIVERER);
        dExpectedModel.updateFilteredDelivererList(predicate);
        assertCommandSuccess(command, dModel, expectedMessage, dExpectedModel);
        assertEquals(Arrays.asList(TypicalDeliverers.CARL, TypicalDeliverers.ELLE, TypicalDeliverers.FIONA),
                dModel.getFilteredDelivererList());
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Supplier> predicate = preparePredicateSupplier("Kurz Elle Kunz");
        FindCommand command = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")), predicate,
                PersonCategory.SUPPLIER);
        sExpectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, sModel, expectedMessage, sExpectedModel);
        assertEquals(Arrays.asList(TypicalSuppliers.CARL, TypicalSuppliers.ELLE, TypicalSuppliers.FIONA),
                sModel.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_filterBuyerGetDeliverer() {
        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate,
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                PersonCategory.BUYER);
        command.execute(dModel);
        assertEquals(Arrays.asList(TypicalDeliverers.CARL, TypicalDeliverers.ELLE, TypicalDeliverers.FIONA),
                dModel.getFilteredDelivererList());
    }

    @Test
    public void execute_multipleKeywords_filterDelivererGetSupplier() {
        NameContainsKeywordsPredicate<Deliverer> predicate = preparePredicateDeliverer("Kurz Elle Kunz");
        FindCommand command = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")), predicate,
                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
                PersonCategory.DELIVERER);
        command.execute(sModel);
        assertEquals(Arrays.asList(TypicalSuppliers.CARL, TypicalSuppliers.ELLE, TypicalSuppliers.FIONA),
                sModel.getFilteredSupplierList());
    }

    @Test
    public void execute_findDeliverers_noBuyersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
        };
        Predicate<Deliverer> delivererPredicate = preparePredicateDeliverer(
                "Kurz Elle Kunz");
        Predicate<Supplier> supplierPredicate = new Predicate<Supplier>() {
            @Override
            public boolean test(Supplier supplier) {
                return false;
            }
        };

        FindCommand command = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                PersonCategory.DELIVERER);
        bExpectedModel.updateFilteredBuyerList(buyerPredicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Collections.emptyList(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_findSuppliers_noBuyersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
        };
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
        };

        Predicate<Supplier> supplierPredicate = preparePredicateSupplier("Kurz Elle Kunz");

        FindCommand command = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                PersonCategory.SUPPLIER);
        bExpectedModel.updateFilteredBuyerList(buyerPredicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Collections.emptyList(), bModel.getFilteredBuyerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Buyer> preparePredicateBuyer(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Deliverer> preparePredicateDeliverer(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Supplier> preparePredicateSupplier(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
