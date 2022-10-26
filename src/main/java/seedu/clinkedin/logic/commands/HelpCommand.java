package seedu.clinkedin.logic.commands;

import seedu.clinkedin.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Entering '" + COMMAND_WORD
            + "' alone will open up a separate window to show help instructions for all commands.\n"
            + "Entering '" + COMMAND_WORD + " <command>' will show help instructions for the specified command.\n"
            + "Example: \n\t- " + COMMAND_WORD
            + "\n\t- " + COMMAND_WORD + " add";

    private final boolean showHelpWindow;

    private final String shownHelpMessage;

    /**
     * Creates a default HelpCommand to show help instructions for all commands.
     * This will open up a separate window to show help instructions for all
     * commands.
     */
    public HelpCommand() {
        this.showHelpWindow = true;
        this.shownHelpMessage = "Opened help window.";
    }

    /**
     * Creates a HelpCommand to show help instructions for the specified command.
     * @param commandMessage The help message to be shown.
     */
    public HelpCommand(String commandMessage) {
        this.showHelpWindow = false;
        this.shownHelpMessage = commandMessage;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(shownHelpMessage, showHelpWindow, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                        && showHelpWindow == ((HelpCommand) other).showHelpWindow
                        && shownHelpMessage.equals(((HelpCommand) other).shownHelpMessage));
    }
}
