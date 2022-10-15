package seedu.foodrem.logic.commands.generalcommands;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.enums.CommandWord.HELP_COMMAND;

import seedu.foodrem.enums.CommandWord;
import seedu.foodrem.logic.commands.Command;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String DEFAULT_HELP_MESSAGE = "Please refer to the user guide.";

    // To be displayed in a new help window
    public static final String USER_GUIDE_URL = "https://ay2223s1-cs2103t-w16-2.github.io/tp/UserGuide";
    private static final String MORE_INFORMATION = "For more information please head to:\n" + USER_GUIDE_URL;
    private static final String COMMAND_WORD = HELP_COMMAND.getCommandWord();
    private static final String HELP_FORMAT_GENERAL =
            "To receive help for a specific command, enter "
                    + "\"" + COMMAND_WORD + " COMMAND_WORD\" "
                    + "in the command box, where COMMAND_WORD is any one of the following:\n"
                    + CommandWord.listAllCommandWords() + "\n\n"
                    + MORE_INFORMATION;
    public static final String NOT_A_COMMAND = "\"%s\" is not a valid command\n\n" + HELP_FORMAT_GENERAL;
    private static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    private static final String HELP_FORMAT_SPECIFIC = "%s\n\n" + HELP_FORMAT_GENERAL;

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
        return String.format(HELP_FORMAT_SPECIFIC, command.getUsage());
    }

    public static String getGeneralHelpMessage() {
        return HELP_FORMAT_GENERAL;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, message);
    }

    public static String getUsage() {
        return HELP_COMMAND.getUsage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && message.equals(((HelpCommand) other).message)); // state check
    }
}
