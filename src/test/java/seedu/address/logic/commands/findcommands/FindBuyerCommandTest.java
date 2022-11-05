package seedu.address.logic.commands.findcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalBuyers;

public class FindBuyerCommandTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model bExpectedModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindBuyerCommand findFirstCommand = new FindBuyerCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")));
        FindBuyerCommand findSecondCommand = new FindBuyerCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBuyerCommand findFirstCommandCopy = new FindBuyerCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")));
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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                TypicalBuyers.getTypicalBuyers().size());
        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("    ");
        FindBuyerCommand command = new FindBuyerCommand(predicate);
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(bExpectedModel.getFilteredBuyerList(), bModel.getFilteredBuyerList());
    }

    @Test
    public void execute_multipleKeywords_multipleBuyersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("Carl Kurz");
        FindBuyerCommand command = new FindBuyerCommand(predicate);
        bExpectedModel.updateFilteredBuyerList(predicate);
        assertCommandSuccess(command, bModel, expectedMessage, bExpectedModel);
        assertEquals(Arrays.asList(TypicalBuyers.CARL), bModel.getFilteredBuyerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Buyer> preparePredicateBuyer(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
