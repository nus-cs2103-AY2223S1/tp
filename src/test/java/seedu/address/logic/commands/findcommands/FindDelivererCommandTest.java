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
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalDeliverers;

public class FindDelivererCommandTest {
    private Model dModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(), new UserPrefs());
    private Model dExpectedModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(),
            new UserPrefs());

    @Test
    public void equals() {
        FindDelivererCommand findFirstCommand = new FindDelivererCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")));
        FindDelivererCommand findSecondCommand = new FindDelivererCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDelivererCommand findFirstCommandCopy = new FindDelivererCommand(
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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate<Deliverer> predicate = preparePredicateDeliverer("    ");
        FindDelivererCommand command = new FindDelivererCommand(predicate);
        dExpectedModel.updateFilteredDelivererList(predicate);
        assertCommandSuccess(command, dModel, expectedMessage, dExpectedModel);
        assertEquals(Collections.emptyList(), dModel.getFilteredDelivererList());
    }

    @Test
    public void execute_multipleKeywords_multipleDeliverersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate<Deliverer> predicate = preparePredicateDeliverer("Kurz Elle Kunz");
        FindDelivererCommand command = new FindDelivererCommand(predicate);
        dExpectedModel.updateFilteredDelivererList(predicate);
        assertCommandSuccess(command, dModel, expectedMessage, dExpectedModel);
        assertEquals(Arrays.asList(TypicalDeliverers.CARL, TypicalDeliverers.ELLE, TypicalDeliverers.FIONA),
                dModel.getFilteredDelivererList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Deliverer> preparePredicateDeliverer(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
