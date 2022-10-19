package seedu.uninurse.model;

import seedu.uninurse.logic.commands.CommandResult;

/**
 * A snapshot of a UninurseBook after a command.
 */
public class UninurseBookSnapshot extends UninurseBook {
    private final CommandResult commandResult;

    /**
     * Creates an UninurseBookSnapshot using the Persons in the {@code toBeCopied}
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied) {
        super(toBeCopied);
        this.commandResult = new CommandResult("");
    }

    /**
     * Creates an UninurseBookSnapshot using the Persons in the {@code toBeCopied}
     */
    public UninurseBookSnapshot(ReadOnlyUninurseBook toBeCopied, CommandResult commandResult) {
        super(toBeCopied);
        this.commandResult = commandResult;
    }

    /**
     * Returns commandResult.
     */
    public CommandResult getCommandResult() {
        return this.commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UninurseBookSnapshot // instanceof handles nulls
                && super.equals(other) && commandResult.equals(((UninurseBookSnapshot) other).commandResult));
    }
}
