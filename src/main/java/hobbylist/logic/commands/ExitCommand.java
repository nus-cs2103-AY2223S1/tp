package hobbylist.logic.commands;

import hobbylist.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting HobbyList as requested ...";

    private static String commandWord = "exit";

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
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
