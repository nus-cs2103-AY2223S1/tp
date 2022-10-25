package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.EditBuyerCommand.EditPersonDescriptor;
import seedu.address.model.buyer.Buyer;

/**
 * A utility class for Buyer.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code buyer}.
     */
    public static String getAddCommand(Buyer buyer) {
        return AddBuyerCommand.COMMAND_WORD + " " + getPersonDetails(buyer);
    }

    /**
     * Returns the part of command string for the given {@code buyer}'s details.
     */
    public static String getPersonDetails(Buyer buyer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + buyer.getName().fullName + " ");
        sb.append(PREFIX_PHONE + buyer.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + buyer.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + buyer.getAddress().value + " ");
        if (buyer.getPriceRange().isPresent()) {
            sb.append(PREFIX_PRICE_RANGE + buyer.getPriceRange().get().toString());
        }
        if (buyer.getDesiredCharacteristics().isPresent()) {
            sb.append(PREFIX_CHARACTERISTICS + buyer.getDesiredCharacteristics().get().toString());
        }
        sb.append(PREFIX_PRIORITY + buyer.getPriority().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getPriceRange().ifPresent(priceRange -> sb.append(PREFIX_PRICE_RANGE)
                .append(priceRange).append(" "));
        descriptor.getDesiredCharacteristics().ifPresent(desiredCharacteristics -> sb.append(PREFIX_CHARACTERISTICS)
                .append(desiredCharacteristics).append(" "));
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY)
                .append(priority.specifiedPriority).append(" "));
        return sb.toString();
    }
}
