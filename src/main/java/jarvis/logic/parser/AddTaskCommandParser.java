package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static jarvis.logic.parser.CliSyntax.PREFIX_TASK_DESC;

import java.util.Optional;
import java.util.stream.Stream;

import jarvis.logic.commands.AddTaskCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.Task;
import jarvis.model.TaskDeadline;
import jarvis.model.TaskDesc;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESC, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_DESC) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        TaskDesc taskDesc = ParserUtil.parseTaskDesc(argMultimap.getValue(PREFIX_TASK_DESC).get());
        Optional<String> deadline = argMultimap.getValue(PREFIX_DEADLINE);
        TaskDeadline taskDeadline = deadline.isPresent() ? ParserUtil.parseDeadline(deadline.get()) : null;

        Task task = new Task(taskDesc, taskDeadline);

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
