package seedu.condonery.testutil;

import static seedu.condonery.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_STATUS;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.condonery.logic.commands.property.AddPropertyCommand;
import seedu.condonery.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.tag.Tag;

/**
 * A utility class for Property.
 */
public class PropertyUtil {

    /**
     * Returns an add command string for adding the {@code property}.
     */
    public static String getAddCommand(Property property) {
        return AddPropertyCommand.COMMAND_WORD + " " + getPropertyDetails(property);
    }

    /**
     * Returns the part of command string for the given {@code property}'s details.
     */
    public static String getPropertyDetails(Property property) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + property.getName().fullName + " ");
        sb.append(PREFIX_ADDRESS + property.getAddress().value + " ");
        sb.append(PREFIX_PRICE + property.getPrice().value + " ");
        property.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_PROPERTY_TYPE + property.getPropertyTypeEnum().toString() + " ");
        sb.append(PREFIX_PROPERTY_STATUS + property.getPropertyStatusEnum().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPropertyDescriptor}'s details.
     */
    public static String getEditPropertyDescriptorDetails(EditPropertyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getPropertyTypeEnum()
                .ifPresent(propertyType -> sb.append(PREFIX_PROPERTY_TYPE).append(propertyType).append(" "));
        descriptor.getPrice().ifPresent(price -> sb.append(PREFIX_PRICE).append(price.value).append(" "));
        System.out.println(sb.toString());
        return sb.toString();
    }
}
