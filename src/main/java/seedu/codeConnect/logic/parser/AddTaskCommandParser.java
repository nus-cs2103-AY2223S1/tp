package seedu.codeConnect.logic.parser;

import static seedu.codeConnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeConnect.logic.parser.ArgumentMultimap.DOES_NOT_EXIST;
import static seedu.codeConnect.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.codeConnect.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.stream.Stream;

import seedu.codeConnect.logic.commands.AddTaskCommand;
import seedu.codeConnect.logic.parser.exceptions.ParseException;
import seedu.codeConnect.model.module.Module;
import seedu.codeConnect.model.task.Deadline;
import seedu.codeConnect.model.task.Status;
import seedu.codeConnect.model.task.Task;
import seedu.codeConnect.model.task.TaskName;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE, PREFIX_MODULE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DEADLINE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        TaskName name = ParserUtil.parseTaskName(argMultimap.getPreamble());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        String moduleString = argMultimap.getValue(PREFIX_MODULE).orElse(null);
        Module module = moduleString != null
                ? ParserUtil.parseModule(moduleString)
                : new Module(DOES_NOT_EXIST);
        Status status = new Status(false);

        Task task = new Task(name, module, deadline, status);

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
