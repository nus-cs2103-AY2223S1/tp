package seedu.address.logic.commands.profile;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a Profile command with hidden internal logic and the ability to be executed.
 */
public abstract class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";

    public static final String OPTION_UNKNOWN = "That is not a valid option flag.";

    /**
     * Executes the Profile command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
