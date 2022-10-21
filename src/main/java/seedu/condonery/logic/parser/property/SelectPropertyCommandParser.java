package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.property.SelectPropertyCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectPropertyCommand object
 */
public class SelectPropertyCommandParser implements Parser<SelectPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectPropertyCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SelectPropertyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectPropertyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectPropertyCommand.MESSAGE_USAGE), pe);
        }
    }
}
