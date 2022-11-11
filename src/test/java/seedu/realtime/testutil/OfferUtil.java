package seedu.realtime.testutil;

import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_OFFER;

import seedu.realtime.logic.commands.AddOfferCommand;
import seedu.realtime.logic.commands.EditOfferCommand.EditOfferDescriptor;
import seedu.realtime.model.offer.Offer;

/**
 * A utility class for Offer
 */
public class OfferUtil {

    /**
     * Returns an add command string for adding the {@code offer}.
     */
    public static String getAddCommand(Offer offer) {
        return AddOfferCommand.COMMAND_WORD + " " + getOfferDetails(offer);
    }

    /**
     * Returns the part of command string for the given {@code offer}'s details.
     */
    public static String getOfferDetails(Offer offer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + offer.getClient().fullName + " ");
        sb.append(PREFIX_LISTING_ID + offer.getListing().value + " ");
        sb.append(PREFIX_OFFER + offer.getOfferPrice().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the give {@code EditOfferDescriptor}'s details.
     */
    public static String getEditOfferDescriptorDetails(EditOfferDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getOfferPrice().ifPresent(offerPrice -> sb.append(PREFIX_OFFER).append(offerPrice).append(" "));
        descriptor.getListing().ifPresent(listingId -> sb.append(PREFIX_LISTING_ID).append(listingId).append(" "));
        return sb.toString();
    }
}
