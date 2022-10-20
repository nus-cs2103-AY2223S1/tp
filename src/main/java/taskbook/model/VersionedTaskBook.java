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
     * Defensively ensure that commits that do nothing do not clog the version history.
     */
    private boolean isDuplicateCommit(TaskBook state) {
        assert state != null;

        TaskBook newestState = taskBookStateList.get(pointer);
        return state.equals(newestState);
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
        pruneToCapacityIfRequired();

        // Set the pointer to point to the newest command.
        pointer = taskBookStateList.size() - 1;
    }

    /**
     * Reverts the state to the previous state and returns it.
     */
    public TaskBook undo() {
        assert taskBookStateList != null;

        pointer = Math.max(0, pointer - 1);
        return taskBookStateList.get(pointer);
    }

    /**
     * Reverts the state to a previously undone state and returns it.
     */
    public TaskBook redo() {
        assert taskBookStateList != null;

        pointer = Math.min(taskBookStateList.size() - 1, pointer + 1);
        return taskBookStateList.get(pointer);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof VersionedTaskBook // instanceof handles nulls
            && taskBookStateList.equals(((VersionedTaskBook) other).taskBookStateList)
            && pointer == ((VersionedTaskBook) other).pointer);
    }
}
