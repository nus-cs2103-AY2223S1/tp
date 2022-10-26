package seedu.taassist.logic.commands.storagecommands;

import seedu.taassist.logic.commands.CommandResult;

/**
 * Represents a command result that contains a storage command.
 */
public class CommandResultWithStorageCommand extends CommandResult {

    private static final String EMPTY_FEEDBACK = "";

    private StorageCommand storageCommand;

    /**
     * Constructs a {@code CommandResultWithStorageCommand} with the specified {@code StorageCommand}.
     */
    public CommandResultWithStorageCommand(StorageCommand storageCommand) {
        super(EMPTY_FEEDBACK);
        this.storageCommand = storageCommand;
    }

    public StorageCommand getStorageCommand() {
        return storageCommand;
    }
}
