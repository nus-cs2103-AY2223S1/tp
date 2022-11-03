package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskUnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TaskUnmarkCommand object
 */
public class TaskUnmarkCommandParser implements Parser<TaskUnmarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TaskUnmarkCommand
     * and returns a TaskUnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskUnmarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEAM_INDEX, PREFIX_TASK_INDEX);

        Index taskIndex;
        Index teamIndex;

        if (!arePrefixesPresent(argMultimap, PREFIX_TEAM_INDEX, PREFIX_TASK_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskUnmarkCommand.MESSAGE_USAGE));
        }

        try {
            teamIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEAM_INDEX).get());
            taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TaskUnmarkCommand.MESSAGE_USAGE), pe);
        }
        return new TaskUnmarkCommand(teamIndex, taskIndex);
    }

}
