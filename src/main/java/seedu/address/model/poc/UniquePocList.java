package seedu.address.model.poc;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.poc.exceptions.PocNotFoundException;
import seedu.address.model.poc.exceptions.DuplicatePocException;

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
 * @see Poc#isSamePoc(Poc)
 */
public class UniquePocList implements Iterable<Poc> {

    private final ObservableList<Poc> internalList = FXCollections.observableArrayList();
    private final ObservableList<Poc> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent company as the given argument.
     */
    public boolean contains(Poc toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePoc);
    }

    /**
     * Adds a company to the list.
     * The company must not already exist in the list.
     */
    public void add(Poc toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePocException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the list.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the list.
     */
    public void setPoc(Poc target, Poc editedPoC) {
        requireAllNonNull(target, editedPoC);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PocNotFoundException();
        }

        if (!target.isSamePoc(editedPoC) && contains(editedPoC)) {
            throw new DuplicatePocException();
        }

        internalList.set(index, editedPoC);
    }

    /**
     * Removes the equivalent company from the list.
     * The company must exist in the list.
     */
    public void remove(Poc toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PocNotFoundException();
        }
    }

    public void setPocs(UniquePocList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code companies}.
     * {@code companies} must not contain duplicate companies.
     */
    public void setPocs(List<Poc> pocs) {
        requireAllNonNull(pocs);
        if (!pocsAreUnique(pocs)) {
            throw new DuplicatePocException();
        }

        internalList.setAll(pocs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Poc> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Poc> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePocList // instanceof handles nulls
                && internalList.equals(((UniquePocList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code companies} contains only unique companies.
     */
    private boolean pocsAreUnique(List<Poc> pocs) {
        for (int i = 0; i < pocs.size() - 1; i++) {
            for (int j = i + 1; j < pocs.size(); j++) {
                if (pocs.get(i).isSamePoc(pocs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
