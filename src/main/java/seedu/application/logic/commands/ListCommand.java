package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.model.Model;

/**
 * Lists all applications except the archived applications in CinternS  to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(Model.HIDE_ARCHIVE_IN_LIST);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
