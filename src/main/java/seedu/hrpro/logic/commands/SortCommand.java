package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;

/**
 * Sorts the projects in the project book by deadline.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sortproj";
    public static final String MESSAGE_SUCCESS = "Projects are now sorted by deadline!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortProjects();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
