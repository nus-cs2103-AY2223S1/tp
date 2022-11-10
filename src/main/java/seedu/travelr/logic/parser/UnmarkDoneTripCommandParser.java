package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.UnmarkDoneTripCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnmarkDoneTripCommand object
 */
public class UnmarkDoneTripCommandParser implements Parser<UnmarkDoneTripCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkDoneTripCommand
     * and returns an UnmarkDoneTripCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public UnmarkDoneTripCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new UnmarkDoneTripCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkDoneTripCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
