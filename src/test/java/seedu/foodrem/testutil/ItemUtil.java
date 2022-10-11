package seedu.foodrem.testutil;

import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_BOUGHT_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_EXPIRY_DATE;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.foodrem.logic.parser.CliSyntax.PREFIX_ITEM_UNIT;

import seedu.foodrem.logic.commands.itemcommands.AddCommand;
import seedu.foodrem.logic.commands.itemcommands.EditCommand.EditItemDescriptor;
import seedu.foodrem.model.item.Item;

/**
 * A utility class for Item.
 */
public class ItemUtil {

    /**
     * Returns an add command string for adding the {@code item}.
     */
    public static String getAddCommand(Item item) {
        return AddCommand.COMMAND_WORD + " " + getItemDetails(item);
    }

    /**
     * Returns the part of command string for the given {@code item}'s details.
     */
    public static String getItemDetails(Item item) {
        return String.format("%s%s %s%s %s%s %s%s %s%s",
                PREFIX_NAME,
                item.getName(),
                PREFIX_ITEM_QUANTITY,
                item.getQuantity(),
                PREFIX_ITEM_UNIT,
                item.getUnit(),
                PREFIX_ITEM_BOUGHT_DATE,
                item.getBoughtDate(),
                PREFIX_ITEM_EXPIRY_DATE,
                item.getExpiryDate());
    }

    /**
     * Returns the part of command string for the given {@code EditItemDescriptor}'s details.
     */
    public static String getEditItemDescriptorDetails(EditItemDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getItemName()
                .ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getItemQuantity()
                .ifPresent(quantity -> sb.append(PREFIX_ITEM_QUANTITY).append(quantity).append(" "));
        descriptor.getItemUnit()
                .ifPresent(unit -> sb.append(PREFIX_ITEM_UNIT).append(unit).append(" "));
        descriptor.getItemExpiryDate()
                .ifPresent(boughtDate -> sb.append(PREFIX_ITEM_BOUGHT_DATE).append(boughtDate).append(" "));
        descriptor.getItemBoughtDate()
                .ifPresent(expiryDate -> sb.append(PREFIX_ITEM_EXPIRY_DATE).append(expiryDate).append(" "));
        //if (descriptor.getTags().isPresent()) {
        //    Set<Tag> tags = descriptor.getTags().get();
        //    if (tags.isEmpty()) {
        //        sb.append(PREFIX_TAG);
        //    } else {
        //        tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        //    }
        //}
        return sb.toString();

    }
}
