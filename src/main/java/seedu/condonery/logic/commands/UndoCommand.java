package seedu.condonery.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.logic.commands.exceptions.EmptyQueueException;
import seedu.condonery.model.Model;

/**
 * Undo the previous command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Successfully undid previous command.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CommandQueue commandQueue = model.getCommandQueue();
        try {
            Command previousCommand = commandQueue.popCommand();
            model.resetPropertyDirectory();
            model.resetClientDirectory();
            model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
            model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

            for (Command cmd : commandQueue) {
                cmd.execute(model);
            }
            return new CommandResult(MESSAGE_SUCCESS);

        } catch (EmptyQueueException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
