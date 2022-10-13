package taskbook.logic.parser.tasks;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.logic.parser.ArgumentMultimap;
import taskbook.logic.parser.ArgumentTokenizer;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.Parser;
import taskbook.logic.parser.ParserUtil;
import taskbook.logic.parser.Prefix;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

/**
 * Parses input arguments and creates a new TaskAddCommand object
 */
public class TaskTodoCommandParser implements Parser<TaskTodoCommand> {

    // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
    private static final Pattern ASSIGN_TO_COMMAND_FORMAT =
            Pattern.compile(String.format("\\s+%s.*", CliSyntax.PREFIX_ASSIGN_TO.getPrefix()));

    // Note: the space at the start of the arguments is necessary due to ArgumentTokenizer behavior.
    private static final Pattern ASSIGN_FROM_COMMAND_FORMAT =
            Pattern.compile(String.format("\\s+%s.*", CliSyntax.PREFIX_ASSIGN_FROM.getPrefix()));

    /**
     * Parses the given {@code String} of arguments in the context of the TaskAddCommand
     * and returns an TaskAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TaskTodoCommand parse(String args) throws ParseException {
        if (ASSIGN_TO_COMMAND_FORMAT.matcher(args).matches()) {
            return parseWithPrefix(args, CliSyntax.PREFIX_ASSIGN_TO);
        }

        if (ASSIGN_FROM_COMMAND_FORMAT.matcher(args).matches()) {
            return parseWithPrefix(args, CliSyntax.PREFIX_ASSIGN_FROM);
        }

        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskTodoCommand.MESSAGE_USAGE));
    }

    private TaskTodoCommand parseWithPrefix(String args, Prefix firstPrefix) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, firstPrefix, CliSyntax.PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, firstPrefix, CliSyntax.PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, TaskTodoCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(firstPrefix).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
        Assignment assignment =
                firstPrefix.equals(CliSyntax.PREFIX_ASSIGN_FROM)
                ? Assignment.FROM
                : Assignment.TO;

        return new TaskTodoCommand(name, description, assignment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
