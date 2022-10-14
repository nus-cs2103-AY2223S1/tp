package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.application.model.Model;

/**
 * Lists all applications in CinternS to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
