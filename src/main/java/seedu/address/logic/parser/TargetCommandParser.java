package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.TargetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Calorie;

/**
 * Parses input arguments and creates a new TargetCommand object
 */
public class TargetCommandParser implements Parser<TargetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TargetCommand
     * and returns an TargetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TargetCommand parse(String args) throws ParseException {
        try {
            Calorie index = ParserUtil.parseCalorie(args);
            return new TargetCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
