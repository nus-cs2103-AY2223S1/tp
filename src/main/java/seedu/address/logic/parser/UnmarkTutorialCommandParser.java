package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input argument and creates a new {@code UnmarkTutorialCommand} object.
 */
public class UnmarkTutorialCommandParser implements Parser<UnmarkTutorialCommand> {

    @Override
    public UnmarkTutorialCommand parse(String args) throws ParseException {

        try {
            Index index;

            index = ParserUtil.parseIndex(args);
            return new UnmarkTutorialCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTutorialCommand.MESSAGE_USAGE), pe
            );
        }
    }
}

