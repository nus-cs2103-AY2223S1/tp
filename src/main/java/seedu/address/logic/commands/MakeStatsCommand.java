package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class MakeStatsCommand extends Command {

    public static final String COMMAND_WORD = "makeStats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_STATS_MESSAGE = "Opened statistics window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_STATS_MESSAGE,
                false, true, false);
    }
}
