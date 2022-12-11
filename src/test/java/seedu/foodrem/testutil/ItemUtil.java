package seedu.foodrem.testutil;

import seedu.foodrem.commons.enums.CommandType;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.model.item.Item;

/**
 * A utility class for Item.
 */
public class ItemUtil {
    /**
     * Returns an add command string for adding the {@code item}.
     */
    public static String getAddCommand(Item item) {
        return CommandType.NEW_COMMAND.getCommandWord() + " " + getItemDetails(item);
    }

    /**
     * Returns the part of command string for the given {@code item}'s details.
     */
    public static String getItemDetails(Item item) {
        return String.format("%s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                CliSyntax.PREFIX_NAME,
                item.getName(),
                CliSyntax.PREFIX_ITEM_QUANTITY,
                item.getQuantity(),
                CliSyntax.PREFIX_ITEM_UNIT,
                item.getUnit(),
                CliSyntax.PREFIX_ITEM_BOUGHT_DATE,
                item.getBoughtDate(),
                CliSyntax.PREFIX_ITEM_EXPIRY_DATE,
                item.getExpiryDate(),
                CliSyntax.PREFIX_ITEM_PRICE,
                item.getPrice(),
                CliSyntax.PREFIX_ITEM_REMARKS,
                item.getRemarks());
    }

    /**
     * Returns the part of command string for the given {@code EditItemDescriptor}'s details.
     */
    public static String getEditItemDescriptorDetails(EditItemDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getItemName()
                .ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name).append(" "));
        descriptor.getItemQuantity()
                .ifPresent(quantity -> sb.append(CliSyntax.PREFIX_ITEM_QUANTITY).append(quantity).append(" "));
        descriptor.getItemUnit()
                .ifPresent(unit -> sb.append(CliSyntax.PREFIX_ITEM_UNIT).append(unit).append(" "));
        descriptor.getItemExpiryDate()
                .ifPresent(boughtDate -> sb.append(CliSyntax.PREFIX_ITEM_BOUGHT_DATE).append(boughtDate).append(" "));
        descriptor.getItemBoughtDate()
                .ifPresent(expiryDate -> sb.append(CliSyntax.PREFIX_ITEM_EXPIRY_DATE).append(expiryDate).append(" "));
        descriptor.getItemPrice()
                .ifPresent(price -> sb.append(CliSyntax.PREFIX_ITEM_PRICE).append(price).append(" "));
        descriptor.getItemRemarks()
                .ifPresent(remarks -> sb.append(CliSyntax.PREFIX_ITEM_REMARKS).append(remarks).append(" "));
        return sb.toString();
    }
}
