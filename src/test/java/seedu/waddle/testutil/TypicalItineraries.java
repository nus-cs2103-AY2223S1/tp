package seedu.waddle.testutil;

import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_END_DATE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_END_DATE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_NAME_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_NAME_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in tests.
 */
public class TypicalItineraries {

    // Manually added - Itinerary's details found in {@code CommandTestUtil}
    public static final Itinerary SUMMER = new ItineraryBuilder().withName(VALID_NAME_SUMMER)
            .withCountry(VALID_COUNTRY_SUMMER).withStartDate(VALID_START_DATE_SUMMER)
            .withEndDate(VALID_END_DATE_SUMMER).withPeople(VALID_PEOPLE_SUMMER).build();
    public static final Itinerary WINTER = new ItineraryBuilder().withName(VALID_NAME_WINTER)
            .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
            .withEndDate(VALID_END_DATE_WINTER).withPeople(VALID_PEOPLE_WINTER).build();

    public static final Itinerary SPRING = new ItineraryBuilder().withName("Summer Trip")
            .withCountry("Australia").withStartDate("2022-01-01")
            .withEndDate("2022-01-15").withPeople("1").build();
    public static final Itinerary AUTUMN = new ItineraryBuilder().withName("Winter Trip")
            .withCountry("Canada").withStartDate("2022-02-02")
            .withEndDate("2022-02-23").withPeople("2").build();
    public static final Itinerary GRADUATION = new ItineraryBuilder().withName("Graduation Trip")
            .withCountry("France").withStartDate("2022-03-03")
            .withEndDate("2022-03-07").withPeople("4").build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItineraries() {} // prevents instantiation

    /**
     * Returns a {@code Waddle} with all the typical itineraries.
     */
    public static Waddle getTypicalWaddle() {
        Waddle waddle = new Waddle();
        for (Itinerary itinerary : getTypicalItineraries()) {
            waddle.addItinerary(itinerary);
        }
        return waddle;
    }

    public static List<Itinerary> getTypicalItineraries() {
        return new ArrayList<>(Arrays.asList(SUMMER, WINTER, SPRING));
    }
}
