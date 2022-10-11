package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_STAGE;

import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.StageCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class StageCommandParser implements Parser<StageCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code StageCommand}
     * and returns a {@code StageCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public StageCommand parse(String args) throws ParseException {
        Stages stage;

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StageCommand.MESSAGE_USAGE));
        }

        try {
            stage = Stages.valueOf(trimmedArgs.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_STAGE, StageCommand.MESSAGE_USAGE));
        }

        return new StageCommand(stage);
    }
}
