package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bill.Bill;

public class SortBillCommandTest {
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void execute_validSortBillName_success() {
        class AppointmentComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getAppointment().toString().compareTo(second.getAppointment().toString());
            }
        }
        SortBillCommand sortBillCommand = new SortBillCommand("name", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "name");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new AppointmentComparator(), true);
        assertCommandSuccess(sortBillCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidSortBillCriteria_throwCommandException() {
        SortBillCommand sortBillCommand = new SortBillCommand("invalid", true);
        assertCommandFailure(sortBillCommand, model, SortBillCommand.MESSAGE_USAGE);

    }

    @Test
    public void execute_validSortBillAmount_success() {
        class AmountComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getAmount().toString().compareToIgnoreCase(second.getAmount().toString());
            }
        }

        SortBillCommand sortBillCommand = new SortBillCommand("amount", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "amount");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new AmountComparator(), true);
        assertCommandSuccess(sortBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortBillDate_success() {
        class DateComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getBillDate().toString().compareTo(second.getBillDate().toString());
            }
        }

        SortBillCommand sortBillCommand = new SortBillCommand("date", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "date");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new DateComparator(), true);
        assertCommandSuccess(sortBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortBillStatus_success() {
        class StatusComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getPaymentStatus().toString().compareTo(second.getPaymentStatus().toString());
            }
        }

        SortBillCommand sortBillCommand = new SortBillCommand("status", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "status");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new StatusComparator(), true);
        assertCommandSuccess(sortBillCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortBillCommand sortBillCommand = new SortBillCommand("name", true);
        SortBillCommand sortBillCommandCopy = new SortBillCommand("name", true);
        SortBillCommand sortBillCommandDifferent = new SortBillCommand("address", true);
        SortBillCommand sortBillCommandDifferent2 = new SortBillCommand("name", false);

        // same object -> returns true
        assertTrue(sortBillCommand.equals(sortBillCommand));

        // same values -> returns true
        assertTrue(sortBillCommand.equals(sortBillCommandCopy));

        // different types -> returns false
        assertFalse(sortBillCommand.equals(1));

        // null -> returns false
        assertFalse(sortBillCommand.equals(null));

        // different criteria -> returns false
        assertFalse(sortBillCommand.equals(sortBillCommandDifferent));

        // different order -> returns false
        assertFalse(sortBillCommand.equals(sortBillCommandDifferent2));

    }
}
