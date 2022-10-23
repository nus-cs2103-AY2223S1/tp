package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetPaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetPaidCommand object
 */
public class SetPaidCommandParser implements Parser<SetPaidCommand> {

    @Override
    public SetPaidCommand parse(String userInput) throws ParseException {
        Index index;

        try {
            index = ParserUtil.parseIndex(userInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetPaidCommand.MESSAGE_USAGE), pe);
        }

        return new SetPaidCommand(index);
    }

}
