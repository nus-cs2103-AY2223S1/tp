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
import seedu.address.model.person.*;
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
                new PersonCategory("Buyer"));
        FindCommand findSecondCommand = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
                new PersonCategory("Buyer"));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
                new PersonCategory("Buyer"));
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
                new NameContainsKeywordsPredicate<>(new ArrayList<>()), new PersonCategory("Buyer"));
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
                new PersonCategory("Buyer"));
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Arrays.asList(TypicalBuyers.CARL, TypicalBuyers.ELLE, TypicalBuyers.FIONA),
                bModel.getFilteredBuyerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Buyer> preparePredicateBuyer(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
