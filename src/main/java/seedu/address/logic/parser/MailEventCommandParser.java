package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MailEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MailEventCommand object
 */
public class MailEventCommandParser implements Parser<MailEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MailEventCommand
     * and returns a MailEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MailEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
