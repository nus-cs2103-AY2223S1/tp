package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetUnpaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetUnpaidCommand object
 */
public class SetUnpaidCommandParser implements Parser<SetUnpaidCommand> {

    @Override
    public SetUnpaidCommand parse(String userInput) throws ParseException {
        Index index;

        try {
            index = ParserUtil.parseIndex(userInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetUnpaidCommand.MESSAGE_USAGE), pe);
        }

        return new SetUnpaidCommand(index);
    }
}
