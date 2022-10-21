package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PEOPLE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.stream.Stream;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COUNTRY, PREFIX_START_DATE,
                        PREFIX_END_DATE, PREFIX_PEOPLE, PREFIX_BUDGET);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_COUNTRY, PREFIX_START_DATE,
                PREFIX_END_DATE, PREFIX_PEOPLE, PREFIX_BUDGET)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Country country = ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get());
        Date startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        People people = ParserUtil.parsePeople(argMultimap.getValue(PREFIX_PEOPLE).get());
        Budget budget = ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get());

        Itinerary itinerary = new Itinerary(name, country, startDate, endDate, people, budget);

        return new AddCommand(itinerary);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
