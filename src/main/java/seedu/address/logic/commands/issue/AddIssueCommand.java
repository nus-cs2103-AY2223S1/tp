package seedu.address.logic.commands.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_URGENCY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ISSUES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueWithoutModel;
import seedu.address.model.project.ProjectId;
import seedu.address.ui.Ui;

/**
 * Command to add issue
 */
public class AddIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-a";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " " + COMMAND_FLAG
            + ": Adds an issue to the project book. \n"
            + "Parameters: "
            + PREFIX_PROJECT_ID + "PROJECT_ID "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_URGENCY + "URGENCY(0, 1, 2) \n"
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_PROJECT_ID + "1 "
            + PREFIX_TITLE + "to create a person class which stores all relevant person data "
            + PREFIX_DEADLINE + "2022-12-10 "
            + PREFIX_URGENCY + "0 ";


    public static final String MESSAGE_SUCCESS = "New issue added: %1$s";
    public static final String MESSAGE_DUPLICATE_ISSUE = "This issue already exists in the project book";
    public static final String MESSAGE_PROJECT_NOT_FOUND = "This project id does not exist in the project book";

    //    private final Issue toAdd;
    private final IssueWithoutModel toAddWithoutModel;
    private final ProjectId projectId;

    /**
     * Creates an AddCommand to add the specified {@code Issue}
     */
    public AddIssueCommand(IssueWithoutModel issueWithoutModel, ProjectId pid) {
        requireNonNull(issueWithoutModel);
        toAddWithoutModel = issueWithoutModel;
        projectId = pid;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) throws CommandException {
        requireNonNull(model);

        if (!model.hasProjectId(projectId.getIdInt())) {
            throw new CommandException(MESSAGE_PROJECT_NOT_FOUND);
        }

        Issue toAdd = toAddWithoutModel.apply(model);

        if (model.hasIssue(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ISSUE);
        }

        ui.showIssues();
        model.updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);


        model.addIssue(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddIssueCommand)) {
            return false;
        }

        return this.toAddWithoutModel.equals(((AddIssueCommand) other).toAddWithoutModel);
    }
}

