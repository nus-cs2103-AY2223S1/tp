package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for all add commands for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    private static final String USER_GUIDE_URL =
            "https://ay2223s1-cs2103t-f11-4.github.io/tp/UserGuide.html#quick-start";

    private static final String GITHUB_RELEASE_URL =
            "https://github.com/AY2223S1-CS2103T-F11-4/tp/releases";

    public static final String SHOWING_HELP_MESSAGE = "For detailed guide: "
            + USER_GUIDE_URL + "\n"
            + "For latest YellowBook version: "
            + GITHUB_RELEASE_URL;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
