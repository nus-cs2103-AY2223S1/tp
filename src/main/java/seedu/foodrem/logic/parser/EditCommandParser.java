package seedu.foodrem.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_ITEM_NAME,
                        PREFIX_ITEM_QUANTITY,
                        PREFIX_ITEM_UNIT,
                        PREFIX_ITEM_BOUGHT_DATE,
                        PREFIX_ITEM_EXPIRY_DATE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditItemDescriptor editItemDescriptor = new EditItemDescriptor();
        if (argMultimap.isValuePresent(PREFIX_ITEM_NAME)) {
            editItemDescriptor.setItemName(ParserUtil.parseName(argMultimap.getPresentValue(PREFIX_ITEM_NAME)));
        }
        if (argMultimap.isValuePresent(PREFIX_ITEM_QUANTITY)) {
            editItemDescriptor.setItemQuantity(
                    ParserUtil.parseQuantity(argMultimap.getPresentValue(PREFIX_ITEM_QUANTITY)));
        }
        if (argMultimap.isValuePresent(PREFIX_ITEM_UNIT)) {
            editItemDescriptor.setItemUnit(
                    ParserUtil.parseUnit(argMultimap.getPresentValue(PREFIX_ITEM_UNIT)));
        }
        if (argMultimap.isValuePresent(PREFIX_ITEM_BOUGHT_DATE)) {
            editItemDescriptor.setItemBoughtDate(
                    ParserUtil.parseBoughtDate(argMultimap.getPresentValue(PREFIX_ITEM_BOUGHT_DATE)));
        }
        if (argMultimap.isValuePresent(PREFIX_ITEM_EXPIRY_DATE)) {
            editItemDescriptor.setItemExpiryDate(
                    ParserUtil.parseExpiryDate(argMultimap.getPresentValue(PREFIX_ITEM_EXPIRY_DATE)));
        }

        if (!editItemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editItemDescriptor);
    }

    ///**
    // * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
    // * If {@code tags} contain only one element which is an empty string, it will be parsed into a
    // * {@code Set<Tag>} containing zero tags.
    // */
    //private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
    //    assert tags != null;
    //
    //    if (tags.isEmpty()) {
    //        return Optional.empty();
    //    }
    //    Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
    //    return Optional.of(ParserUtil.parseTags(tagSet));
    //}
}
