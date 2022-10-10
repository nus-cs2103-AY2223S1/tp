package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Lists all persons in the address book to the user.
 */
public class EditProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-e";

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        return null;
    }
}
