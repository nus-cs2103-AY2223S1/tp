package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the address book to the user.
 */
public class ListProjectCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_SUCCESS = "Listed all projects";


    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showProjects();
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
