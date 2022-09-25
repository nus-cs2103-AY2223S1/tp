package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the GUI theme.
 */
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";
    public enum Theme { LIGHT, DARK };
    public static final String THEME_LIGHT = "light";
    public static final String THEME_DARK = "dark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the GUI theme.\n"
            + "Parameters: THEME (must be 'dark' or 'light')\n"
            + "Example: " + COMMAND_WORD + " light";
    private final Theme theme;

    public ThemeCommand(Theme theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format("Theme changed to %s mode. Visual changes not implemented yet!", this.theme.toString()));
    }
}
