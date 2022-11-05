package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.logic.commands.PinCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new PinCommand object.
 */
public class PinCommandParser implements Parser<PinCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand
     * and returns a PinCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public PinCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PinCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE), pe);
        }
    }

}
