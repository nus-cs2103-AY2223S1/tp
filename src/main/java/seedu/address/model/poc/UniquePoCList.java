package seedu.address.model.poc;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.poc.exceptions.PoCNotFoundException;
import seedu.address.model.poc.exceptions.DuplicatePoCException;

/**
 * A list of companies that enforces uniqueness between its elements and does not allow nulls.
 * A company is considered unique by comparing using {@code Company#isSameCompany(Company)}.
 * As such, adding and updating of companies uses Company#isSameCompany(Company) for equality to ensure that the
 * company being added or updated is unique in terms of identity in the UniqueCompanyList.
 * However, the removal of a company uses Company#equals(Object) to ensure that the company with exactly
 * the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see PoC#isSamePerson(PoC)
 */
public class UniquePoCList implements Iterable<PoC> {

    private final ObservableList<PoC> internalList = FXCollections.observableArrayList();
    private final ObservableList<PoC> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent company as the given argument.
     */
    public boolean contains(PoC toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a company to the list.
     * The company must not already exist in the list.
     */
    public void add(PoC toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePoCException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the list.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the list.
     */
    public void setCompany(PoC target, PoC editedPoC) {
        requireAllNonNull(target, editedPoC);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PoCNotFoundException();
        }

        if (!target.isSamePerson(editedPoC) && contains(editedPoC)) {
            throw new DuplicatePoCException();
        }

        internalList.set(index, editedPoC);
    }

    /**
     * Removes the equivalent company from the list.
     * The company must exist in the list.
     */
    public void remove(PoC toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PoCNotFoundException();
        }
    }

    public void setCompanies(UniquePoCList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code companies}.
     * {@code companies} must not contain duplicate companies.
     */
    public void setCompanies(List<PoC> companies) {
        requireAllNonNull(companies);
        if (!companiesAreUnique(companies)) {
            throw new DuplicatePoCException();
        }

        internalList.setAll(companies);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PoC> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PoC> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePoCList // instanceof handles nulls
                && internalList.equals(((UniquePoCList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code companies} contains only unique companies.
     */
    private boolean companiesAreUnique(List<PoC> companies) {
        for (int i = 0; i < companies.size() - 1; i++) {
            for (int j = i + 1; j < companies.size(); j++) {
                if (companies.get(i).isSamePerson(companies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
