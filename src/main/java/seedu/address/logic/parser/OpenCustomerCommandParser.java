package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OpenCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new OpenCustomerCommand object
 */
public class OpenCustomerCommandParser implements Parser<OpenCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OpenCustomerCommand
     * and returns a OpenCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OpenCustomerCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                return new OpenCustomerCommand();
            }
            Index index = ParserUtil.parseIndex(args);
            return new OpenCustomerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCustomerCommand.MESSAGE_USAGE), pe);
        }
    }

}
