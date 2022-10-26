package modtrekt.logic.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;

/**
 * Lists all tasks.
 */
@Parameters(commandDescription = "List modules in the Module List.")
public class ListModulesCommand extends Command {
    public static final String COMMAND_PHRASE = "list module";
    public static final String COMMAND_ALIAS = "ls module";

    @Parameter(names = "-a", description = "Show all modules")
    private boolean areDoneModulesShown;

    /**
     * Returns a new ListTasksCommand object, with no fields initialized, for use with JCommander.
     */
    public ListModulesCommand() {
    }

    public ListModulesCommand(boolean areDoneTasksShown) {
        this.areDoneModulesShown = areDoneTasksShown;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (areDoneModulesShown) {
            model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
            return new CommandResult("Here is the list of all modules, including those marked as done!");
        } else {
            model.updateFilteredModuleList(Model.PREDICATE_HIDE_DONE_MODULES);
            return new CommandResult("Here is the list of all active modules!");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListModulesCommand // instanceof handles nulls
                && areDoneModulesShown == (((ListModulesCommand) other).areDoneModulesShown));
    }
}
