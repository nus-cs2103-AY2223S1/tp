package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.realtime.commons.core.index.Index;
import seedu.realtime.logic.commands.DeleteOfferCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteOfferCommand object
 */
public class DeleteOfferCommandParser implements Parser<DeleteOfferCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOfferCommand
     * and returns a DeleteOfferCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOfferCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOfferCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOfferCommand.MESSAGE_USAGE), pe);
        }
    }
}
