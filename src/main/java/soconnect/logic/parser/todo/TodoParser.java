package soconnect.logic.parser.todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import soconnect.commons.core.Messages;
import soconnect.logic.commands.AddCommand;
import soconnect.logic.commands.ClearCommand;
import soconnect.logic.commands.Command;
import soconnect.logic.commands.HelpCommand;
import soconnect.logic.parser.AddCommandParser;
import soconnect.logic.parser.exceptions.ParseException;

/**
 * Parses user input for {@code Todo} commands.
 */
public class TodoParser {

    /**
     * Used for initial separation of subcommand word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subCommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into {@code Todo} command for execution.
     *
     * @param userInput Full user input string excluding the "todo" command word.
     * @return The {@code Todo} command based on the user input.
     * @throws ParseException If the user input does not conform with the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String subCommandWord = matcher.group("subCommandWord");
        final String arguments = matcher.group("arguments");
        switch (subCommandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
