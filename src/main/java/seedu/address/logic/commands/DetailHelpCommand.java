package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.ui.MainPanelName;

/**
 * Format full help instructions for every command for display.
 */
public class DetailHelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "If you want to show help for specific command, pass command word as parameter.\n"
            + "Parameter: ["
            + BackCommand.COMMAND_WORD + "|"
            + DeleteAttributeCommand.COMMAND_WORD + "|"
            + SetCommand.COMMAND_WORD + "|"
            + DetailHelpCommand.COMMAND_WORD + "|"
            + ExitCommand.COMMAND_WORD + "]\n"
            + "Example: " + COMMAND_WORD + ", " + COMMAND_WORD + " " + SetCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private String helpMessage;

    private boolean showHelpPanel;

    /**
     * Creates an HelpCommand to show help message and help panel.
     */
    public DetailHelpCommand() {
        this.helpMessage = SHOWING_HELP_MESSAGE;
        this.showHelpPanel = true;
    }

    /**
     * Creates an HelpCommand to show usage instruction.
     */
    public DetailHelpCommand(String helpMessage) {
        this.helpMessage = helpMessage;
        this.showHelpPanel = false;
    }

    @Override
    public CommandResult execute(Model model) {
        assert helpMessage != null;

        return new CommandResult(helpMessage, showHelpPanel, false, false);
    }

    public static boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.Detail) || name.equals(MainPanelName.DetailHelp);
    }
}
