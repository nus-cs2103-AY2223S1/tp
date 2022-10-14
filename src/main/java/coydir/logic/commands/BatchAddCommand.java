package coydir.logic.commands;

import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;

import java.util.List;

import static coydir.commons.util.CollectionUtil.requireAllNonNull;
import static coydir.logic.commands.AddCommand.MESSAGE_DUPLICATE_PERSON;

public class BatchAddCommand extends Command{
    public static final String COMMAND_WORD = "batchadd";
    public static final String MESSAGE_USAGE = "placeholder";
    public static final String MESSAGE_SUCCESS = "Batch Add Success";

    private final String filename;

    /**
     * Creates an BatchAddCommand to add the multiple {@code Person}
     */
    public BatchAddCommand(String filename) {
        requireAllNonNull(filename);
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<AddCommand> addCommandList= model.batchAdd(this.filename);
        if (addCommandList.isEmpty()) {
            new CommandException("Empty List");
        }
        for (AddCommand item : addCommandList) {
            item.execute(model);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
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
