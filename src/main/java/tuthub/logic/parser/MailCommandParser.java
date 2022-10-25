package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.MailCommand;
import tuthub.logic.parser.exceptions.ParseException;

public class MailCommandParser implements Parser<MailCommand>{

    public Boolean isIndex(String s) {
        try {
            int index = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public MailCommand parseForIndex(String i) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(i);
            return new MailCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailCommand.MESSAGE_USAGE), pe);
        }
    }

    public MailCommand parseForAll(String s) throws ParseException {
        return new MailCommand(s);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (isIndex(trimmedArgs)) {
            return parseForIndex(trimmedArgs);
        }
        return parseForAll(trimmedArgs);
    }
}
