package nus.climods.logic.parser;

import static nus.climods.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import nus.climods.logic.commands.FindCommand;
import nus.climods.logic.commands.LsCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<LsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand and returns a ListCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keyWords = trimmedArgs.split("\\s+");

        return new LsCommand();
    }

}
