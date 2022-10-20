package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new DeleteLinkCommandParser object.
 */
public class DeleteLinkCommandParser implements Parser<DeleteLinkCommand> {

    @Override
    public DeleteLinkCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteLinkCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLinkCommand.MESSAGE_USAGE), pe);
        }
    }
}
