package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ProjectCliSyntax.PREFIX_REPOSITORY;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.commands.project.ProjectCommand;
import seedu.address.logic.parser.predicates.IssueContainsKeywordsPredicate;
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

    public static final String MESSAGE_FIND_PROJECT_USAGE = COMMAND_WORD + ": Finds and filters projects by keyword "
            + "from the "
            + "address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_REPOSITORY + "REPOSITORY "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_NAME + "DevEnable "
            + PREFIX_REPOSITORY + "tp/devenable ";

    private static ProjectContainsKeywordsPredicate predicate;

    public FindProjectCommand(ProjectContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
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
