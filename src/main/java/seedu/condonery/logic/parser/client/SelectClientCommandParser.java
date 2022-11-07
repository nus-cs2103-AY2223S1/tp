package seedu.condonery.logic.parser.client;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.client.SelectClientCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectClientCommand object
 */
public class SelectClientCommandParser implements Parser<SelectClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectClientCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SelectClientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectClientCommand.MESSAGE_USAGE), pe);
        }
    }
}
