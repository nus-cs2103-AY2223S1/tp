package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.tasks.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new TaskCommand object
 */
public class TaskCommandParser implements Parser<TaskCommand> {

    @Override
    public TaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_NAME).get();
        String address = argMultimap.getValue(PREFIX_DESCRIPTION).get();

        Task task = new Task(name, address);

        return new TaskCommand(task);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
