package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.model.HideArchiveFromListPredicate;
import seedu.application.model.Model;

/**
 * Lists all applications in CinternS except the archived applications to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        HideArchiveFromListPredicate hideArchiveFromListPredicate =
                new HideArchiveFromListPredicate();
        model.updateFilteredApplicationList(hideArchiveFromListPredicate);
        model.updateApplicationListWithInterview();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
