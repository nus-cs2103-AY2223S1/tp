package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Map;

import seedu.clinkedin.logic.commands.HelpCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution. If the user input is empty, the
     * help message will be the general help message. If the user input is not empty,
     * the help message will be the help message of the command that the user inputs.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        // If no arguments are provided, return the default help command
        if (args.trim().length() == 0) {
            return new HelpCommand();
        }
        try {
            // If arguments are provided, parse the arguments and return the help command
            // associated with the command specified
            Map<String, String> allCommandMessages = CliSyntax.getAllCommandMessages();
            if (allCommandMessages.containsKey(args.trim())) {
                return new HelpCommand(allCommandMessages.get(args.trim()));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            }
        } catch (Exception e) {
            // If the argument provided is invalid, throw a parse exception to alert the
            // user that they have entered an invalid command
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }
}
