package seedu.address.testutil;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.model.item.Item;

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
        return item.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditItemDescriptor}'s details.
     */
    public static String getEditItemDescriptorDetails(EditItemDescriptor descriptor) {
        //StringBuilder sb = new StringBuilder();
        //descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        //descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        //descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        //descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        //if (descriptor.getTags().isPresent()) {
        //    Set<Tag> tags = descriptor.getTags().get();
        //    if (tags.isEmpty()) {
        //        sb.append(PREFIX_TAG);
        //    } else {
        //        tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
        //    }
        //}
        // return sb.toString();
        // TODO: Fix this
        return "";
    }
}
