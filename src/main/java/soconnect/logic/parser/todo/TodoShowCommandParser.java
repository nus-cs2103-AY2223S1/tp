package soconnect.logic.parser.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.INDICATOR_PRIORITY;
import static soconnect.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_TODOS;

import java.util.List;

import soconnect.logic.commands.todo.TodoShowCommand;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.Prefix;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.todo.Priority;
import soconnect.model.todo.TodoContainsPriorityPredicate;

/**
 * Parses input arguments and creates a new TodoShowCommand object.
 */
public class TodoShowCommandParser implements Parser<TodoShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code SearchCommand}
     * and returns a {@code SearchCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TodoShowCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }

        List<ArgumentTokenizer.PrefixArgument> argList = tokenizeToList(args, new Prefix("all"), PREFIX_PRIORITY);
        int expectedNumberOfArguments = 2;
        if (argList.size() != expectedNumberOfArguments) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }

        return parseShowCondition(argList.get(1));
    }

    private TodoShowCommand parseShowCondition(ArgumentTokenizer.PrefixArgument prefixArg) throws ParseException {
        Prefix prefix = prefixArg.getPrefix();
        String arg = prefixArg.getArgument();

        switch (prefix.getPrefix()) {

        case "all":
            return new TodoShowCommand(PREDICATE_SHOW_ALL_TODOS);

        case INDICATOR_PRIORITY:
            Priority priority = ParserUtil.parsePriority(arg);
            return new TodoShowCommand(new TodoContainsPriorityPredicate(priority));

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }
    }

}
