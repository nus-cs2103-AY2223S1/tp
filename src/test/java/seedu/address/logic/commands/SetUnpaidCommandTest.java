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

public class SetUnpaidCommandTest {

    private Model model = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBillsHealthContact(), new UserPrefs());

    @Test
    public void setBillAsUnpaid() {
        SetUnpaidCommand command = new SetUnpaidCommand(Index.fromOneBased(1));
        int index = command.getIndexOfBill().getZeroBased();
        Bill billToSetAsUnpaid = expectedModel.getFilteredBillList().get(index);
        expectedModel.setBillAsUnpaid(billToSetAsUnpaid);
        assertCommandSuccess(command, model, String.format(SetUnpaidCommand.MESSAGE_SET_UNPAID_BILL_SUCCESS,
                billToSetAsUnpaid), expectedModel);

    }

    @Test
    public void alreadySetUnpaid() {
        SetUnpaidCommand command = new SetUnpaidCommand(Index.fromOneBased(2));
        int index = command.getIndexOfBill().getZeroBased();
        Bill billToSetAsUnpaid = expectedModel.getFilteredBillList().get(index);
        assertCommandFailure(String.format(SetUnpaidCommand.MESSAGE_ALREADY_SET_UNPAID, billToSetAsUnpaid),
                command, model);
    }

    @Test
    public void invalidIndex() {
        SetPaidCommand command = new SetPaidCommand(Index.fromOneBased(100));
        assertCommandFailure(String.format(SetPaidCommand.MESSAGE_INVALID_INDEX), command, model);
    }

    @Test
    public void equals() {
        SetUnpaidCommand setUnpaidCommand = new SetUnpaidCommand(Index.fromOneBased(1));
        SetUnpaidCommand setUnpaidCommand2 = new SetUnpaidCommand(Index.fromOneBased(2));
        SetUnpaidCommand setUnpaidCommand3 = new SetUnpaidCommand(Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(setUnpaidCommand.equals(setUnpaidCommand));

        // same values -> returns true
        assertTrue(setUnpaidCommand.equals(setUnpaidCommand3));

        // different types -> returns false
        assertFalse(setUnpaidCommand.equals(1));

        // null -> returns false
        assertFalse(setUnpaidCommand.equals(null));

        // different bill -> returns false
        assertFalse(setUnpaidCommand.equals(setUnpaidCommand2));
    }
}

