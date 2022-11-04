package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_IMAGE_UPLOAD;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_INTERESTEDCLIENTS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.commons.core.index.Index;
import seedu.condonery.logic.commands.property.EditPropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.logic.parser.ArgumentMultimap;
import seedu.condonery.logic.parser.ArgumentTokenizer;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditPropertyCommand object
 */
public class EditPropertyCommandParser implements Parser<EditPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPropertyCommand
     * and returns a Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PRICE, PREFIX_TAG,
                        PREFIX_PROPERTY_TYPE, PREFIX_IMAGE_UPLOAD, PREFIX_INTERESTEDCLIENTS,
                        PREFIX_PROPERTY_STATUS);
        EditPropertyDescriptor editPropertyDescriptor =
                new EditPropertyDescriptor();
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPropertyCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_NAME).isPresent()
            && !argMultimap.getValue(PREFIX_ADDRESS).isPresent()
            && !argMultimap.getValue(PREFIX_PRICE).isPresent()
            && !argMultimap.getValue(PREFIX_PROPERTY_TYPE).isPresent()
            && !argMultimap.getValue(PREFIX_PROPERTY_STATUS).isPresent()
            && !argMultimap.getValue(PREFIX_IMAGE_UPLOAD).isPresent()
            && argMultimap.getAllValues(PREFIX_TAG).size() == 0) {
            throw new ParseException(EditPropertyCommand.MESSAGE_NOT_EDITED);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPropertyDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPropertyDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            try {
                Integer.parseInt(argMultimap.getValue(PREFIX_PRICE).get());
            } catch (NumberFormatException e) {
                throw new ParseException(Messages.MESSAGE_NUMBER_OUT_OF_RANGE);
            }
            editPropertyDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPropertyDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_INTERESTEDCLIENTS).isPresent()) {
            editPropertyDescriptor.setInterestedClients(
                    ParserUtil.parseClients(argMultimap.getAllValues(PREFIX_INTERESTEDCLIENTS)));
        }

        if (argMultimap.getValue(PREFIX_PROPERTY_TYPE).isPresent()) {
            editPropertyDescriptor
                    .setPropertyTypeEnum(
                            ParserUtil.parsePropertyType(
                                    argMultimap.getValue(PREFIX_PROPERTY_TYPE).get()));
        }

        if (argMultimap.getValue(PREFIX_PROPERTY_STATUS).isPresent()) {
            try {
                editPropertyDescriptor.setPropertyStatusEnum(
                        ParserUtil.parsePropertyStatus(
                                argMultimap.getValue(PREFIX_PROPERTY_STATUS).get()));
            } catch (IllegalArgumentException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_STATUS,
                        EditPropertyCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_IMAGE_UPLOAD).isPresent()) {
            return new EditPropertyCommand(index, editPropertyDescriptor, true);
        }

        return new EditPropertyCommand(index, editPropertyDescriptor);
    }

    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}

