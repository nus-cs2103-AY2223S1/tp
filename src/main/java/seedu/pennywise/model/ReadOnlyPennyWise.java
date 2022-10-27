package seedu.pennywise.model;

import javafx.collections.ObservableList;
import seedu.pennywise.model.entry.Entry;

/**
 * Unmodifiable view of PennyWise.
 */
public interface ReadOnlyPennyWise {

    /**
     * Returns an unmodifiable view of the expenditure list.
     * Invariant: This list will not contain any duplicate expenditure.
     */
    ObservableList<Entry> getExpenditureList();

    /**
     * Returns an unmodifiable view of the income list.
     * Invariant: This list will not contain any duplicate income.
     */
    ObservableList<Entry> getIncomeList();

}
