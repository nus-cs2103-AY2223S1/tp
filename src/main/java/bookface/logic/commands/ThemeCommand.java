package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.add.AddCommand;
import bookface.model.Model;

/**
 * Handles changing of theme colour for BookFace's UI.
 */
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";
    public static final String MESSAGE_SUCCESS = "Theme changed.";
    public static final String MESSAGE_USAGE = AddCommand.generateMessage(COMMAND_WORD,
            "Change the theme of BookFace.", COMMAND_WORD);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
