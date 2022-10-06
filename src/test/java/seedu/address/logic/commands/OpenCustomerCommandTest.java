package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code OpenCustomerCommand}.
 */
public class OpenCustomerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        OpenCustomerCommand openCommand = new OpenCustomerCommand(INDEX_FIRST_CUSTOMER);

        String expectedMessage = String.format(OpenCustomerCommand.MESSAGE_OPEN_CUSTOMER_SUCCESS, customerToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(openCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        OpenCustomerCommand openCustomerCommand = new OpenCustomerCommand(outOfBoundIndex);

        assertCommandFailure(openCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Customer customerToOpen = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        OpenCustomerCommand openCommand = new OpenCustomerCommand(INDEX_FIRST_CUSTOMER);

        String expectedMessage = String.format(OpenCustomerCommand.MESSAGE_OPEN_CUSTOMER_SUCCESS, customerToOpen);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(openCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Index outOfBoundIndex = INDEX_SECOND_CUSTOMER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        OpenCustomerCommand openCommand = new OpenCustomerCommand(outOfBoundIndex);

        assertCommandFailure(openCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        OpenCustomerCommand OpenFirstCommand = new OpenCustomerCommand(INDEX_FIRST_CUSTOMER);
        OpenCustomerCommand OpenSecondCommand = new OpenCustomerCommand(INDEX_SECOND_CUSTOMER);

        // same object -> returns true
        assertTrue(OpenFirstCommand.equals(OpenFirstCommand));

        // same values -> returns true
        OpenCustomerCommand deleteFirstCommandCopy = new OpenCustomerCommand(INDEX_FIRST_CUSTOMER);
        assertTrue(OpenFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(OpenFirstCommand.equals(1));

        // null -> returns false
        assertFalse(OpenFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(OpenFirstCommand.equals(OpenSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCustomer(Model model) {
        model.updateFilteredCustomerList(p -> false);

        assertTrue(model.getFilteredCustomerList().isEmpty());
    }
}
