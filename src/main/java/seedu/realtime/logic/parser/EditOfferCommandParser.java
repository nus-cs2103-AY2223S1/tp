package seedu.realtime.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_OFFER;

import seedu.realtime.commons.core.index.Index;
import seedu.realtime.logic.commands.EditOfferCommand;
import seedu.realtime.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditOfferCommand object
 */
public class EditOfferCommandParser implements Parser<EditOfferCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditOfferCommand
     * and returns an EditOfferCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOfferCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_LISTING_ID, PREFIX_NAME, PREFIX_OFFER);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditOfferCommand.MESSAGE_USAGE), pe);
        }

        EditOfferCommand.EditOfferDescriptor editOfferDescriptor = new EditOfferCommand.EditOfferDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editOfferDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_LISTING_ID).isPresent()) {
            editOfferDescriptor.setListing(ParserUtil.parseListingId(argMultimap.getValue(PREFIX_LISTING_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_OFFER).isPresent()) {
            editOfferDescriptor.setOfferPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_OFFER).get()));
        }

        if (!editOfferDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditOfferCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOfferCommand(index, editOfferDescriptor);
    }

}
