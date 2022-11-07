package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_COMPLETION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTaskCommandParser object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTaskCommandParser
     * and returns a FindTaskCommand object for execution.
     *
     * @param args Raw arguments from user input.
     * @return FindTaskCommand object.
     * @throws ParseException If the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION,
                        PREFIX_TASK_DEADLINE, PREFIX_TASK_COMPLETION_STATUS);

        if (arePrefixesEmpty(argMultimap, PREFIX_TASK_DESCRIPTION,
                PREFIX_TASK_DEADLINE, PREFIX_TASK_COMPLETION_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            FindTaskCommand.MESSAGE_USAGE));
        }

        List<Description> descriptions = getDescriptions(argMultimap);
        List<Deadline> deadlines = getDeadlines(argMultimap);
        List<CompletionStatus> completionStatuses = getCompletionStatuses(argMultimap);

        return new FindTaskCommand(
                new TaskContainsKeywordsPredicate(descriptions, deadlines, completionStatuses));
    }

    private List<Description> getDescriptions(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseDescriptions(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get());
    }

    private List<Deadline> getDeadlines(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseDeadlines(argMultimap.getValue(PREFIX_TASK_DEADLINE).get());
    }

    private List<CompletionStatus> getCompletionStatuses(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_TASK_COMPLETION_STATUS).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseCompletionStatuses(argMultimap.getValue(PREFIX_TASK_COMPLETION_STATUS).get());
    }

    /**
     * Returns true if all the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
