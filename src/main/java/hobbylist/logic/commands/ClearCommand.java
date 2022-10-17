package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.model.HobbyList;
import hobbylist.model.Model;

/**
 * Clears the HobbyList.
 */
public class ClearCommand extends Command {

    public static final String MESSAGE_SUCCESS = "HobbyList has been cleared!";
    private static String commandWord = "clear";

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
        requireNonNull(model);
        model.setHobbyList(new HobbyList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
