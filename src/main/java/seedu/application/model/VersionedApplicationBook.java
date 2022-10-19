package seedu.application.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedApplicationBook extends ApplicationBook {
    
    private List<ReadOnlyApplicationBook> applicationBookStateList;
    private int currentStatePointer;

    public VersionedApplicationBook(ReadOnlyApplicationBook initialState) {
        super(initialState);

        applicationBookStateList = new ArrayList<>();
        applicationBookStateList.add(new ApplicationBook(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code ApplicationBook} state at the end of {@code applicationBookStateList}.
     * States after {@code currentStatePointer}, which have been previously undone, are removed.
     */
    public void commit() {
        applicationBookStateList.subList(currentStatePointer + 1, applicationBookStateList.size()).clear();
        applicationBookStateList.add(new ApplicationBook(this));
        currentStatePointer++;
    }

    /**
     * Restores the previous {@code ApplicationBook} state from its history.
     */
    public void undo() {
        assert canUndo();
        currentStatePointer--;
        resetData(applicationBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code applicationBookStateList} has previous {@code ApplicationBook} states to restore.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Restores a previously undone {@code ApplicationBook} state from its history.
     */
    public void redo() {
        assert canRedo();
        currentStatePointer++;
        resetData(applicationBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code applicationBookStateList} has previously undone {@code ApplicationBook} states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < applicationBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VersionedApplicationBook) // instanceof handles nulls
                && super.equals((VersionedApplicationBook) other)
                && applicationBookStateList.equals(((VersionedApplicationBook) other).applicationBookStateList)
                && currentStatePointer == ((VersionedApplicationBook) other).currentStatePointer;
    }
}
