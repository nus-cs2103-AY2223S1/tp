package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.IssueCliSyntax.*;

import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.issue.*;
import seedu.address.model.project.Project;

import java.util.stream.Stream;


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

    //TODO: implement
    private AddIssueCommand parseAddIssueCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_DESCRIPTION, PREFIX_DEADLINE,
                        PREFIX_PRIORITY, PREFIX_STATUS, PREFIX_PROJECTID);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_PROJECTID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE));
        }

        Description description = IssueParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Deadline deadline = IssueParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Priority priority = IssueParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        Status status = IssueParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Project project = IssueParserUtil.parseProject(argMultimap.getValue(PREFIX_PROJECTID).get());
        IssueId issueId = new IssueId(UniqueIssueList.generateId());

        Issue issue = new Issue(description, deadline, priority, status, project, issueId);

        return new AddIssueCommand(issue);
    }
    //TODO: implement
    private EditIssueCommand parseEditIssueCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private DeleteIssueCommand parseDeleteIssueCommand(String arguments) {
        return null;
    }

    //TODO: implement
    private ListIssueCommand parseListIssueCommand(String arguments) {
        return new ListIssueCommand();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
