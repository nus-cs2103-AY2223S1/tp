package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.stream.Stream;

import seedu.travelr.logic.commands.AddEventCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;

/**
 * Parses input arguments and creates a new AddEventCommand object.
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESC);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DESC) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());

        Event event = new Event(title, description);

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
