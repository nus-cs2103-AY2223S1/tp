package seedu.pennywise.model;

import javafx.collections.ObservableList;
import seedu.pennywise.model.entry.Entry;

/**
 * Unmodifiable view of an pennywise book
 */
public interface ReadOnlyPennyWise {

    /**
     * Returns an unmodifiable view of the expenditure list.
     * This list will not contain any duplicate expenditure.
     */
    ObservableList<Entry> getExpenditureList();

    /**
     * Returns an unmodifiable view of the income list.
     * This list will not contain any duplicate income.
     */
    ObservableList<Entry> getIncomeList();

}
