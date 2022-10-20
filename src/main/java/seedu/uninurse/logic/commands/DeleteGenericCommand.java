package seedu.uninurse.logic.commands;

/**
 * Deletes a person or a task.
 */
public abstract class DeleteGenericCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "delete";
}
