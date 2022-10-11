package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
//import static seedu.address.testutil.TypicalBuyers.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Supplier;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void equals() {
//        FindCommand findFirstCommand = new FindCommand(
//                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
//                "Buyer");
//        FindCommand findSecondCommand = new FindCommand(
//                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("second")),
//                "Buyer");
//
//        // same object -> returns true
//        assertTrue(findFirstCommand.equals(findFirstCommand));
//
//        // same values -> returns true
//        FindCommand findFirstCommandCopy = new FindCommand(
//                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("first")),
//                "Buyer");
//        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(findFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(findFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(findFirstCommand.equals(findSecondCommand));
//    }
//
//    @Test
//    public void execute_zeroKeywords_noBuyerFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("    ");
//        FindCommand command = new FindCommand(predicate,
//                new NameContainsKeywordsPredicate<>(new ArrayList<>()),
//                new NameContainsKeywordsPredicate<>(new ArrayList<>()), "Buyer");
//        expectedModel.updateFilteredBuyerList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredBuyerList());
//    }
//
//    @Test
//    public void execute_multipleKeywords_multipleBuyersFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate<Buyer> predicate = preparePredicateBuyer("Kurz Elle Kunz");
//        FindCommand command = new FindCommand(predicate,
//                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")),
//                new NameContainsKeywordsPredicate<>(Arrays.asList("Kurz", "Elle", "Kunz")), "Buyer");
//        expectedModel.updateFilteredBuyerList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredBuyerList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate<Buyer> preparePredicateBuyer(String userInput) {
//        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
//    }
}
