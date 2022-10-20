package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkQCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates a new MarkQCommand.
 */
public class UnmarkQCommandParser implements Parser<UnmarkQCommand> {

    @Override
    public UnmarkQCommand parse(String userInput) throws ParseException {
        try {
            Index index;
            index = ParserUtil.parseIndex(userInput);
            return new UnmarkQCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkQCommand.MESSAGE_USAGE), pe);
        }
    }
}
