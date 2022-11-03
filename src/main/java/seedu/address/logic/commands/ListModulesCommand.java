package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.model.Model;

/**
 * Lists all modules in the module list to the user.
 */
public class ListModulesCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all modules";

    public static final String EMPTY_LIST = "No module in list";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        if (model.getFilteredModuleList().size() == 0) {
            return new CommandResult(EMPTY_LIST);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
