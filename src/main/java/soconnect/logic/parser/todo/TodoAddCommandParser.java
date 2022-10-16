package soconnect.logic.parser.todo;

import static soconnect.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import java.util.stream.Stream;
import soconnect.commons.core.Messages;
import soconnect.logic.commands.todo.TodoAddCommand;
import soconnect.logic.parser.ArgumentMultimap;
import soconnect.logic.parser.ArgumentTokenizer;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.Prefix;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.todo.Description;
import soconnect.model.todo.Todo;

/**
 * Parses input arguments and creates a new TodoAddCommand object.
 */
public class TodoAddCommandParser implements Parser<TodoAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TodoAddCommand
     * and returns an TodoAddCommand object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public TodoAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                TodoAddCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Todo todo = new Todo(description);

        return new TodoAddCommand(todo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
