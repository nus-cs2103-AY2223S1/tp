package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.ui.SecondaryPaneState;

/**
 * Command to return the secondary pane to the home page.
 */
public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";
    public static final String MESSAGE_SUCCESS = "Displaying the home page. Welome back!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, SecondaryPaneState.WELCOME);
    }
}
