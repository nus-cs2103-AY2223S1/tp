package bookface.logic.parser.add;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bookface.commons.core.Messages;
import bookface.logic.commands.add.AddCommand;
import bookface.logic.commands.add.AddUserCommand;
import bookface.logic.parser.CommandParsable;
import bookface.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements CommandParsable<AddCommand> {

    /**
     * Used for initial separation of the object to add and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<entityToAdd>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String addCommand) throws ParseException {
        String trimmedString = addCommand.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmedString.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        final String entityToAdd = matcher.group("entityToAdd");
        final String arguments = matcher.group("arguments");

        if (arguments.trim().equals("")) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        switch (entityToAdd) {

        case AddUserCommand.COMMAND_WORD:
            return new AddUserArgumentsParser().parse(arguments);

        default:
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
}
