package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.FindPetCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.*;
import seedu.address.model.pet.PetNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalPets;
import seedu.address.testutil.TypicalSuppliers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindPetCommandTest {
    private Model model = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPets.getTypicalPetsAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindPetCommand findFirstCommand = new FindPetCommand(
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")));
        FindPetCommand findSecondCommand = new FindPetCommand(
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("second")));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPetCommand findFirstCommandCopy = new FindPetCommand(
                new PetNameContainsKeywordsPredicate<>(Arrays.asList("first")));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
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
