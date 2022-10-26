package seedu.waddle.testutil;

import seedu.waddle.model.itinerary.Budget;
import seedu.waddle.model.itinerary.Country;
import seedu.waddle.model.itinerary.Date;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.model.itinerary.ItineraryDuration;
import seedu.waddle.model.itinerary.Name;
import seedu.waddle.model.itinerary.People;

/**
 * A utility class to help with building Itinerary objects.
 */
public class ItineraryBuilder {
    public static final String DEFAULT_NAME = "Summer";
    public static final String DEFAULT_COUNTRY = "FRANCE";
    public static final String DEFAULT_START_DATE = "2022-10-14";
    public static final String DEFAULT_DURATION = "1";
    public static final String DEFAULT_PEOPLE = "1";
    public static final String DEFAULT_BUDGET = "100.00";

    private Name name;
    private Country country;
    private Date startDate;
    private ItineraryDuration duration;
    private People people;
    private Budget budget;

    /**
     * Creates a {@code ItineraryBuilder} with the default details.
     */
    public ItineraryBuilder() {
        name = new Name(DEFAULT_NAME);
        country = new Country(DEFAULT_COUNTRY);
        startDate = new Date(DEFAULT_START_DATE);
        duration = new ItineraryDuration(DEFAULT_DURATION);
        people = new People(DEFAULT_PEOPLE);
        budget = new Budget(DEFAULT_BUDGET);
    }

    /**
     * Initializes the ItineraryBuilder with the data of {@code itineraryToCopy}.
     */
    public ItineraryBuilder(Itinerary itineraryToCopy) {
        name = itineraryToCopy.getName();
        country = itineraryToCopy.getCountry();
        startDate = itineraryToCopy.getStartDate();
        duration = itineraryToCopy.getDuration();
        people = itineraryToCopy.getPeople();
        budget = itineraryToCopy.getBudget();
    }

    /**
     * Sets the {@code Name} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Country} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withCountry(String country) {
        this.country = new Country(country);
        return this;
    }

    /**
     * Sets the {@code Start Date} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withStartDate(String startDate) {
        this.startDate = new Date(startDate);
        return this;
    }

    /**
     * Sets the {@code ItineraryDuration} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withDuration(String duration) {
        this.duration = new ItineraryDuration(duration);
        return this;
    }

    /**
     * Sets the {@code People} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withPeople(String people) {
        this.people = new People(people);
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withBudget(String budget) {
        this.budget = new Budget(budget);
        return this;
    }

    public Itinerary build() {
        return new Itinerary(name, country, startDate, duration, people, budget);
    }

}
