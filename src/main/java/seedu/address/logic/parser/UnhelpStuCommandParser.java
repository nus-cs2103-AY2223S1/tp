package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnhelpStuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input and creates a new UnhelpStuCommand.
 */
public class UnhelpStuCommandParser implements Parser<UnhelpStuCommand> {

    @Override
    public UnhelpStuCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new UnhelpStuCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnhelpStuCommand.MESSAGE_USAGE), pe
            );
        }
    }
}
