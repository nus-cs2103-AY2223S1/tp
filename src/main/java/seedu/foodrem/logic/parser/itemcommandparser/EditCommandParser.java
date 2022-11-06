package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.itemcommands.EditCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

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
                        CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_ITEM_QUANTITY,
                        CliSyntax.PREFIX_ITEM_UNIT,
                        CliSyntax.PREFIX_ITEM_BOUGHT_DATE,
                        CliSyntax.PREFIX_ITEM_EXPIRY_DATE,
                        CliSyntax.PREFIX_ITEM_PRICE,
                        CliSyntax.PREFIX_ITEM_REMARKS);
        Index index = StringUtil.validateAndGetIndexFromString(argMultimap.getPreamble().trim(),
                                                               EditCommand.getUsage());

        EditItemDescriptor editItemDescriptor = new EditItemDescriptor();
        setItemNameIfValuePresent(editItemDescriptor, argMultimap);
        setItemQuantityIfValuePresent(editItemDescriptor, argMultimap);
        setItemUnitIfValuePresent(editItemDescriptor, argMultimap);
        setItemBoughtDateIfValuePresent(editItemDescriptor, argMultimap);
        setItemExpiryDateIfValuePresent(editItemDescriptor, argMultimap);
        setItemPriceIfValuePresent(editItemDescriptor, argMultimap);
        setItemRemarkIfValuePresent(editItemDescriptor, argMultimap);

        if (!editItemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editItemDescriptor);
    }

    /**
     * Sets ItemName of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemNameIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_NAME)) {
            editItemDescriptor.setItemName(
                    ParserUtil.parseItemName(argMultimap.getPresentValue(CliSyntax.PREFIX_NAME)));
        }
    }

    /**
     * Sets ItemQuantity of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemQuantityIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_ITEM_QUANTITY)) {
            editItemDescriptor.setItemQuantity(
                    ParserUtil.parseQuantity(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_QUANTITY)));
        }
    }

    /**
     * Sets ItemUnit of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemUnitIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_ITEM_UNIT)) {
            editItemDescriptor.setItemUnit(
                    ParserUtil.parseUnit(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_UNIT)));
        }
    }

    /**
     * Sets ItemBoughtDate of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemBoughtDateIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_ITEM_BOUGHT_DATE)) {
            editItemDescriptor.setItemBoughtDate(
                    ParserUtil.parseBoughtDate(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_BOUGHT_DATE)));
        }
    }

    /**
     * Sets ItemExpiryDate of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemExpiryDateIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_ITEM_EXPIRY_DATE)) {
            editItemDescriptor.setItemExpiryDate(
                    ParserUtil.parseExpiryDate(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_EXPIRY_DATE)));
        }
    }

    /**
     * Sets ItemPrice of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemPriceIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_ITEM_PRICE)) {
            editItemDescriptor.setItemPrice(
                    ParserUtil.parsePrice(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_PRICE)));
        }
    }

    /**
     * Sets ItemRemarks of the editItemDescriptor if the value is present in the ArgumentMultimap.
     */
    private void setItemRemarkIfValuePresent(EditItemDescriptor editItemDescriptor, ArgumentMultimap argMultimap) {
        if (argMultimap.isValuePresent(CliSyntax.PREFIX_ITEM_REMARKS)) {
            editItemDescriptor.setItemRemarks(
                    ParserUtil.parseRemarks(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_REMARKS)));
        }
    }
}
