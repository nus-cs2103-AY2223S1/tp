package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.trackascholar.logic.commands.UnPinCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.applicant.Name;


/**
 * Parses input arguments and creates a new UnPinCommand object.
 */
public class UnPinCommandParser implements Parser<UnPinCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PinCommand
     * and returns a PinCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public UnPinCommand parse(String args) throws ParseException {
        try {
            Name name = ParserUtil.parseName(args);
            return new UnPinCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnPinCommand.MESSAGE_USAGE), pe);
        }
    }

}
