package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import coydir.logic.commands.BatchAddCommand;
import coydir.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class BatchAddCommandParser implements Parser<BatchAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BatchAddCommand
     * and returns a BatchAdd object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchAddCommand parse(String args) throws ParseException {
        try {
            String filename = ParserUtil.parseFileName(args);
            return new BatchAddCommand(filename);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchAddCommand.MESSAGE_USAGE), pe);
        }
    }

}
