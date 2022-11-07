package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.stream.Stream;

import seedu.hrpro.logic.commands.AddTaskCommand;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.task.Task;
import seedu.hrpro.model.task.TaskDescription;
import seedu.hrpro.model.task.TaskMark;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION, PREFIX_TASK_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_DESCRIPTION, PREFIX_TASK_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        TaskDescription description =
                ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get());
        Deadline deadline =
                ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get());
        Task task = new Task(deadline, description, new TaskMark("false"));
        assert task != null; //task is created and should not be null
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
