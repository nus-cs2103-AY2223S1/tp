package seedu.address.logic.commands;

import seedu.address.commons.core.Themes.Theme;
import seedu.address.model.Model;

/**
 * Changes to theme to the theme given in the argument.
 * Currently only Light and Dark theme.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the theme to the specified theme.\n"
            + "Parameters: THEME (only light or dark)\n"
            + "Example: " + COMMAND_WORD + " \"dark\" OR " + COMMAND_WORD + " \"light\"";

    public static final String CHANGE_THEME_MESSAGE = "Changed theme.";

    public static final String CHANGE_THEME_ERROR = "Specified theme is already selected.";

    private final Theme theme;

    public ThemeCommand(Theme theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(CHANGE_THEME_MESSAGE, theme);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ThemeCommand
                && theme.equals(((ThemeCommand) other).theme));
    }
}
