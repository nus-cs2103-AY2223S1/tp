package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.logic.parser.ParserUtil.isDisplayHelp;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.DeleteCommand;
import eatwhere.foodguide.logic.parser.exceptions.DisplayCommandHelpException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     * @throws DisplayCommandHelpException if the user input is for displaying command help
     */
    public DeleteCommand parse(String args) throws ParseException, DisplayCommandHelpException {
        if (isDisplayHelp(args)) {
            throw new DisplayCommandHelpException(DeleteCommand.MESSAGE_USAGE);
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
