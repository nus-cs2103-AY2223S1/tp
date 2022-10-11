package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.UniqueEntryList;

/**
 * Wraps all data at the application level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class PennyWise implements ReadOnlyPennyWise {

    private final UniqueEntryList expenditure;

    private final UniqueEntryList income;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        expenditure = new UniqueEntryList();
        income = new UniqueEntryList();
    }

    public PennyWise() {
    }

    /**
     * Creates an PennyWise using the Entries in the {@code toBeCopied}
     */
    public PennyWise(ReadOnlyPennyWise toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the expenditure list with {@code entries}.
     * {@code entries} must not contain duplicate expenditures.
     */
    public void setExpenditure(List<Entry> entries) {
        this.expenditure.setEntries(entries);
    }

    /**
     * Replaces the given expenditure {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the expenditure list.
     * The expenditure identity of {@code editedEntry} must not be the same as another existing expenditure
     * in the expenditure list.
     */
    public void setExpenditure(Entry target, Entry editedEntry) {
        requireNonNull(editedEntry);

        expenditure.setEntries(target, editedEntry);
    }

    /**
     * Replaces the contents of the income list with {@code entries}.
     * {@code entries} must not contain duplicate incomes.
     */
    public void setIncome(List<Entry> entries) {
        this.income.setEntries(entries);
    }

    /**
     * Replaces the given income {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the income list.
     * The income identity of {@code editedEntry} must not be the same as another existing income in the income list.
     */
    public void setIncome(Entry target, Entry editedEntry) {
        requireNonNull(editedEntry);

        income.setEntries(target, editedEntry);
    }

    /**
     * Resets the existing data of this {@code PennyWise} with {@code newData}.
     */
    public void resetData(ReadOnlyPennyWise newData) {
        requireNonNull(newData);

        setExpenditure(newData.getExpenditureList());
        setIncome(newData.getIncomeList());
    }

    //// entry-level operations

    /**
     * Returns true if an expenditure with the same identity as {@code entry} exists in the expenditure list.
     */
    public boolean hasExpenditure(Entry entry) {
        requireNonNull(entry);
        return expenditure.contains(entry);
    }

    /**
     * Adds an expenditure to the expenditure list.
     * The expenditure must not already exist in the expenditure list.
     */
    public void addExpenditure(Entry e) {
        expenditure.add(e);
    }

    /**
     * Removes {@code key} from this {@code PennyWise}.
     * {@code key} must exist in the expenditure list.
     */
    public void removeExpenditure(Entry key) {
        expenditure.remove(key);
    }

    /**
     * Returns true if an income with the same identity as {@code entry} exists in the income list.
     */
    public boolean hasIncome(Entry entry) {
        requireNonNull(entry);
        return income.contains(entry);
    }

    /**
     * Adds an income to the income list.
     * The income must not already exist in the income list.
     */
    public void addIncome(Entry e) {
        income.add(e);
    }

    /**
     * Removes {@code key} from this {@code PennyWise}.
     * {@code key} must exist in the income list.
     */
    public void removeIncome(Entry key) {
        income.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return expenditure.asUnmodifiableObservableList().size() + " entries";
        // TODO: refine later
    }

    @Override
    public ObservableList<Entry> getExpenditureList() {
        return expenditure.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Entry> getIncomeList() {
        return income.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PennyWise // instanceof handles nulls
                && expenditure.equals(((PennyWise) other).expenditure)
                && income.equals(((PennyWise) other).income));
    }

    @Override
    public int hashCode() {
        return expenditure.hashCode();
    }
}
