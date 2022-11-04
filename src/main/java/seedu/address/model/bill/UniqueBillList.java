package seedu.address.model.bill;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.bill.exceptions.BillNotFoundException;
import seedu.address.model.bill.exceptions.DuplicateBillException;

/**
 * A list of bills that enforces uniqueness between its elements and does not allow nulls.
 * A bill is considered unique by comparing using {@code Bill#equals(Object)}. As such,
 * adding and updating of bills uses Bill#equals(Object) for equality so as to ensure that
 * the bill being added or updated is unique in terms of identity in the UniqueBillList.
 *
 * Supports a minimal set of list operations.
 *
 * @see seedu.address.model.bill.Bill#equals(Object)
 */
public class UniqueBillList implements Iterable<Bill> {

    private final ObservableList<Bill> internalList = FXCollections.observableArrayList();
    private final ObservableList<Bill> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent patient as the given argument.
     */
    public boolean contains(Bill toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(bill -> bill.isSameBill(toCheck));
    }

    /**
     * Adds a patient to the list.
     * The patient must not already exist in the list.
     */
    public void add(Bill toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBillException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the list.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the list.
     */
    public void setBill(Bill target, Bill editedBill) {
        requireAllNonNull(target, editedBill);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BillNotFoundException();
        }

        boolean notSameBill = !target.isSameBill(editedBill);
        boolean hasNewBill = contains(editedBill);
        if (notSameBill && hasNewBill) {
            throw new DuplicateBillException();
        }

        internalList.set(index, editedBill);
    }

    /**
     * Removes the equivalent patient from the list.
     * The patient must exist in the list.
     */
    public void remove(Bill toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BillNotFoundException();
        }
    }

    public void setBills(UniqueBillList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setBills(List<Bill> bills) {
        requireAllNonNull(bills);
        if (!billsAreUnique(bills)) {
            throw new DuplicateBillException();
        }

        ArrayList<Bill> sortedBills = new ArrayList<>();
        for (int i = 0; i < bills.size(); i++) {
            Bill bill = bills.get(i);
            sortedBills.add(new Bill(bill.getAppointment(), bill.getAmount(),
                    bill.getBillDate(), new PaymentStatus(bill.getPaymentStatus().toString())));
        }
        internalList.setAll(sortedBills);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Bill> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Bill> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBillList // instanceof handles nulls
                && internalList.equals(((UniqueBillList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code patients} contains only unique patients.
     */
    private boolean billsAreUnique(List<Bill> bills) {
        for (int i = 0; i < bills.size() - 1; i++) {
            for (int j = i + 1; j < bills.size(); j++) {
                if (bills.get(i).equals(bills.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list of bills in alphabetical order by given criteria.
     */
    public void sort(Comparator<Bill> comparator, boolean isAscending) {
        if (isAscending) {
            FXCollections.sort(internalList, comparator);
        } else {
            FXCollections.sort(internalList, comparator.reversed());
        }
    }
}
