package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.sort.EmailComparator;
import seedu.address.model.sort.NameComparator;
import seedu.address.model.sort.PhoneComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @param args String argument parsed
     * @return new SortCommand
     * @throws ParseException
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL
                );

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new SortCommand(new NameComparator());
        }
        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new SortCommand(new PhoneComparator());
        }
        if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            return new SortCommand(new EmailComparator());
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
