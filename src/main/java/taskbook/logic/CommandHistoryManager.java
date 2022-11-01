package taskbook.logic;

import java.util.ArrayList;

/**
 * Manages command history of the application.
 */
public class CommandHistoryManager implements CommandHistory {

    private static final int DEFAULT_CAPACITY = 1000;
    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE / 2 - 1;

    /** When size reaches double of the {@code capacity} allocated, the older half of the history is pruned. */
    private final int capacity;
    private int pointer;
    private ArrayList<String> commandsList;

    /**
     * Constructs a {@code CommandHistoryManager}.
     */
    public CommandHistoryManager() {
        capacity = DEFAULT_CAPACITY;
        pointer = 0;
        commandsList = new ArrayList<>();
    }

    /**
     * Constructs a {@code CommandHistoryManager} with the given {@code capacity}.
     */
    public CommandHistoryManager(int capacity) {
        // Defensively ensure that the capacity does not ensure a certain threshold.
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }

        this.capacity = capacity;
        pointer = 0;
        commandsList = new ArrayList<>();
    }

    @Override
    public String getPreviousCommand() {
        assert commandsList != null;

        if (commandsList.isEmpty()) {
            return null;
        }

        pointer = Math.max(0, pointer - 1);
        return commandsList.get(pointer);
    }

    @Override
    public String getNextCommand() {
        assert commandsList != null;

        if (commandsList.isEmpty()) {
            return null;
        }

        pointer = Math.min(commandsList.size(), pointer + 1);
        if (pointer == commandsList.size()) {
            // Pointer is pointing to new "empty" command.
            return "";
        }
        return commandsList.get(pointer);
    }

    /**
     * Prunes the older half of the history to reach the capacity, if required.
     */
    private void pruneToCapacityIfRequired() {
        assert commandsList != null;

        if (commandsList.size() <= capacity * 2) {
            return;
        }

        ArrayList<String> newList = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            String command = commandsList.get(capacity + i + 1);
            newList.add(command);
        }

        commandsList = newList;
    }

    @Override
    public void addCommand(String command) {
        assert commandsList != null && command != null;

        if (command.isEmpty()) {
            return;
        }

        commandsList.add(command);
        pruneToCapacityIfRequired();

        // Set the pointer to point to the newest command.
        pointer = commandsList.size();
    }
}
