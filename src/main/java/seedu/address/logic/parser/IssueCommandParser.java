package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.FLAG_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_ISSUE_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_ID;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.IssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.commands.issue.MarkIssueCommand;
import seedu.address.logic.commands.issue.SetIssueDefaultViewCommand;
import seedu.address.logic.commands.issue.SortIssueCommand;
import seedu.address.logic.commands.issue.UnmarkIssueCommand;
import seedu.address.logic.commands.issue.find.FindIssueByDescriptionCommand;
import seedu.address.logic.commands.issue.find.FindIssueByPriorityCommand;
import seedu.address.logic.commands.issue.find.FindIssueByProjectCommand;
import seedu.address.logic.commands.issue.find.FindIssueByStatusCommand;
import seedu.address.logic.commands.issue.find.FindIssueCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.IssueWithoutModel;
import seedu.address.model.issue.Priority;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.predicates.DescriptionContainsKeywordsPredicate;
import seedu.address.model.issue.predicates.PriorityMatchesKeywordsPredicate;
import seedu.address.model.issue.predicates.ProjectContainsKeywordsPredicate;
import seedu.address.model.issue.predicates.StatusMatchesKeywordsPredicate;
import seedu.address.model.project.ProjectId;

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
        case SortIssueCommand.COMMAND_FLAG:
            return parseSortIssueCommand(arguments);
        case ListIssueCommand.COMMAND_FLAG:
            return parseListIssueCommand(arguments);
        case MarkIssueCommand.COMMAND_FLAG:
            return parseMarkIssueCommand(arguments);
        case UnmarkIssueCommand.COMMAND_FLAG:
            return parseUnmarkIssueCommand(arguments);
        case SetIssueDefaultViewCommand.COMMAND_FLAG:
            return parseSetIssueDefaultViewCommand(arguments);
        case FindIssueCommand.COMMAND_FLAG:
            return parseFindIssueCommand(arguments);
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


    /**
     * Verifies only one valid user input argument
     * Length of a valid command for sort key for issue by priority e.g.d/1
     *
     * @param arguments user input for key for sort by deadline
     * @return true if there is only one valid input
     */
    private boolean hasOneArgumentOfLengthThree(String arguments) {
        return arguments.trim().length() == 3;
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
        ProjectId projectid = ParserUtil.parseProjectId(argMultimap.getValue(PREFIX_PROJECT_ID).get());

        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(description, deadline, priority, status, projectid);

        return new AddIssueCommand(issueWithoutModel, projectid);
    }

    private EditIssueCommand parseEditIssueCommand(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_ISSUE_ID, PREFIX_DESCRIPTION,
                        PREFIX_DEADLINE, PREFIX_PRIORITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_ISSUE_ID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditIssueCommand.MESSAGE_USAGE));
        }

        Description newDescription = null;
        Deadline newDeadline = null;
        Priority newPriority = null;
        IssueId newIssueId = ParserUtil.parseIssueId(argMultimap.getValue(PREFIX_ISSUE_ID).get());

        if (!anyPrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_PRIORITY)) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS,
                    EditIssueCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            newDescription = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            newDeadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        }

        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            newPriority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        return new EditIssueCommand(newDescription, newDeadline, newPriority, newIssueId);
    }

    private FindIssueCommand parseFindIssueCommand(String arguments) throws ParseException {
        try {

            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(arguments, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
                            PREFIX_STATUS, PREFIX_NAME);

            String trimmedArgs = arguments.trim();

            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIssueCommand.MESSAGE_FIND_ISSUE_USAGE));
            }


            if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
                return new FindIssueByDescriptionCommand(new DescriptionContainsKeywordsPredicate(
                        argMultimap.getAllValues(PREFIX_DESCRIPTION)));
            }

            if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
                return new FindIssueByStatusCommand(new StatusMatchesKeywordsPredicate(
                        argMultimap.getAllValues(PREFIX_STATUS)));
            }

            if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                return new FindIssueByProjectCommand(new ProjectContainsKeywordsPredicate(
                        argMultimap.getAllValues(PREFIX_NAME)));
            }

            //implies arePrefixesPresent(argMultimap, PREFIX_STATUS) is true
            return new FindIssueByPriorityCommand(new PriorityMatchesKeywordsPredicate(
                    argMultimap.getAllValues(PREFIX_PRIORITY)));

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIssueCommand.MESSAGE_FIND_ISSUE_USAGE), pe);
        }

    }

    private FindIssueCommand parseFindIssueCommand(String flag, String arguments) throws ParseException {
        return parseFindIssueCommand(arguments);
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

    private SortIssueCommand parseSortIssueCommand(String arguments) throws ParseException {

        Prefix sortPrefix = null;
        int key = -1;

        if (!hasOneArgumentOfLengthThree(arguments)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortIssueCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_PRIORITY,
                PREFIX_DEADLINE);

        if (!anyPrefixesPresent(argMultimap, PREFIX_PRIORITY, PREFIX_DEADLINE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SortIssueCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            sortPrefix = PREFIX_DEADLINE;
            key = ParserUtil.parseDeadlineSortForIssue(argMultimap.getValue(PREFIX_DEADLINE).get());
        }

        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            sortPrefix = PREFIX_PRIORITY;
            key = ParserUtil.parsePrioritySort(argMultimap.getValue(PREFIX_PRIORITY).get());
        }

        return new SortIssueCommand(sortPrefix, key);
    }

    private ListIssueCommand parseListIssueCommand(String arguments) {
        return new ListIssueCommand();
    }

    private MarkIssueCommand parseMarkIssueCommand(String arguments) throws ParseException {
        try {
            Status newStatus = new Status(true);
            IssueId newIssueId = ParserUtil.parseIssueId(arguments);
            return new MarkIssueCommand(newStatus, newIssueId);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkIssueCommand.MESSAGE_USAGE), pe);
        }
    }

    private UnmarkIssueCommand parseUnmarkIssueCommand(String arguments) throws ParseException {
        try {
            Status newStatus = new Status(false);
            IssueId newIssueId = ParserUtil.parseIssueId(arguments);
            return new UnmarkIssueCommand(newStatus, newIssueId);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkIssueCommand.MESSAGE_USAGE), pe);
        }
    }

    private IssueCommand parseSetIssueDefaultViewCommand(String arguments) {
        return new SetIssueDefaultViewCommand();

    }
}
