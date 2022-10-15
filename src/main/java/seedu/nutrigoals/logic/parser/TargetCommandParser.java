package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.nutrigoals.logic.commands.TargetCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.Calorie;

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
            Calorie calorie = ParserUtil.parseCalorie(args);
            return new TargetCommand(calorie);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TargetCommand.MESSAGE_USAGE), pe);
        }
    }
}
