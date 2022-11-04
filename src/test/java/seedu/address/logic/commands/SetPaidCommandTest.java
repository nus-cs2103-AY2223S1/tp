package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBills.getTypicalBillsHealthContact;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bill.Bill;

public class SetPaidCommandTest {

    private Model model = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());

    @Test
    public void setBillAsPaid() {
        SetPaidCommand command = new SetPaidCommand(Index.fromOneBased(2));
        int index = command.getIndexOfBill().getZeroBased();
        Bill billToSetAsPaid = expectedModel.getFilteredBillList().get(index);
        expectedModel.setBillAsPaid(billToSetAsPaid);
        assertCommandSuccess(command, model, String.format(SetPaidCommand.MESSAGE_SET_PAID_BILL_SUCCESS,
                billToSetAsPaid), expectedModel);

    }

    @Test
    public void alreadyPaid() {
        SetPaidCommand command = new SetPaidCommand(Index.fromOneBased(1));
        int index = command.getIndexOfBill().getZeroBased();
        Bill billToSetAsPaid = expectedModel.getFilteredBillList().get(index);
        assertCommandFailure(String.format(SetPaidCommand.MESSAGE_ALREADY_PAID, billToSetAsPaid), command, model);
    }

    @Test
    public void invalidIndex() {
        SetPaidCommand command = new SetPaidCommand(Index.fromOneBased(100));
        assertCommandFailure(String.format(SetPaidCommand.MESSAGE_INVALID_INDEX), command, model);
    }

    @Test
    public void equals() {
        SetPaidCommand setPaidCommand = new SetPaidCommand(Index.fromOneBased(1));
        SetPaidCommand setPaidCommand2 = new SetPaidCommand(Index.fromOneBased(2));
        SetPaidCommand setPaidCommand3 = new SetPaidCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(setPaidCommand.equals(setPaidCommand));

        // same values -> returns true
        assertTrue(setPaidCommand.equals(setPaidCommand3));

        // different types -> returns false
        assertFalse(setPaidCommand.equals(1));

        // null -> returns false
        assertFalse(setPaidCommand.equals(null));

        // different bill -> returns false
        assertFalse(setPaidCommand.equals(setPaidCommand2));
    }
}
