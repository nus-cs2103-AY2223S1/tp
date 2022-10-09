package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input argument and creates a new {@code MarkTutorialCommand} object.
 */
public class MarkTutorialCommandParser implements Parser<MarkTutorialCommand> {

    @Override
    public MarkTutorialCommand parse(String args) throws ParseException {

        Index index;

        try {
            index = ParserUtil.parseIndex(args);
            return new MarkTutorialCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTutorialCommand.MESSAGE_USAGE), pe
            );
        }
    }
}
