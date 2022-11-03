package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import seedu.travelr.logic.commands.AddCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.trip.Trip;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final String INVALID_DATE_ERROR_MESSAGE = "Invalid date provided. \nPlease check if you have accounted for leap years.";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESC, PREFIX_LOCATION, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DESC, PREFIX_LOCATION, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());
        DateField dateField = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        String dateFieldValue = dateField.toString();
        if (!dateFieldValue.equals(argMultimap.getValue(PREFIX_DATE).get())) {
            throw new ParseException(INVALID_DATE_ERROR_MESSAGE);
        }

        Trip trip = new Trip(title, description, location, dateField);

        return new AddCommand(trip);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
