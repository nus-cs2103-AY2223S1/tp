package foodwhere.testutil;

import java.util.Set;

import foodwhere.logic.commands.AddCommand;
import foodwhere.logic.commands.EditCommand;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.detail.Detail;
import foodwhere.model.stall.Stall;

/**
 * A utility class for Stall.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code stall}.
     */
    public static String getAddCommand(Stall stall) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(stall);
    }

    /**
     * Returns the part of command string for the given {@code stall}'s details.
     */
    public static String getPersonDetails(Stall stall) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + stall.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + stall.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_ADDRESS + stall.getAddress().value + " ");
        stall.getDetails().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_DETAIL + s.detail + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getAddress().ifPresent(address ->
                sb.append(CliSyntax.PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getDetails().isPresent()) {
            Set<Detail> details = descriptor.getDetails().get();
            if (details.isEmpty()) {
                sb.append(CliSyntax.PREFIX_DETAIL);
            } else {
                details.forEach(s -> sb.append(CliSyntax.PREFIX_DETAIL).append(s.detail).append(" "));
            }
        }
        return sb.toString();
    }
}
