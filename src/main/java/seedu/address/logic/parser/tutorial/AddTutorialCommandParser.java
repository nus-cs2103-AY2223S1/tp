package seedu.address.logic.parser.tutorial;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.tutorial.AddTutorialCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialDay;
import seedu.address.model.tutorial.TutorialModule;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.TutorialTimeslot;
import seedu.address.model.tutorial.TutorialVenue;


/**
 * Parses input arguments and creates a new AddTutorialCommand object
 */
public class AddTutorialCommandParser implements Parser<AddTutorialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorialCommand
     * and returns an AddTutorialCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTutorialCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE,
                        PREFIX_TIMESLOT, PREFIX_DAY);

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE,
                PREFIX_TIMESLOT, PREFIX_DAY);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTutorialCommand.MESSAGE_USAGE));
        }

        TutorialName name = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_NAME).get());
        TutorialModule module = ParserUtil.parseTutorialModule(argMultimap.getValue(PREFIX_MODULE).get());
        TutorialVenue venue = ParserUtil.parseTutorialVenue(argMultimap.getValue(PREFIX_VENUE).get());
        TutorialTimeslot timeslot = ParserUtil.parseTutorialTimeslot(argMultimap.getValue(PREFIX_TIMESLOT).get());
        TutorialDay day = ParserUtil.parseTutorialDay(argMultimap.getValue(PREFIX_DAY).get());

        Tutorial tutorial = new Tutorial(name, module, venue, timeslot, day);

        return new AddTutorialCommand(tutorial);
    }
}
