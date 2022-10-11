package seedu.travelr.testutil;

import seedu.address.logic.commands.AddCommand;
import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

import java.util.Set;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

/**
 * A utility class for Trip.
 */
public class TripUtil {

    /**
     * Returns an add command string for adding the {@code trip}.
     */
    public static String getAddCommand(Trip trip) {
        return AddCommand.COMMAND_WORD + " " + getTripDetails(trip);
    }

    /**
     * Returns the part of command string for the given {@code trip}'s details.
     */
    public static String getTripDetails(Trip trip) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + trip.getTitle().fullTitle + " ");
        sb.append(PREFIX_DESC + trip.getDescription().value + " ");
        trip.getEvents().stream().forEach(
            s -> sb.append(PREFIX_EVENT + s.getTitle().toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTripDescriptor}'s details.
     */
    public static String getEditTripDescriptorDetails(EditTripDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.fullTitle).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESC).append(description.value).append(" "));
        if (descriptor.getEvents().isPresent()) {
            Set<Event> events = descriptor.getEvents().get();
            if (events.isEmpty()) {
                sb.append(PREFIX_EVENT);
            } else {
                events.forEach(s -> sb.append(PREFIX_EVENT).append(s.getTitle().toString()).append(" "));
            }
        }
        return sb.toString();
    }
}
