package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the prefix to determine the sort condition.
 */
public class SortPersonCommandParser implements Parser<SortPersonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortPersonCommand
     * and returns a SortPersonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortPersonCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.equalsIgnoreCase(PREFIX_NAME.getPrefix())) {
            return new SortPersonCommand(SortPersonCommand.Criteria.NAME);
        }
        if (trimmedArgs.equalsIgnoreCase(PREFIX_COMPANY_NAME.getPrefix())) {
            return new SortPersonCommand(SortPersonCommand.Criteria.COMPANY_NAME);
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE));

    }
}
