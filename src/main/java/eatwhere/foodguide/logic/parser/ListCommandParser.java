package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;

import eatwhere.foodguide.logic.commands.ListCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public ListCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(ListCommand.MESSAGE_USAGE);
        }

        return new ListCommand();
    }
}
