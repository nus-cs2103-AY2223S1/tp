package seedu.address.logic.commands.event;

import seedu.address.logic.commands.Command;

/**
 * Represents a Event command with hidden internal logic and the ability to be executed.
 */
public abstract class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String OPTION_UNKNOWN = "That is not a valid option flag. "
            + " Please use one of the following valid flags: "
            + "None implemented";

}
