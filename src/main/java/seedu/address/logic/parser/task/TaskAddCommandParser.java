package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_STUDENT;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.task.TaskAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns an TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaskAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_TASK_DESC,
                        PREFIX_TASK_DEADLINE, PREFIX_TASK_STUDENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_NAME, PREFIX_TASK_DESC,
                PREFIX_TASK_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
        }


        TaskName taskName = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get());
        TaskDescription taskDesc = ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESC).get());
        TaskDeadline taskDeadline = ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get());

        List<String> studentNames = argMultimap.getAllValues(PREFIX_TASK_STUDENT);

        // Delay the creation of the task
        return new TaskAddCommand(taskName, taskDesc, taskDeadline, studentNames);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
