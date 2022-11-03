package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.commons.core.Messages.MESSAGE_NEGATIVE_NUMBER;
import static seedu.condonery.commons.core.Messages.MESSAGE_NUMBER_INVALID;
import static seedu.condonery.commons.core.Messages.MESSAGE_RANGE_INVALID;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_LOWER;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_UPPER;

import java.util.stream.Stream;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.property.RangePropertyCommand;
import seedu.condonery.logic.parser.ArgumentMultimap;
import seedu.condonery.logic.parser.ArgumentTokenizer;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.Prefix;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.property.PropertyPriceWithinRangePredicate;

/**
 * Parses input arguments and creates a new RangePropertyCommand object
 */
public class RangePropertyCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns a Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LOWER, PREFIX_UPPER);

        if (!arePrefixesPresent(argMultimap, PREFIX_LOWER, PREFIX_UPPER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RangePropertyCommand.MESSAGE_USAGE));
        }

        try {
            ParserUtil.parseNumber(argMultimap.getValue(PREFIX_LOWER).get());
            ParserUtil.parseNumber(argMultimap.getValue(PREFIX_UPPER).get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_NUMBER_INVALID, RangePropertyCommand.MESSAGE_USAGE));
        }
        Integer lower = ParserUtil.parseNumber(argMultimap.getValue(PREFIX_LOWER).get());
        Integer upper = ParserUtil.parseNumber(argMultimap.getValue(PREFIX_UPPER).get());

        if (lower > upper) {
            throw new ParseException(String.format(MESSAGE_RANGE_INVALID, RangePropertyCommand.MESSAGE_USAGE));
        }

        if (Integer.signum(lower) == -1 || Integer.signum(upper) == -1) {
            throw new ParseException(String.format(MESSAGE_NEGATIVE_NUMBER, RangePropertyCommand.MESSAGE_USAGE));
        }
        return new RangePropertyCommand(new PropertyPriceWithinRangePredicate(lower, upper));

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
