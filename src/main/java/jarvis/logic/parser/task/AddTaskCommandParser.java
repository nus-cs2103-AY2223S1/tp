package jarvis.logic.parser.task;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static jarvis.logic.parser.CliSyntax.PREFIX_TASK_DESC;

import java.util.stream.Stream;

import jarvis.logic.commands.task.AddTaskCommand;
import jarvis.logic.parser.ArgumentMultimap;
import jarvis.logic.parser.ArgumentTokenizer;
import jarvis.logic.parser.Parser;
import jarvis.logic.parser.ParserUtil;
import jarvis.logic.parser.Prefix;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.task.Deadline;
import jarvis.model.task.Task;
import jarvis.model.task.TaskDesc;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESC, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_DESC, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        TaskDesc taskDesc = ParserUtil.parseTaskDesc(argMultimap.getValue(PREFIX_TASK_DESC).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        Task task = new Task(taskDesc, deadline);

        return new AddTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
