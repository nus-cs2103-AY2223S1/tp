package seedu.foodrem.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String DEFAULT_HELP_MESSAGE = "Please refer to the user guide.";

    // To be displayed in a new help window
    // TODO: Update this to match user guide
    public static final String USER_GUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String MORE_INFORMATION = "For more information please head to:\n" + USER_GUIDE_URL;

    public static final String HELP_FOR_ALL_COMMANDS =
            "To receive help for a specific command, enter "
                    + "\"" + HelpCommand.COMMAND_WORD + " COMMAND_WORD\" "
                    + "in the command box, where COMMAND_WORD is any one of the following:\n"
                    + CommandWord.listAllCommandWords() + "\n\n"
                    + MORE_INFORMATION;

    public static final String NOT_A_COMMAND = "\"%s\" is not a valid command\n\n" + HELP_FOR_ALL_COMMANDS;

    private static final String HELP_FOR_SPECIFIC_COMMAND = "%s\n\n" + HELP_FOR_ALL_COMMANDS;

    private static final String COMMAND_WORD = CommandWord.HELP_COMMAND.getCommandWord();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays help for FoodRem.\n"
            + "Example: " + COMMAND_WORD;

    private final String message;

    /**
     * @param message string representing message to be displayed.
     */
    public HelpCommand(String message) {
        requireNonNull(message);
        this.message = message;
    }

    public static String getCommandHelpMessage(CommandWord command) {
        // TODO: Create tests for this method
        return String.format(HelpCommand.HELP_FOR_SPECIFIC_COMMAND, command.getHelp());
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, message);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && message.equals(((HelpCommand) other).message)); // state check
    }
}
