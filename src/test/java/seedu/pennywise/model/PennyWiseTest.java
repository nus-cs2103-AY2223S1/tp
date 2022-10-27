package seedu.pennywise.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.testutil.Assert.assertThrows;
import static seedu.pennywise.testutil.TypicalEntry.ALLOWANCE;
import static seedu.pennywise.testutil.TypicalEntry.LUNCH;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.Expenditure;
import seedu.pennywise.model.entry.Income;
import seedu.pennywise.model.entry.exceptions.DuplicateEntryException;
import seedu.pennywise.testutil.ExpenditureBuilder;
import seedu.pennywise.testutil.IncomeBuilder;

public class PennyWiseTest {

    private final PennyWise pennyWise = new PennyWise();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), pennyWise.getExpenditureList());
        assertEquals(Collections.emptyList(), pennyWise.getIncomeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pennyWise.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPennyWise_replacesData() {
        PennyWise newData = getTypicalPennyWise();
        pennyWise.resetData(newData);
        assertEquals(newData, pennyWise);
    }

    @Test
    public void resetData_withDuplicateExpenditure_throwsDuplicateEntryException() {
        // Two entries with the same identity fields
        Entry lunchCopy = new ExpenditureBuilder(LUNCH).build();
        Entry allowanceCopy = new IncomeBuilder(ALLOWANCE).build();
        List<Entry> newExpenditures = Arrays.asList(LUNCH, lunchCopy);
        List<Entry> newIncomes = Arrays.asList(ALLOWANCE, allowanceCopy);
        PennyWiseStub newData = new PennyWiseStub(newExpenditures, newIncomes);
        assertThrows(DuplicateEntryException.class, () -> pennyWise.resetData(newData));
    }

    @Test
    public void hasExpenditure_nullExpenditure_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pennyWise.hasExpenditure(null));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> pennyWise.hasIncome(null));
    }

    @Test
    public void hasExpenditure_expenditureNotInPennyWise_returnsFalse() {
        assertFalse(pennyWise.hasExpenditure(LUNCH));
    }

    @Test
    public void hasIncome_incomeNotInPennyWise_returnsFalse() {
        assertFalse(pennyWise.hasIncome(ALLOWANCE));
    }

    @Test
    public void hasExpenditure_expenditureInPennyWise_returnsTrue() {
        pennyWise.addExpenditure(LUNCH);
        assertTrue(pennyWise.hasExpenditure(LUNCH));
    }

    @Test
    public void hasIncome_incomeInPennyWise_returnsTrue() {
        pennyWise.addIncome(ALLOWANCE);
        assertTrue(pennyWise.hasIncome(ALLOWANCE));
    }

    @Test
    public void hasExpenditure_expenditureWithSameIdentityFieldsInPennyWise_returnsTrue() {
        pennyWise.addExpenditure(LUNCH);
        Expenditure lunchCopy = new ExpenditureBuilder(LUNCH).build();
        assertTrue(pennyWise.hasExpenditure(lunchCopy));
    }

    @Test
    public void hasIncome_incomeWithSameIdentityFieldsInPennyWise_returnsTrue() {
        pennyWise.addIncome(ALLOWANCE);
        Income allowanceCopy = new IncomeBuilder(ALLOWANCE).build();
        assertTrue(pennyWise.hasIncome(allowanceCopy));
    }

    @Test
    public void getExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> pennyWise.getExpenditureList().remove(0));
    }

    @Test
    public void getIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> pennyWise.getIncomeList().remove(0));
    }
    // /**
    //  * A stub ReadOnlyPennyWise whose entries list can violate interface constraints.
    //  */
    private static class PennyWiseStub implements ReadOnlyPennyWise {
        private final ObservableList<Entry> expenditureList = FXCollections.observableArrayList();
        private final ObservableList<Entry> incomeList = FXCollections.observableArrayList();

        PennyWiseStub(Collection<Entry> expenditures, Collection<Entry> incomes) {
            this.expenditureList.setAll(expenditures);
            this.incomeList.setAll(incomes);
        }

        /**
         * Returns an unmodifiable view of the expenditure list.
         * This list will not contain any duplicate expenditure.
         */
        @Override
        public ObservableList<Entry> getExpenditureList() {
            return expenditureList;
        }

        /**
        * Returns an unmodifiable view of the income list.
        * This list will not contain any duplicate income.
        */
        @Override
        public ObservableList<Entry> getIncomeList() {
            return incomeList;
        }
    }

}
