package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import nus.climods.logic.commands.FindCommand;
import nus.climods.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    @Override
    public FindCommand parse(String searchPhrase) throws ParseException {
        String trimmedSearchPhrase = searchPhrase.trim();
        if (trimmedSearchPhrase.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(Arrays.asList(trimmedSearchPhrase.split("\\s+")));
    }
}
