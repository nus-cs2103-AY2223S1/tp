package tracko.logic.parser.item;

import static java.util.Objects.requireNonNull;
import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import tracko.commons.core.index.Index;
import tracko.logic.commands.item.EditItemCommand;
import tracko.logic.commands.item.EditItemCommand.EditItemDescriptor;
import tracko.logic.parser.ArgumentMultimap;
import tracko.logic.parser.ArgumentTokenizer;
import tracko.logic.parser.CliSyntax;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditItemCommand object
 */
public class EditItemCommandParser implements Parser<EditItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditItemCommand
     * and returns an EditItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_ITEM, CliSyntax.PREFIX_QUANTITY,
                        CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_SELL_PRICE, CliSyntax.PREFIX_COST_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItemCommand.MESSAGE_USAGE), pe);
        }

        EditItemDescriptor editItemDescriptor = new EditItemDescriptor();

        parseArguments(editItemDescriptor, argMultimap);

        if (!editItemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditItemCommand.MESSAGE_NOT_EDITED);
        }

        return new EditItemCommand(index, editItemDescriptor);
    }

    /**
     * Parses the arguments that are inputted by the user.
     *
     * @param editItemDescriptor The descriptor that stores the updated value.
     * @param argMultimap The arguments supplied by the user.
     * @throws ParseException Exception that is thrown if the arguments are provided wrongly.
     */
    public void parseArguments(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getValue(CliSyntax.PREFIX_ITEM).isPresent()) {
            editItemDescriptor.setItemName(ParserUtil.parseItemName(argMultimap.getValue(CliSyntax.PREFIX_ITEM).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_QUANTITY).isPresent()) {
            editItemDescriptor.setQuantity(ParserUtil
                    .parseQuantity(argMultimap.getValue(CliSyntax.PREFIX_QUANTITY).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).isPresent()) {
            editItemDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG)).ifPresent(editItemDescriptor::setTags);

        if (argMultimap.getValue(CliSyntax.PREFIX_SELL_PRICE).isPresent()) {
            editItemDescriptor.setSellPrice(ParserUtil
                    .parsePrice(argMultimap.getValue(CliSyntax.PREFIX_SELL_PRICE).get()));
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_COST_PRICE).isPresent()) {
            editItemDescriptor.setCostPrice(ParserUtil
                    .parsePrice(argMultimap.getValue(CliSyntax.PREFIX_COST_PRICE).get()));
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
