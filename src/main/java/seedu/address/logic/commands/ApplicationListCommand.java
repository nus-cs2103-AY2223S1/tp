package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.address.model.ApplicationModel;

/**
 * Lists all applications in the CinternS to the user.
 */
public class ApplicationListCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";


    @Override
    public CommandResult execute(ApplicationModel model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
