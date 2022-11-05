package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Reused from https://github.com/se-edu/addressbook-level4 with small changes.
 *
 * @@author se-edu
 */

public class VersionedSurvin extends Survin {
    private final List<ReadOnlySurvin> survinStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedSurvin
     *
     * @param survin
     */
    public VersionedSurvin(ReadOnlySurvin survin) {
        super(survin);

        survinStateList = new ArrayList<>();
        survinStateList.add(new Survin(survin));
        currentStatePointer = 0;
    }

    /**
     * Commits the current survin state into {@code survinStateList}.
     */
    public void commit() {
        removeUndoneStates();
        survinStateList.add(new Survin(this));
        currentStatePointer++;
    }

    /**
     * Restores survin to a previous state if survin is undoable.
     */
    public void undo() {
        assert canUndo() : "should be undoable";

        currentStatePointer--;
        resetData(survinStateList.get(currentStatePointer));
    }

    /**
     * Returns true if there exists previous states that we can undo to.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VersionedSurvin)) {
            return false;
        }
        VersionedSurvin otherVersionedSurvin = (VersionedSurvin) other;
        return super.equals(otherVersionedSurvin)
                && survinStateList.equals(otherVersionedSurvin.survinStateList)
                && currentStatePointer == otherVersionedSurvin.currentStatePointer;
    }

    private void removeUndoneStates() {
        for (int i = currentStatePointer + 1; i < survinStateList.size(); ++i) {
            survinStateList.remove(i);
        }
    }

}
