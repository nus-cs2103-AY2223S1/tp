package seedu.address.logic.commands;

import java.util.Stack;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Tracks past undoable commands.
 */
public class CommandManager {
    public static final String MESSAGE_EMPTY_UNDOSTACK = "No commands to undo.\nUndoable Commands:\nCreate, Delete, Update, Clear, Description, AddMeeting, DeleteMeeting";
    public static final String MESSAGE_EMPTY_REDOSTACK = "No commands to redo.";

    private final Stack<UndoableCommand> undoStack;
    private final Stack<UndoableCommand> redoStack;

    /**
     * Constructs a {@code CommandManager}.
     */
    public CommandManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Undoes the last available undoable command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult undo(Model model) throws CommandException {
        if (undoStack.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_UNDOSTACK);
        }

        UndoableCommand lastCommand = undoStack.pop();
        pushToRedoStack(lastCommand);
        return lastCommand.undo(model);
    }

    /**
     * Redoes the last available undone command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult redo(Model model) throws CommandException {
        if (redoStack.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_REDOSTACK);
        }

        UndoableCommand lastCommand = redoStack.pop();
        pushToUndoStack(lastCommand);
        return lastCommand.redo(model);
    }

    /**
     * Push a new undoable command onto the undoStack.
     *
     * @param command Command that was executed
     */
    public void pushNewCommand(Command command) {
        if (command instanceof UndoableCommand) {
            UndoableCommand undoableCommand = (UndoableCommand) command;
            undoStack.push(undoableCommand);
            clearRedoStack();
        }
    }

    /**
     * Push an undoable command onto the undoStack.
     *
     * @param undoableCommand Command to be added to undoStack
     */
    private void pushToUndoStack(UndoableCommand undoableCommand) {
        undoStack.push(undoableCommand);
    }

    /**
     * Pushes an undoable command onto the redoStack.
     *
     * @param undoableCommand Command that was undone
     */
    private void pushToRedoStack(UndoableCommand undoableCommand) {
        redoStack.push(undoableCommand);
    }

    /**
     * Clears the commands in the redoStack.
     */
    private void clearRedoStack() {
        redoStack.clear();
    }
}
