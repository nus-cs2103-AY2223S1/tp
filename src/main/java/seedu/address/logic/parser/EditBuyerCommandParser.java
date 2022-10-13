package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.parseTagsForEdit;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.EditBuyerCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditBuyerCommand object
 */
public class EditBuyerCommandParser extends Parser<EditBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBuyerCommand
     * and returns an EditBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBuyerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_PRICE_RANGE, PREFIX_CHARACTERISTICS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBuyerCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            editPersonDescriptor.setPriceRange(ParserUtil
                    .parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get()));
        }
        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            editPersonDescriptor.setDesiredCharacteristics(ParserUtil
                    .parseCharacteristics(argMultimap.getValue(PREFIX_CHARACTERISTICS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBuyerCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBuyerCommand(index, editPersonDescriptor);
    }

}
