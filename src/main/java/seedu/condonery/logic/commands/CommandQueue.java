package seedu.condonery.logic.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seedu.condonery.logic.commands.exceptions.EmptyQueueException;

/**
 * Keeps track of the various commands executed by user in the Application's lifecycle.
 */
public class CommandQueue implements Iterable<Command> {
    private final List<Command> commandQueue = new ArrayList<>();

    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    /**
     * Pops the last command from the queue.
     *
     * @return previous command executed
     * @throws EmptyQueueException if queue is empty.
     */
    public Command popCommand() throws EmptyQueueException {
        int size;
        if ((size = commandQueue.size()) > 0) {
            Command result = commandQueue.get(size - 1);
            commandQueue.remove(size - 1);
            return result;
        } else {
            throw new EmptyQueueException("No previous commands found!");
        }
    }
    @Override
    public Iterator<Command> iterator() {
        return commandQueue.iterator();
    }

}
