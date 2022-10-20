package tracko.logic.parser.order;

import static java.util.Objects.requireNonNull;
import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CliSyntax.PREFIX_ITEM;
import static tracko.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import tracko.commons.core.index.Index;
import tracko.logic.commands.item.EditItemCommand;
import tracko.logic.commands.order.EditOrderCommand;
import tracko.logic.commands.order.EditOrderCommand.EditOrderDescriptor;
import tracko.logic.parser.ArgumentMultimap;
import tracko.logic.parser.ArgumentTokenizer;
import tracko.logic.parser.CliSyntax;
import tracko.logic.parser.Parser;
import tracko.logic.parser.ParserUtil;
import tracko.logic.parser.Prefix;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.order.ItemQuantityPair;
import tracko.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                    CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_ITEM, CliSyntax.PREFIX_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditOrderCommand.MESSAGE_USAGE), pe);
        }

        EditOrderDescriptor editOrderDescriptor = new EditOrderDescriptor();
        parseContacts(editOrderDescriptor, argMultimap);
        parseItemQuantity(editOrderDescriptor, argMultimap);

        if (!editOrderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditItemCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
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

    public boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    /**
     * Detects whether {@code argMultimap} contains prefixes of the customer's details and stores
     * the updated data into an {@code editOrderDescriptor}.
     *
     * @param editOrderDescriptor The descriptor to store the updated data.
     * @param argMultimap The map that stores the prefixes inputted by the user.
     * @throws ParseException Exception that is thrown when the user inputs the wrong prefixes.
     */
    public void parseContacts(EditOrderDescriptor editOrderDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME)) {
            editOrderDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_PHONE)) {
            editOrderDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_EMAIL)) {
            editOrderDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()));
        }
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ADDRESS)) {
            editOrderDescriptor.setAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get()));
        }
    }

    /**
     * Detects whether {@code argMultimap} contains item and quantity prefixes and stores
     * the data into an {@code editOrderDescriptor}.
     *
     * @param editOrderDescriptor The descriptor to store the updated data.
     * @param argMultimap The map that stores the prefixes inputted by the user.
     * @throws ParseException Exception that is thrown when the user inputs one prefix without the other.
     */
    public void parseItemQuantity(EditOrderDescriptor editOrderDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {

        boolean isItemPrefixPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ITEM);
        boolean isQuantityPrefixPresent = arePrefixesPresent(argMultimap, CliSyntax.PREFIX_QUANTITY);

        if ((isItemPrefixPresent && !isQuantityPrefixPresent)
                || (!isItemPrefixPresent && isQuantityPrefixPresent)) {
            throw new ParseException("Item prefix should be accompanied with a quantity prefix, and vice versa.");
        }

        if (isItemPrefixPresent && isQuantityPrefixPresent) {
            String item = argMultimap.getValue(PREFIX_ITEM).get();
            Integer quantity = Integer.parseInt(argMultimap.getValue(PREFIX_QUANTITY).get());
            editOrderDescriptor.setItemToEdit(new ItemQuantityPair(item, quantity));
        }
    }
}
