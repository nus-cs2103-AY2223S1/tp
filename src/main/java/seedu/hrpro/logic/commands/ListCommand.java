package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.model.Model.PREDICATE_SHOW_ALL_PROJECTS;
import static seedu.hrpro.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.hrpro.model.Model;

/**
 * Lists all projects in hr pro to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all projects and tasks!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
