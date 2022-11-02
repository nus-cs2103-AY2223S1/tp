package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CreateMeetingCommandParser.DATE_TIME_VALIDATOR;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMeetingCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditMeetingCommand
     * and returns an EditMeetingCommand object for execution.
     * @return a new command to edit a contact
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE),
                     pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editMeetingDescriptor.setDescription(ParserUtil
                    .parseMeetingDetails(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String processedEditedDateAndTime;
            try {
                processedEditedDateAndTime =
                    DATE_TIME_VALIDATOR.processDateTime(argMultimap.getValue(PREFIX_DATE).get());
            } catch (java.text.ParseException e) {
                throw new ParseException(e.getMessage());
            }
            editMeetingDescriptor.setDate(processedEditedDateAndTime);
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editMeetingDescriptor.setLocation(ParserUtil
                    .parseMeetingDetails(argMultimap.getValue(PREFIX_LOCATION).get()));
        }

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

}
