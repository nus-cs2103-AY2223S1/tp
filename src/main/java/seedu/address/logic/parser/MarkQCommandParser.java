package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkQCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates a new MarkQCommand.
 */
public class MarkQCommandParser implements Parser<MarkQCommand> {

    @Override
    public MarkQCommand parse(String userInput) throws ParseException {
        try {
            Index index;
            index = ParserUtil.parseIndex(userInput);
            return new MarkQCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkQCommand.MESSAGE_USAGE), pe);
        }
    }
}
