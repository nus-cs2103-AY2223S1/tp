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

    public static final Itinerary ALICE = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary BENSON = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary CARL = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary DANIEL = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary ELLE = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary FIONA = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary GEORGE = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();

    // Manually added
    public static final Itinerary HOON = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();
    public static final Itinerary IDA = new ItineraryBuilder().withName("Alice Pauline")
            .withCountry("123, Jurong West Ave 6, #08-111").withStartDate("alice@example.com")
            .withEndDate("94351253").withPeople("friends").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Itinerary SUMMER = new ItineraryBuilder().withName(VALID_NAME_SUMMER)
            .withCountry(VALID_COUNTRY_SUMMER).withStartDate(VALID_START_DATE_SUMMER)
            .withEndDate(VALID_END_DATE_SUMMER).withPeople(VALID_PEOPLE_SUMMER).build();
    public static final Itinerary WINTER = new ItineraryBuilder().withName(VALID_NAME_WINTER)
            .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
            .withEndDate(VALID_END_DATE_WINTER).withPeople(VALID_PEOPLE_WINTER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItineraries() {} // prevents instantiation

    /**
     * Returns a {@code Waddle} with all the typical itineraries.
     */
    public static Waddle getTypicalWaddle() {
        Waddle ab = new Waddle();
        for (Itinerary person : getTypicalItineraries()) {
            ab.addItinerary(person);
        }
        return ab;
    }

    public static List<Itinerary> getTypicalItineraries() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
