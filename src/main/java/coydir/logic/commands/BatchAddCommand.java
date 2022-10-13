package coydir.logic.commands;

import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import static coydir.commons.util.CollectionUtil.requireAllNonNull;

public class BatchAddCommand extends Command{
    public static final String COMMAND_WORD = "batchadd";
    public static final String MESSAGE_ARGUMENTS = "Filename: %1$s";
    public static final String MESSAGE_USAGE = "placeholder";

    private final String filename;

    /**
     * @param filename of the
     */
    public BatchAddCommand(String filename) {
        requireAllNonNull(filename);
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS,filename));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BatchAddCommand)) {
            return false;
        }

        // state check
        BatchAddCommand e = (BatchAddCommand) other;
        return filename.equals(e.filename);
    }
}
