package seedu.address.logic.commands.commission;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalCommissions.CAT_PRODUCER;
import static seedu.address.testutil.TypicalCommissions.DOG_PRODUCER;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.OpenCommissionCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.Commission;
import seedu.address.ui.GuiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code OpenCommissionCommand}.
 */
class OpenCommissionCommandTest {
    private final Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_noIndex_switchesTab() {
        model.selectCustomer(model.getSortedFilteredCustomerList().get(0));
        CommandResult result = Assertions.assertDoesNotThrow(() -> new OpenCommissionCommand().execute(model));
        assertEquals(result.getFeedbackToUser(), Messages.MESSAGE_OPEN_COMMISSION_TAB_SUCCESS);
        assertEquals(model.getSelectedTab(), GuiTab.COMMISSION);
    }

    @Test
    public void execute_validIndex_success() {
        model.selectCustomer(model.getSortedFilteredCustomerList().get(0));
        model.getSelectedCustomer().getValue().addCommission(
                DOG_PRODUCER.apply(model.getSelectedCustomer().getValue()));
        model.getSelectedCustomer().getValue().addCommission(
                CAT_PRODUCER.apply(model.getSelectedCustomer().getValue()));

        Commission commissionToOpen = model.getFilteredCommissionList().get(1);
        OpenCommissionCommand openCommissionCommand = new OpenCommissionCommand(INDEX_SECOND);

        String expectedMessage = String.format(Messages.MESSAGE_OPEN_COMMISSION_SUCCESS,
                commissionToOpen);

        CommandResult result = assertDoesNotThrow(() -> openCommissionCommand.execute(model));

        assertEquals(result.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_indexTooHigh_throwsCommandException() {
        model.selectCustomer(model.getSortedFilteredCustomerList().get(0));
        OpenCommissionCommand openCommissionCommand = new OpenCommissionCommand(INDEX_THIRD);
        assertCommandFailure(openCommissionCommand, model, Messages.MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        OpenCommissionCommand openFirstCommand = new OpenCommissionCommand(INDEX_FIRST);
        OpenCommissionCommand openSecondCommand = new OpenCommissionCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(openFirstCommand, openFirstCommand);

        // same values -> returns true
        OpenCommissionCommand openFirstCommandCopy = new OpenCommissionCommand(INDEX_FIRST);
        assertEquals(openFirstCommand, openFirstCommandCopy);

        // null -> returns false
        assertNotEquals(openFirstCommand, null);

        // different commission -> returns false
        assertNotEquals(openFirstCommand, openSecondCommand);
    }
}
