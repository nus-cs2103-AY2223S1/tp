package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns a TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEAM_INDEX, PREFIX_TASK_NAME);

        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAM_INDEX, PREFIX_TASK_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE), pe);
        }

        Name taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get());

        Task task = new Task(taskName);

        return new TaskAddCommand(index, task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
