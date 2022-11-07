package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all projects in the project book to the user.
 */
public class ListProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_SUCCESS = "Listed all projects in the project book";

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showProjects();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
