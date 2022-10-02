package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BookCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Appointment;

/**
 * Parses input arguments and creates a new BookCommand object.
 */
public class BookCommandParser implements Parser<BookCommand> {
    @Override
    public BookCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REASON, PREFIX_DATE);
        Index index;


        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BookCommand.MESSAGE_USAGE), pe);
        }

        Appointment appointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_REASON).get(),
                argMultimap.getValue(PREFIX_DATE).get());
        return new BookCommand(index, appointment);
    }
}
