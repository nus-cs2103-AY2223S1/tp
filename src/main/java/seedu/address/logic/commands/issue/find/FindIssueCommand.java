package seedu.address.logic.commands.issue.find;

import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.model.issue.Issue;

import static seedu.address.logic.commands.issue.IssueCommand.COMMAND_WORD;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_PHONE;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_STATUS;

public abstract class FindIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of issues shown.";
    private static final String MESSAGE_ISSUE_NOT_FOUND = "An issue matching requirements not found.";

    public static final String MESSAGE_FIND_ISSUE_USAGE = COMMAND_WORD + ": Finds and filters issues by keyword " +
            "from the " +
            "address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + PREFIX_STATUS + "STATUS "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_DESCRIPTION + "This is an issue "
            + PREFIX_PRIORITY + "1 ";
}
