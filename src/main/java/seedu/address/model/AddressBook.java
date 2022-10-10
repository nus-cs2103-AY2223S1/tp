package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Entry;
import seedu.address.model.person.UniqueEntryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyPennyWise {

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

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyPennyWise toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setExpenditure(List<Entry> entries) {
        this.expenditure.setEntries(entries);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setIncome(List<Entry> entries) {
        this.income.setEntries(entries);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPennyWise newData) {
        requireNonNull(newData);

        setExpenditure(newData.getExpenditureList());
        setIncome(newData.getIncomeList());
    }

    //// entry-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasExpenditure(Entry entry) {
        requireNonNull(entry);
        return expenditure.contains(entry);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addExpenditure(Entry e) {
        expenditure.add(e);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setExpenditure(Entry target, Entry editedEntry) {
        requireNonNull(editedEntry);

        expenditure.setEntries(target, editedEntry);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeExpenditure(Entry key) {
        expenditure.remove(key);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasIncome(Entry entry) {
        requireNonNull(entry);
        return income.contains(entry);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addIncome(Entry e) {
        income.add(e);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setIncome(Entry target, Entry editedEntry) {
        requireNonNull(editedEntry);

        income.setEntries(target, editedEntry);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
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
                || (other instanceof AddressBook // instanceof handles nulls
                && expenditure.equals(((AddressBook) other).expenditure)
                && income.equals(((AddressBook) other).income));
    }

    @Override
    public int hashCode() {
        return expenditure.hashCode();
    }
}
