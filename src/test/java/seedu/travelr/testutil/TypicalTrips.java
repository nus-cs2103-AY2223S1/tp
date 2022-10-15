package seedu.travelr.testutil;

//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_EATING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_GERMANY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.travelr.model.AddressBook;
import seedu.travelr.model.trip.Trip;

/**
 * A utility class containing a list of {@code Trip} objects to be used in tests.
 */
public class TypicalTrips {

    public static final Trip MALAYSIA = new TripBuilder().withTitle("Malaysia Trip")
            .withDescription("Going around Malaysia.")
            .withEvents("Night Market Tour").build();
    public static final Trip TAIWAN = new TripBuilder().withTitle("Taiwan Trip")
            .withEvents("Drink Bubble Tea", "Eat street snacks").build();
    public static final Trip JAPAN = new TripBuilder().withTitle("Japan Trip").withDescription("Going around Japan")
            .build();
    public static final Trip THAILAND = new TripBuilder().withTitle("Thailand Trip")
            .withDescription("Going around Thailand")
            .withEvents("Temple visit").build();
    public static final Trip EUROPE = new TripBuilder().withTitle("Europe Trip")
            .withDescription("Touring across Europe").build();
    public static final Trip MOON = new TripBuilder().withTitle("Moon Exploration")
            .withDescription("One small leap for man").build();
    public static final Trip MARS = new TripBuilder().withTitle("Mars Exploration").withDescription("Colonising Mars")
           .build();

    // Manually added
    public static final Trip PLUTO = new TripBuilder().withTitle("Pluto venture").withDescription("Dwarf planet")
            .build();
    public static final Trip SUN = new TripBuilder().withTitle("Sun exploration")
            .withDescription("A bit hot in here eh?").build();

    // Manually added - Trip's details found in {@code CommandTestUtil}
    public static final Trip GERMANY = new TripBuilder().withTitle(VALID_TITLE_GERMANY)
            .withDescription(VALID_DESCRIPTION_GERMANY).withEvents(VALID_EVENT_EATING).build();
    public static final Trip ANTARCTICA = new TripBuilder().withTitle(VALID_TITLE_ANTARCTICA)
            .withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(VALID_EVENT_SIGHTSEEING, VALID_EVENT_EATING)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTrips() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical trips.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Trip trip : getTypicalTrips()) {
            ab.addTrip(trip);
        }
        return ab;
    }

    public static List<Trip> getTypicalTrips() {
        return new ArrayList<>(Arrays.asList(MALAYSIA, TAIWAN, JAPAN, THAILAND, EUROPE, MARS, MOON));
    }
}
