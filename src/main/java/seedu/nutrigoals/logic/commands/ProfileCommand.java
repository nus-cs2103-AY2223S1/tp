package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;

/**
 * Displays the user's details
 */
public class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";
    public static final String MESSAGE_SUCCESS = "Profile created: \n %s";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getUserDetails()));
    }
}
