package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.ui.GuiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code OpenCustomerCommand}.
 */
public class OpenCustomerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Customer customerToDelete = model.getSortedFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        OpenCustomerCommand openCommand = new OpenCustomerCommand(INDEX_FIRST);

        String expectedMessage = String.format(Messages.MESSAGE_OPEN_CUSTOMER_SUCCESS, customerToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(openCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredCustomerList().size() + 1);
        OpenCustomerCommand openCustomerCommand = new OpenCustomerCommand(outOfBoundIndex);

        assertCommandFailure(openCustomerCommand, model, null, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST);

        Customer customerToOpen = model.getSortedFilteredCustomerList().get(INDEX_FIRST.getZeroBased());
        OpenCustomerCommand openCommand = new OpenCustomerCommand(INDEX_FIRST);

        String expectedMessage = String.format(Messages.MESSAGE_OPEN_CUSTOMER_SUCCESS, customerToOpen);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showCustomerAtIndex(expectedModel, INDEX_FIRST);

        assertCommandSuccess(openCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCustomerList().size());

        OpenCustomerCommand openCommand = new OpenCustomerCommand(outOfBoundIndex);

        assertCommandFailure(openCommand, model, null, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noIndex_switchesTab() {
        model = new ModelManager();
        CommandResult result = assertDoesNotThrow(() -> new OpenCustomerCommand().execute(model, null));
        assertEquals(result.getFeedbackToUser(), OpenCustomerCommand.MESSAGE_USAGE);
        assertEquals(model.getSelectedTab(), GuiTab.CUSTOMER);
    }

    @Test
    public void equals() {
        OpenCustomerCommand openFirstCommand = new OpenCustomerCommand(INDEX_FIRST);
        OpenCustomerCommand openSecondCommand = new OpenCustomerCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(openFirstCommand.equals(openFirstCommand));

        // same values -> returns true
        OpenCustomerCommand openFirstCommandCopy = new OpenCustomerCommand(INDEX_FIRST);
        assertTrue(openFirstCommand.equals(openFirstCommandCopy));

        // different types -> returns false
        assertFalse(openFirstCommand.equals(1));

        // null -> returns false
        assertFalse(openFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(openFirstCommand.equals(openSecondCommand));
    }
}
