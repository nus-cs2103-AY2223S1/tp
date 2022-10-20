package soconnect.logic.parser.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.ArgumentTokenizer.PrefixArgument;
import static soconnect.logic.parser.ArgumentTokenizer.tokenizeToList;
import static soconnect.logic.parser.CliSyntax.INDICATOR_ALL;
import static soconnect.logic.parser.CliSyntax.INDICATOR_PRIORITY;
import static soconnect.logic.parser.CliSyntax.INDICATOR_TAG;
import static soconnect.logic.parser.CliSyntax.PREFIX_ALL;
import static soconnect.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_TODOS;

import java.util.List;

import soconnect.logic.commands.todo.TodoShowCommand;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.Prefix;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Priority;
import soconnect.model.todo.TodoContainsPriorityPredicate;
import soconnect.model.todo.TodoContainsTagPredicate;

/**
 * Parses input arguments and creates a new {@code TodoShowCommand} object.
 */
public class TodoShowCommandParser implements Parser<TodoShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code TodoShowCommand}
     * and returns a {@code TodoShowCommand} object for execution.
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

        List<PrefixArgument> argList = tokenizeToList(args, PREFIX_ALL, PREFIX_PRIORITY,
            PREFIX_TAG);

        // We only expect 2 arguments from the user: First is the preamble, Second is the filter condition
        int expectedNumberOfArguments = 2;
        if (argList.size() != expectedNumberOfArguments) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }

        PrefixArgument condition = argList.get(1);
        return parseShowCondition(condition);
    }

    /**
     * Parses the filter condition in {@code prefixArg} into a {@code TodoShowCommand}.
     */
    private TodoShowCommand parseShowCondition(PrefixArgument prefixArg) throws ParseException {
        Prefix prefix = prefixArg.getPrefix();
        String arg = prefixArg.getArgument();

        switch (prefix.getPrefix()) {
        case INDICATOR_ALL:
            return new TodoShowCommand(PREDICATE_SHOW_ALL_TODOS);

        case INDICATOR_PRIORITY:
            Priority priority = ParserUtil.parsePriority(arg);
            TodoContainsPriorityPredicate priorityPredicate = new TodoContainsPriorityPredicate(priority);
            return new TodoShowCommand(priorityPredicate);

        case INDICATOR_TAG:
            Tag tag = ParserUtil.parseTag(arg);
            TodoContainsTagPredicate tagPredicate = new TodoContainsTagPredicate(tag);
            return new TodoShowCommand(tagPredicate);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoShowCommand.MESSAGE_USAGE));
        }
    }

}
