package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.NuScheduler;

/**
 * Clears the NUScheduler.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "NUScheduler has been cleared!";
    public static final String MESSAGE_HELP = "Clears all the profiles and events in NUScheduler.";
    public static final String MESSAGE_FAILURE = "Please remove extra inputs after clear to clear NUScheduler.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setNuScheduler(new NuScheduler());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
