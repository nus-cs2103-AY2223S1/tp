package seedu.address.logic.commands.issue.find;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.issue.IssueCommand;

/**
 * Represents an abstract class to find and filter issue list.
 */
public abstract class FindIssueCommand extends IssueCommand {

    public static final String COMMAND_FLAG = "-f";
    public static final String MESSAGE_SUCCESS = "Filtered list of issues shown.";
    private static final String MESSAGE_ISSUE_NOT_FOUND = "An issue matching requirements not found.";

    public static final String MESSAGE_FIND_ISSUE_USAGE = COMMAND_WORD + ": Finds and filters issues by keyword "
            + "from the "
            + "address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_PRIORITY + "PRIORITY "
            + PREFIX_PROJECT_ID + "PROJECT ID "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_DESCRIPTION + "This is an issue "
            + PREFIX_STATUS + "COMPLETED "
            + PREFIX_PRIORITY + "1 "
            + PREFIX_NAME + "DevEnable ";
}
