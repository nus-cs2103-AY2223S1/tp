package seedu.uninurse.logic.commands;

/**
 * Represents a command which is undo-able.
 */
public abstract class UndoableCommand extends Command {
    /**
     * Returns whether the command is undo-able.
     */
    @Override
    public boolean isUndoable() {
        return true;
    }
}
