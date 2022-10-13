package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * Parses input arguments and creates a new TaskCommand object.
 */
public class TaskCommandParser implements Parser<TaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskCommand
     * and returns a TaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_TITLE, PREFIX_TASK_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_TITLE, PREFIX_TASK_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }

        TaskTitle title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TASK_TITLE).get());
        TaskDescription description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get());

        Task task = new Task(title, description);

        return new TaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
