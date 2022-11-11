package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CliSyntax.PREFIX_ID;
import static coydir.logic.parser.CliSyntax.PREFIX_RATE;

import java.util.stream.Stream;

import coydir.logic.commands.RateCommand;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.person.EmployeeId;
import coydir.model.person.Rating;

/**
 * Parses input arguments and creates a new AddLeaveCommand object.
 */
public class RateCommandParser implements Parser<RateCommand> {

    /**
     * Parses input arguments and creates a new AddLeaveCommand object.
     */
    public RateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_RATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_RATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
        }
        String id = ParserUtil.parseNumber(argMultimap.getValue(PREFIX_ID).get());
        Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATE).get());
        return new RateCommand(new EmployeeId(id), rating);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
