package seedu.travelr.testutil;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.travelr.logic.commands.AddCommand;
import seedu.travelr.model.trip.Trip;

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
        sb.append(PREFIX_LOCATION + trip.getLocation().locationName + " ");
        sb.append(PREFIX_DATE + trip.getDateField().toString() + " ");
        return sb.toString();
    }

}
