package taskbook.model;

import java.util.ArrayList;

/**
 * TaskBook with version history.
 */
public class VersionedTaskBook extends TaskBook {

    private static final int DEFAULT_CAPACITY = 15;
    private static final int MAXIMUM_CAPACITY = 100;
    private final int capacity;
    private ArrayList<TaskBook> taskBookStateList;
    private int pointer;

    /**
     * Creates a VersionedTaskBook with the given {@code capacity} and {@code initialState}.
     * Defensively creates a copy of the {@code initialState}.
     */
    public VersionedTaskBook(int capacity, TaskBook initialState) {
        super(initialState);

        // Defensively ensure that the capacity does not ensure a certain threshold.
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }

        this.capacity = capacity;
        taskBookStateList = new ArrayList<>();
        taskBookStateList.add(new TaskBook(initialState));
        pointer = 0;
    }

    /**
     * Creates a VersionedTaskBook with the default capacity and the given {@code initialState}.
     */
    public VersionedTaskBook(TaskBook initialState) {
        this(DEFAULT_CAPACITY, initialState);
    }

    /**
     * Creates a VersionedTaskBook with the default capacity and TaskBook as the initial state.
     */
    public VersionedTaskBook() {
        this(new TaskBook());
    }

    /**
     * Creates a VersionedTaskBook with the equal to the given {@code toBeCopied}.
     */
    public VersionedTaskBook(VersionedTaskBook toBeCopied) {
        capacity = toBeCopied.capacity;
        taskBookStateList = new ArrayList<>();
        pointer = toBeCopied.pointer;

        taskBookStateList.addAll(toBeCopied.taskBookStateList);
    }

    /**
     * Returns true if the given state matches the newest state in the version history.
     * Ensure that commits that with no state change do not clog the version history.
     */
    private boolean isDuplicateCommit() {
        TaskBook newestState = taskBookStateList.get(pointer);
        return newestState.equals(this);
    }

    private void pruneFutureStatesIfRequired() {
        if (pointer == taskBookStateList.size() - 1) {
            return;
        }

        ArrayList<TaskBook> prunedList = new ArrayList<>(capacity);
        for (int i = 0; i <= pointer; i++) {
            TaskBook state = taskBookStateList.get(i);
            prunedList.add(state);
        }

        taskBookStateList = prunedList;
    }

    private void pruneToCapacityIfRequired() {
        assert taskBookStateList != null;

        if (taskBookStateList.size() <= capacity * 2) {
            return;
        }

        ArrayList<TaskBook> prunedList = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            TaskBook state = taskBookStateList.get(capacity + i + 1);
            prunedList.add(state);
        }

        taskBookStateList = prunedList;
    }

    /**
     * Commits a copy of the given state into the version history.
     * If the state of the TaskBook is the same, nothing is added to the version history.
     * After committing, all states in the "future" are removed.
     * In other words, after committing, an immediate redo always does nothing.
     */
    public void commit() {
        assert taskBookStateList != null;

        if (isDuplicateCommit()) {
            return;
        }

        pruneFutureStatesIfRequired();
        // Defensively commit a copy of the state instead.
        TaskBook copy = new TaskBook(this);
        taskBookStateList.add(copy);
        pruneToCapacityIfRequired();

        // Set the pointer to point to the newest command.
        pointer = taskBookStateList.size() - 1;
    }
    private void setPointedAsCurrentData() {
        TaskBook pointedState = taskBookStateList.get(pointer);
        TaskBook copy = new TaskBook(pointedState);
        resetData(copy);
    }

    /**
     * Returns true if an undo operation is valid.
     */
    public boolean canUndo() {
        return pointer - 1 >= 0;
    }

    /**
     * Reverts the state to the previous state and returns it.
     * Does nothing if there is no state to revert to.
     */
    public void undo() {
        assert taskBookStateList != null;

        if (!canUndo()) {
            return;
        }

        pointer--;
        setPointedAsCurrentData();
    }

    /**
     * Returns true if a redo operation is valid.
     */
    public boolean canRedo() {
        return pointer + 1 < taskBookStateList.size();
    }

    /**
     * Reverts the state to a previously undone state and returns it.
     * Does nothing if there is no state to revert to.
     */
    public void redo() {
        assert taskBookStateList != null;

        if (!canRedo()) {
            return;
        }

        pointer++;
        setPointedAsCurrentData();
    }

    /**
     * Returns true if the underlying TaskBook at the current pointer is equivalent to the given {@code other} object.
     * @see TaskBook#equals(Object) TaskBook's equality comparison for how it is determined.
     */
    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    /**
     * Returns true if the given {@code other} is equivalent.
     * They are equivalent iff they share the same state list and pointer position.
     * Capacity is ignored.
     */
    public boolean isEquivalentTo(VersionedTaskBook other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        }

        return taskBookStateList.equals(other.taskBookStateList) && pointer == other.pointer;
    }
}
