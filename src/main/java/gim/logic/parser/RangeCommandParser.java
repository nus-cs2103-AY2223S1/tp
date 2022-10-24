package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_END_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_RANGE_ADVANCED;

import java.util.stream.Stream;

import gim.logic.commands.RangeCommand;
import gim.logic.parser.exceptions.ParseException;
import gim.model.date.Date;
import gim.model.exercise.DateWithinRangePredicate;

/**
 * Parses input arguments and creates a new RangeCommand object
 */
public class RangeCommandParser implements Parser<RangeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RangeCommand
     * and returns a RangeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RangeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_END_DATE,
                PREFIX_RANGE_ADVANCED);

        // advanced version: when the user does not input any of the dates, but only inputs an integer with prefix l/
        if (arePrefixesPresent(argMultimap, PREFIX_RANGE_ADVANCED)
                && !arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_END_DATE)) {
            int days = Integer.parseInt(argMultimap.getValue(PREFIX_RANGE_ADVANCED).get());

            // Only accept non-negative integers for the number of days
            if (days < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RangeCommand.ADVANCED_MESSAGE_USAGE));
            }
            Date today = new Date();
            Date startDate = today.getPreviousDaysDate(days);
            return new RangeCommand(new DateWithinRangePredicate(startDate, today), true);
        }

        // basic version: the user inputs both start date with prefix d/ and end date with prefix e/
        // both date inputs are compulsory for the basic version
        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RangeCommand.MESSAGE_USAGE));
        }
        Date startDate;
        Date endDate;
        try {
            startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RangeCommand.MESSAGE_USAGE));
        }

        return new RangeCommand(new DateWithinRangePredicate(startDate, endDate));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
