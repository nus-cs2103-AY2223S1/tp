package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DATE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_END_TIME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import longtimenosee.logic.commands.AddEventCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.Duration;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Name;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform to  the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION,
                        PREFIX_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);
        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_NAME, PREFIX_DATE,
                PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_START_TIME).get(),
                argMultimap.getValue(PREFIX_END_TIME).get());
        Event event = new Event(description, name, date, duration);
        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
