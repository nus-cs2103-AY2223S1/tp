package eatwhere.foodguide.testutil;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_CUISINE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_LOCATION;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_NAME;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_PRICE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import eatwhere.foodguide.logic.commands.AddCommand;
import eatwhere.foodguide.logic.commands.EditCommand.EditEateryDescriptor;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.tag.Tag;

/**
 * A utility class for Eatery.
 */
public class EateryUtil {

    /**
     * Returns an add command string for adding the {@code eatery}.
     */
    public static String getAddCommand(Eatery eatery) {
        return AddCommand.COMMAND_WORD + " " + getEateryDetails(eatery);
    }

    /**
     * Returns the part of command string for the given {@code eatery}'s details.
     */
    public static String getEateryDetails(Eatery eatery) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + eatery.getName().fullName + " ");
        sb.append(PREFIX_PRICE + eatery.getPrice().value + " ");
        sb.append(PREFIX_CUISINE + eatery.getCuisine().value + " ");
        sb.append(PREFIX_LOCATION + eatery.getLocation().value + " ");
        eatery.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEateryDescriptor}'s details.
     */
    public static String getEditEateryDescriptorDetails(EditEateryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PRICE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_CUISINE).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_LOCATION).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
