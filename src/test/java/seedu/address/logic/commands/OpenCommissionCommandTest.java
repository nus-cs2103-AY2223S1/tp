package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalCommissions.CAT_PRODUCER;
import static seedu.address.testutil.TypicalCommissions.DOG_PRODUCER;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.Commission;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code OpenCommissionCommand}.
 */
class OpenCommissionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noSelectedCustomer_throwsCommandException() {
        // initialise an empty model manager since ModelManager#selectCustomer does not allow me to select null
        model = new ModelManager();
        assertCommandFailure(new OpenCommissionCommand(INDEX_FIRST), model, Messages.MESSAGE_NO_ACTIVE_CUSTOMER);
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

        String expectedMessage = String.format(OpenCommissionCommand.MESSAGE_OPEN_COMMISSION_SUCCESS,
                commissionToOpen);

        CommandResult result = assertDoesNotThrow(() -> openCommissionCommand.execute(model));

        assertEquals(result.getFeedbackToUser(), expectedMessage);
        assertEquals(model.getSelectedCommission().getValue(), commissionToOpen);

        // This is necessary because the Customer objects seem to be shared among testcases, and will cause other tests
        // to fail without this line.
        model.getSelectedCustomer().getValue().getCommissions().setCommissions(new ArrayList<>());
    }

    @Test
    public void execute_indexTooHigh_throwsCommandException() {
        model.selectCustomer(model.getSortedFilteredCustomerList().get(0));
        OpenCommissionCommand openCommissionCommand = new OpenCommissionCommand(INDEX_FIRST);
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
