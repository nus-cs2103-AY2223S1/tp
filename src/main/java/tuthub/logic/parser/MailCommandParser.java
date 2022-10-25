package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.MailCommand;
import tuthub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MailCommand object
 */
public class MailCommandParser implements Parser<MailCommand> {

    /**
     * Checks if the argument is an index or not.
     *
     * @param s The user input.
     * @return true if input is an index, otherwise false.
     */
    public Boolean isIndex(String s) {
        try {
            int index = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Parses the input arguments when the input arguments is an index.
     *
     * @param i The index specified by the user.
     * @return A MailCommand object
     * @throws ParseException If the user input does not conform the expected format.
     */
    public MailCommand parseForIndex(String i) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(i);
            return new MailCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MailCommand
     * and returns a MailCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public MailCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (isIndex(trimmedArgs)) {
            return parseForIndex(trimmedArgs);
        }
        return new MailCommand(trimmedArgs);
    }
}
