package seedu.workbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code WorkBook} that keeps track of its own history.
 */
public class VersionedWorkBook extends WorkBook {

    private final List<ReadOnlyWorkBook> workBookStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedWorkBook using the Internships in the {@code initialState}
     */
    public VersionedWorkBook(ReadOnlyWorkBook initialState) {
        super(initialState);

        workBookStateList = new ArrayList<>();
        workBookStateList.add(new WorkBook(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        workBookStateList.add(new WorkBook(this));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        workBookStateList.subList(currentStatePointer + 1, workBookStateList.size()).clear();
    }

    /**
     * Restores the address book to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(workBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has address book states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedWorkBook)) {
            return false;
        }

        VersionedWorkBook otherVersionedAddressBook = (VersionedWorkBook) other;

        // state check
        return super.equals(otherVersionedAddressBook)
                && workBookStateList.equals(otherVersionedAddressBook.workBookStateList)
                && currentStatePointer == otherVersionedAddressBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at the start of workBookState list, unable to undo changes.");
        }
    }
}
