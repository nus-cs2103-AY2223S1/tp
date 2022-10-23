package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Used to throw the error during command execution as {@code CommandLine#execute} does not throw an Exception
 */
public class ParameterExceptionHandler implements CommandLine.IParameterExceptionHandler {
    @Override
    public int handleParseException(CommandLine.ParameterException ex, String[] args) throws Exception {
        System.out.println("here");
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ex.getMessage()));
    }
}
