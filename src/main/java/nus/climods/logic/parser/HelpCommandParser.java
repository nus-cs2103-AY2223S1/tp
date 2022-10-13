package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import nus.climods.logic.commands.HelpCommand;
import nus.climods.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    private static final String DEFAULT_ERROR_MESSAGE =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);

    @Override
    public HelpCommand parse(String args) throws ParseException {
        String trimmedSearchPhrase = args.trim();
        if (!trimmedSearchPhrase.isEmpty()) {
            throw new ParseException(DEFAULT_ERROR_MESSAGE);
        }
        return new HelpCommand();
    }
}
