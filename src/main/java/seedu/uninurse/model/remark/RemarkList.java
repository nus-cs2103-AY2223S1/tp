package seedu.uninurse.model.remark;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.uninurse.model.GenericList;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.ModificationType;
import seedu.uninurse.model.remark.exceptions.DuplicateRemarkException;
import seedu.uninurse.model.remark.exceptions.RemarkNotFoundException;

/**
 * Represents a unique list of remarks for a particular patient.
 * Two remarks with the same descriptions are considered duplicates and are hence, not allowed.
 * Supports a minimal set of list operations.
 */
public class RemarkList implements GenericList<Remark> {
    private final List<Remark> internalRemarkList;

    /**
     * Constructs an empty RemarkList.
     */
    public RemarkList() {
        this.internalRemarkList = new ArrayList<>();
    }

    /**
     * Constructs a RemarkList.
     *
     * @param remarkList The given list of remarks.
     */
    public RemarkList(List<Remark> remarkList) {
        requireAllNonNull(remarkList);
        this.internalRemarkList = remarkList;
    }

    @Override
    public RemarkList add(Remark remark) {
        requireAllNonNull(remark);
        if (internalRemarkList.contains(remark)) {
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
        requireAllNonNull(editedRemark);
        try {
            List<Remark> updatedRemarks = new ArrayList<>(internalRemarkList);
            updatedRemarks.set(index, editedRemark);

            if (internalRemarkList.contains(editedRemark)) {
                throw new DuplicateRemarkException();
            }

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
    public List<ListModificationPair> getDiff(GenericList<Remark> otherRemarkList) {
        List<ListModificationPair> listModificationPairs = new ArrayList<>();

        if (equals(otherRemarkList)) {
            return listModificationPairs;
        }

        if (size() == 0 && otherRemarkList.size() == 1) {
            listModificationPairs.add(new ListModificationPair(ModificationType.ADD, 0));
            return listModificationPairs;
        }

        if (size() == 1 && otherRemarkList.size() == 0) {
            listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, 0));
            return listModificationPairs;
        }

        int pointer = 0;
        while (get(pointer).equals(otherRemarkList.get(pointer))) {
            pointer++;
            if (pointer == size() || pointer == otherRemarkList.size()) {
                if (size() < otherRemarkList.size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.ADD, pointer));
                } else if (otherRemarkList.size() < size()) {
                    listModificationPairs.add(new ListModificationPair(ModificationType.DELETE, pointer));
                }
                break;
            }
        }
        listModificationPairs.add(new ListModificationPair(ModificationType.EDIT, pointer));
        return listModificationPairs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        internalRemarkList.forEach(r -> {
            int index = internalRemarkList.indexOf(r);
            if (index == 0) {
                sb.append(index + 1)
                        .append(". ")
                        .append(r);
            } else {
                sb.append("\n")
                        .append(index + 1)
                        .append(". ")
                        .append(r);
            }
        });
        return sb.toString();
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
