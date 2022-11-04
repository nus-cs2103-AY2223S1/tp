package seedu.address.logic.parser.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorial.EditTutorialCommand;
import seedu.address.logic.commands.tutorial.EditTutorialCommand.EditTutorialDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.datetime.DatetimeCommonUtils;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditTutorialCommandParser implements Parser<EditTutorialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTutorialCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE, PREFIX_VENUE,
                        PREFIX_TIME, PREFIX_DATE_DAY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTutorialCommand.MESSAGE_USAGE), pe);
        }

        EditTutorialDescriptor editTutorialDescriptor = new EditTutorialDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTutorialDescriptor.setName(TutorialParserUtil
                    .parseTutorialName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            editTutorialDescriptor.setModule(ParserUtil
                    .parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get()));
        }
        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editTutorialDescriptor.setVenue(ParserUtil
                    .parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_DAY).isPresent() && argMultimap.getValue(PREFIX_TIME).isPresent()) {
            String day = argMultimap.getValue(PREFIX_DATE_DAY).get();
            String timeslot = argMultimap.getValue(PREFIX_TIME).get();
            editTutorialDescriptor.setTimeslot(DatetimeCommonUtils
                    .parseWeeklyTimeslot(day, timeslot));
        }

        //Only day
        if (argMultimap.getValue(PREFIX_DATE_DAY).isPresent() && !argMultimap.getValue(PREFIX_TIME).isPresent()) {
            throw new ParseException(EditTutorialCommand.MESSAGE_DATETIME_TUTORIAL);
        }
        //Only timeslot
        if (!argMultimap.getValue(PREFIX_DATE_DAY).isPresent() && argMultimap.getValue(PREFIX_TIME).isPresent()) {
            throw new ParseException(EditTutorialCommand.MESSAGE_DATETIME_TUTORIAL);
        }
        if (!editTutorialDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTutorialCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTutorialCommand(index, editTutorialDescriptor);
    }

}

