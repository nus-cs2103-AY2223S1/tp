package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Priority;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.UniqueIssueList;
import seedu.address.model.project.Project;

/**
 * Parser to parse any commands related to issues
 */
public class IssueCommandParser implements Parser<IssueCommand> {
    /**
     * Method to parse any commands that have to do with issues (start with 'issue')
     *
     * @param flag      flag used in command
     * @param arguments arguments used in command
     * @return a IssueCommand
     * @throws ParseException
     */
    @Override
    public IssueCommand parse(String flag, String arguments) throws ParseException {
        switch (flag.strip()) {
        case AddIssueCommand.COMMAND_FLAG:
            return parseAddIssueCommand(arguments);
        case EditIssueCommand.COMMAND_FLAG:
            return parseEditIssueCommand(arguments);
        case DeleteIssueCommand.COMMAND_FLAG:
            return parseDeleteIssueCommand(arguments);
        case ListIssueCommand.COMMAND_FLAG:
            return parseListIssueCommand(arguments);
        default:
            throw new ParseException(FLAG_UNKNOWN_COMMAND);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private AddIssueCommand parseAddIssueCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_DESCRIPTION, PREFIX_DEADLINE,
                        PREFIX_PRIORITY, PREFIX_PROJECT_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_PROJECT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Deadline deadline = Deadline.EmptyDeadline.EMPTY_DEADLINE;
        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        }

        Priority priority = Priority.NONE;
        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        Status status = Status.EmptyStatus.EMPTY_STATUS;
        Project project = ParserUtil.parseProject(argMultimap.getValue(PREFIX_PROJECT_ID).get());
        IssueId issueId = new IssueId(UniqueIssueList.generateId());

        Issue issue = new Issue(description, deadline, priority, status, project, issueId);

        return new AddIssueCommand(issue);
    }

    private EditIssueCommand parseEditIssueCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_ISSUE_ID, PREFIX_DESCRIPTION,
                        PREFIX_DEADLINE, PREFIX_PRIORITY);
        if (!arePrefixesPresent(argMultimap, PREFIX_ISSUE_ID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditIssueCommand.MESSAGE_USAGE));
        }

        Description newDescription;
        Deadline newDeadline;
        Priority newPriority;
        IssueId issueId = ParserUtil.parseIssueId(argMultimap.getValue(PREFIX_ISSUE_ID).get());
        Issue initialIssue = UniqueIssueList.getIssue(issueId.getIdInt());

        if (!anyPrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_PRIORITY)) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS,
                    EditIssueCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            newDescription = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
            initialIssue.setDescription(newDescription);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            newDeadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
            initialIssue.setDeadline(newDeadline);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            newPriority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            initialIssue.setPriority(newPriority);
        }

        return new EditIssueCommand(initialIssue);
    }

    private DeleteIssueCommand parseDeleteIssueCommand(String arguments) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(arguments);
            return new DeleteIssueCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteIssueCommand.MESSAGE_USAGE), pe);
        }
    }

    //TODO: implement
    private ListIssueCommand parseListIssueCommand(String arguments) {
        return new ListIssueCommand();
    }




}
