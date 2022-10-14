package seedu.guest.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_DATE_RANGE;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_IS_ROOM_CLEAN;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_NUMBER_OF_GUESTS;
import static seedu.guest.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.EditCommand;
import seedu.guest.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.guest.logic.parser.exceptions.ParseException;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DATE_RANGE,
                        PREFIX_NUMBER_OF_GUESTS, PREFIX_IS_ROOM_CLEAN);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditGuestDescriptor editGuestDescriptor = new EditGuestDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editGuestDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editGuestDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editGuestDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_RANGE).isPresent()) {
            editGuestDescriptor.setDateRange(ParserUtil.parseDateRange(argMultimap.getValue(PREFIX_DATE_RANGE).get()));
        }
        if (argMultimap.getValue(PREFIX_NUMBER_OF_GUESTS).isPresent()) {
            editGuestDescriptor.setNumberOfGuests(ParserUtil
                    .parseNumberOfGuests(argMultimap.getValue(PREFIX_NUMBER_OF_GUESTS).get()));
        }
        if (argMultimap.getValue(PREFIX_IS_ROOM_CLEAN).isPresent()) {
            editGuestDescriptor.setIsRoomClean(ParserUtil
                    .parseIsRoomClean(argMultimap.getValue(PREFIX_IS_ROOM_CLEAN).get()));
        }

        if (!editGuestDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editGuestDescriptor);
    }

}
