package seedu.waddle.testutil;

import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PEOPLE;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.waddle.logic.commands.AddCommand;
import seedu.waddle.logic.commands.EditCommand.EditItineraryDescriptor;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * A utility class for Person.
 */
public class ItineraryUtil {

    /**
     * Returns an add command string for adding the {@code itinerary}.
     */
    public static String getAddCommand(Itinerary itinerary) {
        return AddCommand.COMMAND_WORD + " " + getItineraryDetails(itinerary);
    }

    /**
     * Returns the part of command string for the given {@code itinerary}'s details.
     */
    public static String getItineraryDetails(Itinerary itinerary) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + itinerary.getName().fullName + " ");
        sb.append(PREFIX_COUNTRY + itinerary.getCountry().toString() + " ");
        sb.append(PREFIX_START_DATE + itinerary.getStartDate().toString() + " ");
        sb.append(PREFIX_START_DATE + itinerary.getEndDate().toString() + " ");
        sb.append(PREFIX_PEOPLE + itinerary.getPeople().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditItineraryDescriptor}'s details.
     */
    public static String getEditItineraryDescriptorDetails(EditItineraryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getCountry().ifPresent(phone -> sb.append(PREFIX_COUNTRY).append(phone).append(" "));
        descriptor.getStartDate().ifPresent(email -> sb.append(PREFIX_START_DATE).append(email).append(" "));
        descriptor.getEndDate().ifPresent(address -> sb.append(PREFIX_END_DATE).append(address).append(" "));
        descriptor.getPeople().ifPresent(people -> sb.append(PREFIX_PEOPLE).append(people).append(" "));
        return sb.toString();
    }
}
