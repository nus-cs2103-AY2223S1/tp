package hobbylist.logic.commands;

import hobbylist.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static String commandWord = "help";

    public static final String MESSAGE_USAGE = commandWord + ": Shows program usage instructions.\n"
            + "Example: " + commandWord;

    /**
     * Sets the command word for the command.
     * @param word Word to set command to.
     */
    public static void setCommandWord(String word) {
        commandWord = word;
    }

    /**
     * Gets the command word for the command.
     * @return Command word.
     */
    public static String getCommandWord() {
        return commandWord;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
