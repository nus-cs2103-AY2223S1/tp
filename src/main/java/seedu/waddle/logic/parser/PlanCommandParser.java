package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DAY_NUMBER;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.PlanCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.DayNumber;

/**
 * Parses input arguments and creates a new PlanCommand object
 */
public class PlanCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the PlanCommand
     * and returns a PlanCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public PlanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY_NUMBER, PREFIX_START_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY_NUMBER, PREFIX_START_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   PlanCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlanCommand.MESSAGE_USAGE), pe);
        }

        DayNumber dayNumber = ParserUtil.parseDayNumber(argMultimap.getValue(PREFIX_DAY_NUMBER).get());
        LocalTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());

        return new PlanCommand(index, dayNumber, startTime);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
