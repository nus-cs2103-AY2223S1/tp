package taskbook.logic.parser.tasks;

import java.util.stream.Stream;

import taskbook.logic.commands.tasks.TaskAddCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.ParserUtil;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;
import taskbook.commons.core.Messages;
import taskbook.logic.parser.*;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskAddCommandParser implements Parser<TaskAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns an TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskAddCommand parse(String args) throws ParseException {
        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        if (args.startsWith(" " + CliSyntax.PREFIX_ASSIGN_TO.getPrefix())) {
            return parseWithPrefix(args, CliSyntax.PREFIX_ASSIGN_TO);
        }

        // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
        if (args.startsWith(" " + CliSyntax.PREFIX_ASSIGN_FROM.getPrefix())) {
            return parseWithPrefix(args, CliSyntax.PREFIX_ASSIGN_FROM);
        }

        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
    }

    private TaskAddCommand parseWithPrefix(String args, Prefix firstPrefix) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, firstPrefix, CliSyntax.PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, firstPrefix, CliSyntax.PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(firstPrefix).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
        Assignment assignment =
                firstPrefix.equals(CliSyntax.PREFIX_ASSIGN_FROM)
                ? Assignment.FROM
                : Assignment.TO;

        return new TaskAddCommand(name, description, assignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
