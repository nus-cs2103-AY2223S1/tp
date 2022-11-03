package seedu.address.logic.parser.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditPropertyCommand object
 */
public class EditPropertyCommandParser extends Parser<EditPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPropertyCommand
     * and returns an EditPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_NAME, PREFIX_PRICE, PREFIX_ADDRESS, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
                        PREFIX_CHARACTERISTICS, PREFIX_OWNER_NAME, PREFIX_PHONE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, EditPropertyCommand.MESSAGE_USAGE), pe);
        }

        EditPropertyDescriptor editPropertyDescriptor = new EditPropertyDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPropertyDescriptor.setName(ParserUtil.parsePropertyName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            editPropertyDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPropertyDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPropertyDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_CHARACTERISTICS).isPresent()) {
            editPropertyDescriptor.setCharacteristics(ParserUtil
                    .parseCharacteristics(argMultimap.getValue(PREFIX_CHARACTERISTICS).get()));
        }

        if (argMultimap.getValue(PREFIX_OWNER_NAME).isPresent()) {
            editPropertyDescriptor.setOwnerName(ParserUtil
                    .parseName(argMultimap.getValue(PREFIX_OWNER_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPropertyDescriptor.setOwnerPhone(ParserUtil
                    .parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }


        if (!editPropertyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPropertyCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPropertyCommand(index, editPropertyDescriptor);
    }
}
