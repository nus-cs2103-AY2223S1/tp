package jeryl.fyp.logic.commands;

import jeryl.fyp.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Parameters: "
            + "[COMMAND]\n"
            + "Example: "
            + COMMAND_WORD + "\n"
            + COMMAND_WORD + " " + AddStudentCommand.COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final String helpMessage;

    private final boolean showHelp;

    /**
     * Private constructor for {@code HelpCommand}.
     * @param helpMessage
     * @param showHelp
     */
    private HelpCommand(String helpMessage, boolean showHelp) {
        this.helpMessage = helpMessage;
        this.showHelp = showHelp;
    }

    /**
     * Default constructor for {@code HelpCommand}.
     */
    public HelpCommand() {
        this(SHOWING_HELP_MESSAGE, true);
    }

    /**
     * Constructor for {@code HelpCommand} on a specific {@Command}.
     */
    public HelpCommand(String helpMessage) {
        this(helpMessage, false);
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(helpMessage, showHelp, false, false , false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && helpMessage.equals(((HelpCommand) other).helpMessage) // state check
                && showHelp == ((HelpCommand) other).showHelp); // state check
    }
}
