package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Navigates the user back to home page.
 */
public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "You're back at the home page!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.goToHomePage();

        assert model.getHomeStatusAsBoolean() == true;

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
