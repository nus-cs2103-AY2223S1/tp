package seedu.waddle.testutil;

import static seedu.waddle.logic.commands.CommandTestUtil.VALID_BUDGET_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_BUDGET_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COUNTRY_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITINERARY_DESC_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PEOPLE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_SUMMER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_DATE_WINTER;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_0000;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_1200;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_1715;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_2330;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.DayNumber;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in tests.
 */
public class TypicalItineraries {

    public static final Itinerary SPRING = new ItineraryBuilder().withName("Spring Trip")
            .withCountry("Australia").withStartDate("2022-01-01")
            .withDuration("14").withPeople("1").withBudget("300").build();
    public static final Itinerary AUTUMN = new ItineraryBuilder().withName("Autumn Hiking")
            .withCountry("Canada").withStartDate("2022-02-02")
            .withDuration("22").withPeople("2").withBudget("700").build();
    public static final Itinerary GRADUATION = new ItineraryBuilder().withName("Graduation Trip")
            .withCountry("France").withStartDate("2022-03-03")
            .withDuration("4").withPeople("4").withBudget("2200").build();

    public static Itinerary getSpring() {
        return new ItineraryBuilder().withName("Spring Trip")
                .withCountry("Australia").withStartDate("2022-01-01")
                .withDuration("14").withPeople("1").withBudget("300").build();
    }

    public static Itinerary getAutumn() {
        return new ItineraryBuilder().withName("Autumn Hiking")
                .withCountry("Canada").withStartDate("2022-02-02")
                .withDuration("22").withPeople("2").withBudget("700").build();
    }

    public static Itinerary getGraduation() {
        return new ItineraryBuilder().withName("Graduation Trip")
                .withCountry("France").withStartDate("2022-03-03")
                .withDuration("4").withPeople("4").withBudget("2200").build();
    }

    // Manually added - Itinerary's details found in {@code CommandTestUtil}
    public static final Itinerary SUMMER = new ItineraryBuilder().withName(VALID_ITINERARY_DESC_SUMMER)
            .withCountry(VALID_COUNTRY_SUMMER).withStartDate(VALID_START_DATE_SUMMER)
            .withDuration(VALID_DURATION_SUMMER).withPeople(VALID_PEOPLE_SUMMER)
            .withBudget(VALID_BUDGET_SUMMER).build();
    public static final Itinerary WINTER = new ItineraryBuilder().withName(VALID_ITINERARY_DESC_WINTER)
            .withCountry(VALID_COUNTRY_WINTER).withStartDate(VALID_START_DATE_WINTER)
            .withDuration(VALID_DURATION_WINTER).withPeople(VALID_PEOPLE_WINTER)
            .withBudget(VALID_BUDGET_WINTER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalItineraries() {

    } // prevents instantiation

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
        List<Item> typicalItems = TypicalItems.getTypicalItems();
        Itinerary autumn = getAutumn();
        Itinerary graduation = getGraduation();

        // configure AUTUMN
        autumn.addItem(typicalItems.get(0));
        try {
            autumn.planItem(Index.fromZeroBased(0), new DayNumber("1"), VALID_START_TIME_2330);
        } catch (CommandException e) {
            assert false : e.getMessage();
        }

        // configure GRADUATION
        graduation.addItem(typicalItems.get(1));
        graduation.addItem(typicalItems.get(2));
        graduation.addItem(typicalItems.get(3));
        try {
            graduation.planItem(Index.fromZeroBased(0), new DayNumber("1"), VALID_START_TIME_0000);
            graduation.planItem(Index.fromZeroBased(0), new DayNumber("1"), VALID_START_TIME_1715);
            graduation.planItem(Index.fromZeroBased(0), new DayNumber("2"), VALID_START_TIME_1200);
        } catch (CommandException e) {
            assert false : e.getMessage();
        }

        return new ArrayList<>(Arrays.asList(SPRING, autumn, graduation));
    }
}
