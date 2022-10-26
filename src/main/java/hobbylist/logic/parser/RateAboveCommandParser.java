package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import hobbylist.logic.commands.FindCommand;
import hobbylist.logic.commands.RateAboveCommand;
import hobbylist.logic.parser.exceptions.ParseException;

/**
 * Parse the input arg.
 */
public class RateAboveCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateAboveCommand parse(String args) throws ParseException {
        int i = 0;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        try {
            i = Integer.valueOf(trimmedArgs);
        } catch (Exception e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
        if (i < 0 || i > 5) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
        return new RateAboveCommand(i);


    }
}
