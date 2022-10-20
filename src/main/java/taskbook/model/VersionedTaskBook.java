package taskbook.model;

import java.util.ArrayList;

/**
 * TaskBook with version history.
 */
public class VersionedTaskBook {

    protected static final String INVALID_UNDO_ACTION = "There are no actions left to undo.";
    protected static final String INVALID_REDO_ACTION = "There are no actions left to redo.";
    private static final int DEFAULT_CAPACITY = 15;
    private static final int MAXIMUM_CAPACITY = 100;
    private final int capacity;
    private ArrayList<TaskBook> taskBookStateList;
    private int pointer;

    /**
     * Creates a VersionedTaskBook with the given {@code capacity} and {@code initialState}.
     */
    public VersionedTaskBook(int capacity, TaskBook initialState) {
        // Defensively ensure that the capacity does not ensure a certain threshold.
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }

        this.capacity = capacity;
        taskBookStateList = new ArrayList<>();
        taskBookStateList.add(initialState);
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
    private boolean isDuplicateCommit(TaskBook state) {
        assert state != null;

        TaskBook newestState = taskBookStateList.get(pointer);
        return state.equals(newestState);
    }

    private void pruneFutureStatesIfRequired() {
        if (pointer + 1 == taskBookStateList.size() - 1) {
            return;
        }

        ArrayList<TaskBook> prunedList = new ArrayList<>(capacity);
        for (int i = 0; i < pointer; i++) {
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
     * If the state of the TaskBook remains the same, nothing is committed.
     * After committing, all states in the "future" are removed.
     * In other words, after committing, an immediate redo would do nothing.
     */
    public void commit(TaskBook state) {
        assert taskBookStateList != null && state != null;

        if (isDuplicateCommit(state)) {
            return;
        }

        // Defensively commit a copy of the state instead.
        TaskBook copy = new TaskBook(state);
        taskBookStateList.add(copy);
        pruneFutureStatesIfRequired();
        pruneToCapacityIfRequired();

        // Set the pointer to point to the newest command.
        pointer = taskBookStateList.size() - 1;
    }

    /**
     * Returns true if an undo operation is valid.
     */
    public boolean canUndo() {
        return pointer - 1 >= 0;
    }

    /**
     * Reverts the state to the previous state and returns it.
     *
     * @throws InvalidActionException if there are no actions left to undo.
     */
    public TaskBook undo() throws InvalidActionException {
        assert taskBookStateList != null;

        if (!canUndo()) {
            throw new InvalidActionException(INVALID_UNDO_ACTION);
        }

        pointer--;
        return taskBookStateList.get(pointer);
    }

    /**
     * Returns true if a redo operation is valid.
     */
    public boolean canRedo() {
        return pointer + 1 < taskBookStateList.size();
    }

    /**
     * Reverts the state to a previously undone state and returns it.
     *
     * @throws InvalidActionException if there are no actions left to redo.
     */
    public TaskBook redo() throws InvalidActionException {
        assert taskBookStateList != null;

        if (!canRedo()) {
            throw new InvalidActionException(INVALID_REDO_ACTION);
        }

        pointer++;
        return taskBookStateList.get(pointer);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof VersionedTaskBook // instanceof handles nulls
            && taskBookStateList.equals(((VersionedTaskBook) other).taskBookStateList)
            && pointer == ((VersionedTaskBook) other).pointer);
    }

    /**
     * Represents an error due to an invalid action on a {@link VersionedTaskBook}.
     */
    public static class InvalidActionException extends Exception {

        /**
         * Constructs an {@code InvalidActionException} with the given {@code message}.
         */
        public InvalidActionException(String message) {
            super(message);
        }
    }
}
