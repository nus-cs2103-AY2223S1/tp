package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectAppointmentCommand object
 */
public class SelectAppointmentCommandParser implements Parser<SelectAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectAppointmentCommand
     * and returns a SelectAppointmentCommand object for execution.
     *
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     *                                                              the expected format
     */
    public SelectAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SelectAppointmentCommand.MESSAGE_USAGE), pe);
        }

        return new SelectAppointmentCommand(index);
    }
}
