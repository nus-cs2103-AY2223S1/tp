package soconnect.logic.parser.todo;

import soconnect.commons.core.Messages;
import soconnect.commons.core.index.Index;
import soconnect.logic.commands.todo.TodoDeleteCommand;
import soconnect.logic.parser.Parser;
import soconnect.logic.parser.ParserUtil;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TodoDeleteCommand object.
 */
public class TodoDeleteCommandParser implements Parser<TodoDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TodoDeleteCommand
     * and returns a TodoDeleteCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public TodoDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TodoDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TodoDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
