package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_AT_HOMEPAGE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all modules in Plannit to the user.
 */
public class ListModuleCommand extends Command {

    public static final String COMMAND_WORD = "list-module";

    public static final String MESSAGE_SUCCESS = "Listed all modules";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean applicationIsNotOnHomePage = !model.getHomeStatus();
        if (applicationIsNotOnHomePage) {
            throw new CommandException(MESSAGE_NOT_AT_HOMEPAGE);
        }
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
