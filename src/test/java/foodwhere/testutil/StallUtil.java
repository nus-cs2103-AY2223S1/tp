package foodwhere.testutil;

import java.util.Set;

import foodwhere.logic.commands.SAddCommand;
import foodwhere.logic.commands.SEditCommand;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.commons.Tag;
import foodwhere.model.stall.Stall;

/**
 * A utility class for Stall.
 */
public class StallUtil {

    /**
     * Returns an sadd command string for adding the {@code stall}.
     */
    public static String getSAddCommand(Stall stall) {
        return SAddCommand.COMMAND_WORD + " " + getStallDetails(stall);
    }

    /**
     * Returns the part of command string for the given {@code stall}'s details.
     */
    public static String getStallDetails(Stall stall) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + stall.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + stall.getAddress().value + " ");
        stall.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tag + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStallDescriptor}'s details.
     */
    public static String getEditStallDescriptorDetails(SEditCommand.EditStallDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(CliSyntax.PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tag).append(" "));
            }
        }
        return sb.toString();
    }
}
