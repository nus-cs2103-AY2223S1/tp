package seedu.uninurse.model.remark;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.remark.exceptions.DuplicateRemarkException;
import seedu.uninurse.model.remark.exceptions.RemarkNotFoundException;
import seedu.uninurse.model.remark.exceptions.UnmodifiedRemarkException;

/**
 * Represents a unique list of remarks for a particular patient.
 * Two remarks with the same descriptions are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class RemarkList implements GenericList<Remark> {
    private final List<Remark> internalRemarkList;

    /**
     * Constructs an empty {@code RemarkList}.
     */
    public RemarkList() {
        this.internalRemarkList = new ArrayList<>();
    }

    /**
     * Constructs a {@code RemarkList}.
     * @param remarkList The given list of remarks.
     */
    public RemarkList(List<Remark> remarkList) {
        requireNonNull(remarkList);
        this.internalRemarkList = remarkList;
    }

    @Override
    public RemarkList add(Remark remark) {
        requireNonNull(remark);
        if (this.internalRemarkList.contains(remark)) {
            throw new DuplicateRemarkException();
        }

        List<Remark> updatedRemarks = new ArrayList<>(internalRemarkList);
        updatedRemarks.add(remark);
        return new RemarkList(updatedRemarks);
    }

    @Override
    public RemarkList delete(int index) {
        try {
            List<Remark> updatedRemarks = new ArrayList<>(internalRemarkList);
            updatedRemarks.remove(index);
            return new RemarkList(updatedRemarks);
        } catch (IndexOutOfBoundsException e) {
            throw new RemarkNotFoundException();
        }
    }

    @Override
    public RemarkList edit(int index, Remark editedRemark) {
        try {
            List<Remark> updatedRemarks = new ArrayList<>(internalRemarkList);
            if (updatedRemarks.get(index).equals(editedRemark)) {
                throw new UnmodifiedRemarkException();
            }
            updatedRemarks.set(index, editedRemark);
            return new RemarkList(updatedRemarks);
        } catch (IndexOutOfBoundsException e) {
            throw new RemarkNotFoundException();
        }
    }

    @Override
    public Remark get(int index) {
        try {
            return internalRemarkList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new RemarkNotFoundException();
        }
    }

    @Override
    public int size() {
        return internalRemarkList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalRemarkList.isEmpty();
    }

    @Override
    public List<Remark> getInternalList() {
        return Collections.unmodifiableList(internalRemarkList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        internalRemarkList.forEach(r -> {
            int index = internalRemarkList.indexOf(r);
            if (index == 0) {
                builder.append(index + 1)
                        .append(". ")
                        .append(r);
            } else {
                builder.append("\n")
                        .append(index + 1)
                        .append(". ")
                        .append(r);
            }
        });
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkList // instanceof handles nulls
                && internalRemarkList.equals(((RemarkList) other).internalRemarkList));
    }

    @Override
    public int hashCode() {
        return internalRemarkList.hashCode();
    }
}
