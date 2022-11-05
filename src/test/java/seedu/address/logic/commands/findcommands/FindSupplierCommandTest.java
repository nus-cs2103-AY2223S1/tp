package seedu.address.logic.commands.findcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalSuppliers;

public class FindSupplierCommandTest {
    private Model sModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());
    private Model sExpectedModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindSupplierCommand findFirstCommand = new FindSupplierCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("first")));
        FindSupplierCommand findSecondCommand = new FindSupplierCommand(
                new NameContainsKeywordsPredicate<>(Arrays.asList("second")));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindSupplierCommand findFirstCommandCopy = new FindSupplierCommand(
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
    public void execute_zeroKeywords_noSupplierFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                TypicalSuppliers.getTypicalSuppliers().size());
        NameContainsKeywordsPredicate<Supplier> predicate = preparePredicateSupplier("    ");
        FindSupplierCommand command = new FindSupplierCommand(predicate);
        sExpectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, sModel, expectedMessage, sExpectedModel);
        assertEquals(sExpectedModel.getFilteredSupplierList(), sModel.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multipleSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate<Supplier> predicate = preparePredicateSupplier("Elle Meyer");
        FindCommand command = new FindSupplierCommand(predicate);
        sExpectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, sModel, expectedMessage, sExpectedModel);
        assertEquals(Arrays.asList(TypicalSuppliers.ELLE), sModel.getFilteredSupplierList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate<Supplier> preparePredicateSupplier(String userInput) {
        return new NameContainsKeywordsPredicate<>(Arrays.asList(userInput.split("\\s+")));
    }
}
