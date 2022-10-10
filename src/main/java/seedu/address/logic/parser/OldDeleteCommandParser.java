package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OldDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new OldDeleteCommand object
 */
public class OldDeleteCommandParser implements OldParser<OldDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OldDeleteCommand
     * and returns a OldDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OldDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new OldDeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OldDeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
