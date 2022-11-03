package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bill.Bill;

public class SortBillCommandTest {
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void execute_validSortBillName_success() {
        /*
        Patient patientToSelect = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectPatientCommand.MESSAGE_SUCCESS, patientToSelect);

        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectPatient(patientToSelect);

        assertCommandSuccess(selectPatientCommand, model, expectedMessage, expectedModel);
        */
        class AppointmentComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getAppointment().toString().compareTo(second.getAppointment().toString());
            }
        }
        SortBillCommand SortBillCommand = new SortBillCommand("name", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "name");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new AppointmentComparator(), true);
        assertCommandSuccess(SortBillCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidSortBillCriteria_throwCommandException() {
        /*
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
         */

        SortBillCommand SortBillCommand = new SortBillCommand("invalid", true);
        assertCommandFailure(SortBillCommand, model, SortBillCommand.MESSAGE_USAGE);

    }

    @Test
    public void execute_validSortBillAmount_success() {
        class AmountComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getAmount().toString().compareToIgnoreCase(second.getAmount().toString());
            }
        }

        SortBillCommand SortBillCommand = new SortBillCommand("amount", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "amount");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new AmountComparator(), true);
    }

    @Test
    public void execute_validSortBillDate_success() {
        class DateComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getBillDate().toString().compareTo(second.getBillDate().toString());
            }
        }

        SortBillCommand SortBillCommand = new SortBillCommand("date", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "date");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new DateComparator(), true);
    }

    @Test
    public void execute_validSortBillStatus_success() {
        class StatusComparator implements Comparator<Bill> {
            @Override
            public int compare(Bill first, Bill second) {
                return first.getPaymentStatus().toString().compareTo(second.getPaymentStatus().toString());
            }
        }

        SortBillCommand SortBillCommand = new SortBillCommand("status", true);
        String expectedMessage = String.format(SortBillCommand.MESSAGE_SORT_SUCCESS, "status");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortBills(new StatusComparator(), true);
    }

    @Test
    public void equals() {
        /*
        SelectPatientCommand selectFirstCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);
        SelectPatientCommand selectSecondCommand = new SelectPatientCommand(INDEX_SECOND_PATIENT);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectPatientCommand selectFirstCommandCopy = new SelectPatientCommand(INDEX_FIRST_PATIENT);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
         */

        SortBillCommand SortBillCommand = new SortBillCommand("name", true);
        SortBillCommand SortBillCommandCopy = new SortBillCommand("name", true);
        SortBillCommand SortBillCommandDifferent = new SortBillCommand("address", true);
        SortBillCommand SortBillCommandDifferent2 = new SortBillCommand("name", false);

        // same object -> returns true
        assertTrue(SortBillCommand.equals(SortBillCommand));

        // same values -> returns true
        assertTrue(SortBillCommand.equals(SortBillCommandCopy));

        // different types -> returns false
        assertFalse(SortBillCommand.equals(1));

        // null -> returns false
        assertFalse(SortBillCommand.equals(null));

        // different criteria -> returns false
        assertFalse(SortBillCommand.equals(SortBillCommandDifferent));

        // different order -> returns false

    }
}
