package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.*;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.EditCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COUNTRY, PREFIX_START_DATE,
                        PREFIX_ITINERARY_DURATION, PREFIX_PEOPLE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditItineraryDescriptor editItineraryDescriptor = new EditCommand.EditItineraryDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editItineraryDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_COUNTRY).isPresent()) {
            editItineraryDescriptor.setCountry(ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            editItineraryDescriptor.setStartDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_ITINERARY_DURATION).isPresent()) {
            editItineraryDescriptor.setDuration(ParserUtil.parseItineraryDuration(argMultimap.getValue(PREFIX_ITINERARY_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PEOPLE).isPresent()) {
            editItineraryDescriptor.setPeople(ParserUtil.parsePeople(argMultimap.getValue(PREFIX_PEOPLE).get()));
        }

        if (!editItineraryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editItineraryDescriptor);
    }

}
