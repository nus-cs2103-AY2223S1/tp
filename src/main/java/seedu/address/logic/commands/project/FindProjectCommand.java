package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_CLIENT_LABEL;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.ProjectContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.ui.Ui;

/**
 * Represents a class to find and filter project list.
 */
public class FindProjectCommand extends ProjectCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of projects shown.";
    private static final String MESSAGE_PROJECT_NOT_FOUND = "A project matching requirements not found.";

    public static final String MESSAGE_FIND_PROJECT_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Finds projects by keyword.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PROJECT_ID + "PROJECT ID] "
            + "[" + PREFIX_REPOSITORY + "REPOSITORY] "
            + "[" + PREFIX_CLIENT_LABEL + "CLIENT LABEL] "
            + "[" + PREFIX_CLIENT_ID + "CLIENT ID] \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "DevEnable "
            + PREFIX_PROJECT_ID + "2 "
            + PREFIX_REPOSITORY + "tp/devenable "
            + PREFIX_CLIENT_LABEL + "Amy "
            + PREFIX_CLIENT_ID + "1 ";

    private static ProjectContainsKeywordsPredicate predicate;

    public FindProjectCommand(ProjectContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        ui.showProjects();
        model.updateFilteredProjectList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW, model.getFilteredProjectList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProjectCommand // instanceof handles nulls
                && predicate.equals(((FindProjectCommand) other).predicate)); // state check
    }
}
