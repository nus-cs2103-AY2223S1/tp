package soconnect.logic.parser.todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soconnect.commons.core.Messages;
import soconnect.logic.commands.Command;
import soconnect.logic.commands.todo.TodoAddCommand;
import soconnect.logic.commands.todo.TodoClearCommand;
import soconnect.logic.commands.todo.TodoCommand;
import soconnect.logic.commands.todo.TodoDeleteCommand;
import soconnect.logic.commands.todo.TodoEditCommand;
import soconnect.logic.commands.todo.TodoShowCommand;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses user input for {@code Todo} commands.
 */
public class TodoParser {

    /**
     * Used for separation of subcommand word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into {@code Todo} command for execution.
     *
     * @param userInput User input string excluding the "todo" command word.
     * @return The {@code Todo} command based on the user input.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, TodoCommand.MESSAGE_USAGE));
        }

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");
        switch (subCommandWord) {
        case TodoAddCommand.SUB_COMMAND_WORD:
            return new TodoAddCommandParser().parse(arguments);

        case TodoEditCommand.SUB_COMMAND_WORD:
            return new TodoEditCommandParser().parse(arguments);

        case TodoDeleteCommand.SUB_COMMAND_WORD:
            return new TodoDeleteCommandParser().parse(arguments);

        case TodoClearCommand.SUB_COMMAND_WORD:
            return new TodoClearCommand();

        case TodoShowCommand.SUB_COMMAND_WORD:
            return new TodoShowCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND + "\n" + TodoCommand.MESSAGE_USAGE);
        }
    }

}
